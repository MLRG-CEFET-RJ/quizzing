package br.com.cefetrj.ws.quizzing.service;

import br.com.cefetrj.ws.quizzing.model.question.Question;
import br.com.cefetrj.ws.quizzing.model.question.QuestionSolr;
import br.com.cefetrj.ws.quizzing.model.tag.Tag;
import br.com.cefetrj.ws.quizzing.pojo.OptionsDTO;
import br.com.cefetrj.ws.quizzing.pojo.QuestionDTO;
import br.com.cefetrj.ws.quizzing.repository.jpaRepository.QuestionRepository;
import br.com.cefetrj.ws.quizzing.repository.jpaRepository.TagRepository;
import br.com.cefetrj.ws.quizzing.repository.solrRepository.SolrQuestionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.*;

@Service
public class QuestionService
{
	private final QuestionRepository questionRepository;

	private final TagRepository tagRepository;

	private final SolrQuestionRepository solrQuestionRepository;

	private final Logger LOGGER = LoggerFactory.getLogger(QuizService.class);

	@Autowired
	public QuestionService(QuestionRepository questionRepository, TagRepository tagRepository, SolrQuestionRepository solrQuestionRepository)
	{
		this.questionRepository = questionRepository;
		this.tagRepository = tagRepository;
		this.solrQuestionRepository = solrQuestionRepository;
	}

	//TODO: Implementar método de busca com outros campos
	public List<QuestionSolr> getQuestions(String str)
	{
		return solrQuestionRepository.findAllByQuestion(str);
	}

	public List<Question> getUserQuestions(Long userId)
	{
		return questionRepository.findByUserId(userId);
	}

	public Response createQuestion(Long userId, QuestionDTO questionDTO)
	{
		Question createdQuestion = newQuestion(userId, questionDTO);
		return Response.status(201).entity(createdQuestion).build();
	}

	public ArrayList<Long> createMultipleQuestions(Long userId, ArrayList<QuestionDTO> questions)
	{
		ArrayList<Long> ids = new ArrayList<>();
		for (QuestionDTO question : questions)
		{
			ids.add(newQuestion(userId, question).getId());
		}
		return ids;
	}

	public Response updateQuestion(QuestionDTO questionDTO)
	{
		Question questionToUpdate = questionRepository.findById(questionDTO.getId()).orElseThrow(() -> new RuntimeException("Not found"));
		Question question = updateObj(questionToUpdate, questionDTO);
		saveQuestion(question);

		return Response.status(200).entity("{\"message\": \"Question successfully updated\"}").build();
	}

	public Response deleteQuestion(Question question)
	{
		Question questionToBeDeleted = questionRepository.findById(question.getId()).orElseThrow(() -> new RuntimeException("Not find"));
		questionRepository.delete(questionToBeDeleted);
		solrQuestionRepository.delete(new QuestionSolr(questionToBeDeleted));
		return Response.status(200).entity("{\"message\": \"Question deleted successfully\"}").build();
	}

	Question getQuestion(Long id)
	{
		return questionRepository.getOne(id);
	}

	private Question newQuestion(Long userId, QuestionDTO questionDTO)
	{
		Question questionToBeCreated = dto2Obj(userId, questionDTO);
		return saveQuestion(questionToBeCreated);
	}

	private Question saveQuestion(Question questionToSave)
	{
		Question createdQuestion = questionRepository.save(questionToSave);
		try
		{
			solrQuestionRepository.save(new QuestionSolr(createdQuestion));
		}
		catch (Exception e)
		{
			LOGGER.error("Ocorreu um erro ao indexar a questão {}", createdQuestion, e);
		}
		return createdQuestion;
	}

	private Question dto2Obj(Long userId, QuestionDTO questionDTO)
	{
		Question question = new Question();
		question.setUserId(userId);
		return buildQuestionFromDTO(question, questionDTO);
	}

	private Question updateObj(Question question, QuestionDTO questionDTO)
	{
		return buildQuestionFromDTO(question, questionDTO);
	}

	private Question buildQuestionFromDTO(Question question, QuestionDTO questionDTO)
	{
		question.setQuestion(questionDTO.getQuestion());
		question.setType(questionDTO.getType());
		question.setAnswer(questionDTO.getAnswer());
		ArrayList<OptionsDTO> optionsList = questionDTO.getOptions();
		question.setOptions(getOptionsFroDTO(optionsList));
		String pic = questionDTO.getImage();
		if (!pic.equals("null"))
		{
			question.setPic(Base64.getDecoder().decode(pic));
		}
		else
		{
			question.setPic(null);
		}

		Set<Tag> tags = question.getTags();
		for (String tag : questionDTO.getTags().split(","))
		{
			Optional<Tag> optionalTag = tagRepository.findByName(tag);
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
		return question;
	}

	private String getOptionsFroDTO(ArrayList<OptionsDTO> optionsList)
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
}
