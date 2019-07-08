package br.com.cefetrj.ws.quizzing.service;

import br.com.cefetrj.ws.quizzing.model.question.Question;
import br.com.cefetrj.ws.quizzing.model.question.QuestionSolr;
import br.com.cefetrj.ws.quizzing.model.rating.Rating;
import br.com.cefetrj.ws.quizzing.model.tag.Tag;
import br.com.cefetrj.ws.quizzing.model.user.ApplicationUser;
import br.com.cefetrj.ws.quizzing.pojo.OptionsDTO;
import br.com.cefetrj.ws.quizzing.pojo.QuestionDTO;
import br.com.cefetrj.ws.quizzing.pojo.RatingDTO;
import br.com.cefetrj.ws.quizzing.repository.jpaRepository.QuestionRepository;
import br.com.cefetrj.ws.quizzing.repository.jpaRepository.RateRepository;
import br.com.cefetrj.ws.quizzing.repository.jpaRepository.TagRepository;
import br.com.cefetrj.ws.quizzing.repository.solrRepository.SolrQuestionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.*;

import static javax.ws.rs.core.Response.Status.*;

@Service
public class QuestionService
{
	private final QuestionRepository questionRepository;

	private final TagRepository tagRepository;

	private final SolrQuestionRepository solrQuestionRepository;

	private final RateRepository rateRepository;

	private final UserService userService;

	private final Logger LOGGER = LoggerFactory.getLogger(QuizService.class);

	@Autowired
	public QuestionService(QuestionRepository questionRepository, TagRepository tagRepository, SolrQuestionRepository solrQuestionRepository, RateRepository rateRepository, UserService userService)
	{
		this.questionRepository = questionRepository;
		this.tagRepository = tagRepository;
		this.solrQuestionRepository = solrQuestionRepository;
		this.rateRepository = rateRepository;
		this.userService = userService;
	}

	//TODO: Implementar método de busca com outros campos
	public List<QuestionSolr> getQuestions(String str)
	{
		return solrQuestionRepository.findAllByQuestion(str);
	}

	public List<Question> getUserQuestions(String userAuthorization)
	{
		ApplicationUser user = getUser(userAuthorization);
		return questionRepository.findByUser(user);
	}

	public Response createQuestion(String userAuthorization, QuestionDTO questionDTO)
	{
		ApplicationUser user = getUser(userAuthorization);
		Question createdQuestion = newQuestion(user, questionDTO);
		return Response.status(CREATED).entity(createdQuestion).build();
	}

	public Response updateQuestion(String userAuthorization, QuestionDTO questionDTO)
	{
		Question questionToUpdate = questionRepository.findById(questionDTO.getId()).orElseThrow(() -> new NotFoundException("Question Not found"));
		ApplicationUser user = getUser(userAuthorization);
		if (questionToUpdate.getUser().equals(user))
		{
			Question question = buildQuestionFromDTO(questionToUpdate, questionDTO);
			saveQuestion(question);
			return Response.status(OK).entity("{\"message\": \"Question successfully updated\"}").build();
		}
		else
		{
			return Response.status(UNAUTHORIZED).entity("{\"message\": \"This user can not update this question\"}").build();
		}
	}

	public Response deleteQuestion(String userAuthorization, Question question)
	{
		Question questionToBeDeleted = questionRepository.findById(question.getId()).orElseThrow(() -> new NotFoundException("Question Not Found"));
		ApplicationUser user = getUser(userAuthorization);
		if (questionToBeDeleted.getUser().equals(user))
		{
			questionRepository.delete(questionToBeDeleted);
			solrQuestionRepository.delete(new QuestionSolr(questionToBeDeleted));
			return Response.status(OK).entity("{\"message\": \"Question deleted successfully\"}").build();
		}
		else
		{
			return Response.status(UNAUTHORIZED).entity("{\"message\": \"This user can not delete this question\"}").build();
		}
	}

