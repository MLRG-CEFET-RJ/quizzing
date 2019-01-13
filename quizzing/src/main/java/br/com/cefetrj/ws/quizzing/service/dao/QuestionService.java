package br.com.cefetrj.ws.quizzing.service.dao;

import br.com.cefetrj.ws.quizzing.model.question.Question;
import br.com.cefetrj.ws.quizzing.repository.QuestionRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Base64;
import java.util.List;

@Service
public class QuestionService
{
	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	UserService userService;


	public List<Question> getQuestions(Long userId)
	{
		return questionRepository.findByUserId(userId);
	}

	public Response createQuestion(Long userId, JSONObject questionObj)
	{
		Question questionToBeCreated = new Question();
		Question createdQuestion;
		JSONObject responseObj = new JSONObject();

		try
		{
			questionToBeCreated.setUserId(userId);
			questionToBeCreated.setQuestion(questionObj.getString("Question"));
			questionToBeCreated.setType(questionObj.getString("Type"));
			JSONArray options = questionObj.optJSONArray("Options");
			if (options != null)
			{
				questionToBeCreated.setOptions(options.toString());
			}
			String pic = questionObj.getString("Image");
			if (!pic.equals("null"))
			{
				questionToBeCreated.setPic(Base64.getDecoder().decode(pic));
			}
			else
			{
				questionToBeCreated.setPic(null);
			}
			createdQuestion = questionRepository.save(questionToBeCreated);
			try
			{
				responseObj.put("message", "Question created successfully");
				responseObj.put("Question", createdQuestion.getQuestion());
			}
			catch (JSONException e)
			{
				return Response.status(201).entity(responseObj.toString()).build();
			}
			return Response.status(201).entity(responseObj.toString()).build();
		}
		catch (JSONException e)
		{
			return Response.status(500).entity("{\"message\": \"Internal Server Error\"}").build();
		}
	}

	public Response updateQuestion(Question question)
	{
		Question questionToUpdate = questionRepository.findById(question.getId())
		                                              .orElseThrow(() -> new RuntimeException("Not find"));
		questionToUpdate.setQuestion(question.getQuestion());
		questionToUpdate.setType(question.getType());
		questionToUpdate.setPic(question.getPic());
		String options = question.getOptions();
		if (options != null)
		{
			questionToUpdate.setOptions(options);
		}
		questionRepository.save(questionToUpdate);

		return  Response.status(200).entity("{\"message\": \"Question successfully updated\"}").build();
	}

	public Response deleteQuestion(Question question)
	{
		Question questionToBeDeleted = questionRepository.findById(question.getId())
		                                                 .orElseThrow(() -> new RuntimeException("Not find"));
		questionRepository.delete(questionToBeDeleted);
		return Response.status(200).entity("{\"message\": \"Question deleted successfully\"}").build();
	}

}
