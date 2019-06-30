package br.com.cefetrj.ws.quizzing.service;

import br.com.cefetrj.ws.quizzing.model.question.Question;
import br.com.cefetrj.ws.quizzing.model.question.QuestionSolr;
import br.com.cefetrj.ws.quizzing.pojo.QuestionDTO;
import br.com.cefetrj.ws.quizzing.repository.jpaRepository.QuestionRepository;
import br.com.cefetrj.ws.quizzing.repository.solrRepository.SolrQuestionRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class QuestionService
{
	private final QuestionRepository questionRepository;

	private final SolrQuestionRepository solrQuestionRepository;

	private final Logger LOGGER = LoggerFactory.getLogger(QuizService.class);

	@Autowired
	public QuestionService(QuestionRepository questionRepository, SolrQuestionRepository solrQuestionRepository)
	{
		this.questionRepository = questionRepository;
		this.solrQuestionRepository = solrQuestionRepository;
	}

	public List<Question> getQuestions(Long userId)
	{
		return questionRepository.findByUserId(userId);
	}

	public Response createQuestion(Long userId, QuestionDTO questionDTO)
	{
		JSONObject responseObj = new JSONObject();

		Question createdQuestion = newQuestion(userId, questionDTO);
		try
		{
			responseObj.put("message", "Question created successfully");
			responseObj.put("Question", createdQuestion.getQuestion());
		}
		catch (JSONException e)
		{
			LOGGER.error("Erro ao criar o objeto Json", e);
			return Response.status(201).entity(responseObj.toString()).build();
		}
		return Response.status(201).entity(responseObj.toString()).build();
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

	public Response updateQuestion(Question question)
	{
		Question questionToUpdate = questionRepository.findById(question.getId()).orElseThrow(() -> new RuntimeException("Not found"));
		questionToUpdate.setQuestion(question.getQuestion());
		questionToUpdate.setType(question.getType());
		questionToUpdate.setAnswer(question.getAnswer());
		questionToUpdate.setPic(question.getPic());
		String options = question.getOptions();
		if (options != null)
		{
			questionToUpdate.setOptions(options);
		}
		questionRepository.save(questionToUpdate);
		solrQuestionRepository.save(new QuestionSolr(questionToUpdate));

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
		Question questionToBeCreated = new Question();
		questionToBeCreated.setUserId(userId);
		questionToBeCreated.setQuestion(questionDTO.getQuestion());
		questionToBeCreated.setType(questionDTO.getType());
		questionToBeCreated.setAnswer(questionDTO.getAnsware());
		String options = questionDTO.getOptions();
		if (options != null)
		{
			questionToBeCreated.setOptions(options);
		}
		String pic = questionDTO.getImage();
		if (!pic.equals("null"))
		{
			questionToBeCreated.setPic(Base64.getDecoder().decode(pic));
		}
		else
		{
			questionToBeCreated.setPic(null);
		}

		Question createdQuestion = questionRepository.save(questionToBeCreated);
		solrQuestionRepository.save(new QuestionSolr(createdQuestion));

		return createdQuestion;
	}
}