	public Response rateQuestion(String userAuthorization, RatingDTO ratingDTO)
	{
		ApplicationUser user = getUser(userAuthorization);
		Long userId = user.getId();
		Optional<Rating> optionalRating = rateRepository.findByUserIdAndQuestionId(userId, ratingDTO.getQuestionId());
		if (!optionalRating.isPresent())
		{
			Question question = questionRepository.findById(ratingDTO.getQuestionId()).orElseThrow(() -> new NotFoundException("Question not found"));

			if (!question.getUser().getId().equals(userId))
			{
				Rating ratingToBeCreated = new Rating(user, question);
				ratingToBeCreated.setRating(ratingDTO.getRating());
				Rating cratedRating = rateRepository.save(ratingToBeCreated);

				updateIndex(question);

				return Response.status(CREATED).entity(cratedRating).build();
			}
			else
			{
				return Response.status(UNAUTHORIZED).entity("{\"message\": \"This user can not rate this question\"}").build();
			}
		}
		else
		{
			Rating rating = optionalRating.get();
			rating.setRating(ratingDTO.getRating());
			Rating updatedRating = rateRepository.save(rating);

			updateIndex(rating.getQuestion());
			return Response.status(CREATED).entity(updatedRating).build();
		}
	}

	private ApplicationUser getUser(String userAuthorization)
	{
		return userService.getUserByAuthorization(userAuthorization);
	}

	private Question newQuestion(ApplicationUser user, QuestionDTO questionDTO)
	{
		Question question = new Question();
		question.setUser(user);
		Question questionToBeCreated = buildQuestionFromDTO(question, questionDTO);
		return saveQuestion(questionToBeCreated);
	}

	private Question saveQuestion(Question questionToSave)
	{
		Question createdQuestion = questionRepository.save(questionToSave);
		updateIndex(createdQuestion);
		return createdQuestion;
	}

	private Question buildQuestionFromDTO(Question question, QuestionDTO questionDTO)
	{
		question.setQuestion(questionDTO.getQuestion());
		question.setType(questionDTO.getType());
		question.setAnswer(questionDTO.getAnswer());
		ArrayList<OptionsDTO> optionsList = questionDTO.getOptions();
		question.setOptions(getOptionsFromDTO(optionsList));
		String pic = questionDTO.getImage();
		if (null != pic)
		{
			question.setPic(Base64.getDecoder().decode(pic));
		}
		else
		{
			question.setPic(null);
		}

		String tagsStr = questionDTO.getTags();
		if (null != questionDTO.getTags())
		{
			Set<Tag> tags = question.getTags();
			for (String tag : tagsStr.split(","))
			{
				Optional<Tag> optionalTag = tagRepository.findByName(tag.trim());
				if (optionalTag.isPresent())
				{
					Tag t = optionalTag.get();
					tags.add(t);
					t.getQuestions().add(question);
				}
				else
				{
					Tag t = new Tag(tag);
					tags.add(t);
					t.getQuestions().add(question);
				}
			}
		}
		return question;
	}

	private String getOptionsFromDTO(ArrayList<OptionsDTO> optionsList)
	{
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			return mapper.writeValueAsString(optionsList);
		}
		catch (JsonProcessingException e)
		{
			return null;
		}
	}

	private void updateIndex(Question question)
	{
		List<Rating> questionRatings = rateRepository.findAllByQuestion(question);

		int totalRating = 0;
		if (!questionRatings.isEmpty())
		{
			int sum = 0;

			for (Rating rating : questionRatings)
			{
				sum += rating.getRating().getValue();
			}

			totalRating = sum / questionRatings.size();
		}

		QuestionSolr questionSolr = new QuestionSolr(question);
		questionSolr.setRating(totalRating);
		try
		{
			solrQuestionRepository.save(questionSolr);
		}
		catch (Exception e)
		{
			LOGGER.error("Ocorreu um erro ao indexar a questão {}", questionSolr, e);
		}
	}

	Question getQuestionById(Long id)
	{
		return questionRepository.findById(id).orElse(null);
	}

}
