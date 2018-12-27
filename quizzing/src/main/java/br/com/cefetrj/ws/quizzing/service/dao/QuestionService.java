package br.com.cefetrj.ws.quizzing.service.dao;

import br.com.cefetrj.ws.quizzing.model.option.Option;
import br.com.cefetrj.ws.quizzing.model.question.Question;
import br.com.cefetrj.ws.quizzing.model.user.User;
import br.com.cefetrj.ws.quizzing.repository.QuestionRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.*;

@Service
public class QuestionService
{
	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	OptionService optionService;

	@Autowired
	UserService userService;


	public List<Question> getQuestions(Long userId)
	{
		return questionRepository.findAllUserQuestions(userId);
	}

	public Response createQuestion(Long userId, JSONObject questionObj)
	{
		Question questionToBeCreated = new Question();
		Question createdQuestion;
		JSONObject responseObj = new JSONObject();

		try
		{
			User user = userService.getUserById(userId);
			questionToBeCreated.setUser(user);
			questionToBeCreated.setQuestion(questionObj.getString("Question"));
			JSONArray options = questionObj.optJSONArray("Options");
			if (options != null)
			{
				Map<String, Option> optionMap = new HashMap<>();
				List<Option> correctOptions = new ArrayList<>();
				for (int i= 0; i < options.length(); i++)
				{
					JSONObject option = options.getJSONObject(i);
					Option newOption = optionService.createOption(option.getString("Option"));
					optionMap.put(option.getString("letter"), newOption);
					if (option.getBoolean("isCorrect"))
						correctOptions.add(newOption);
				}
				questionToBeCreated.setOptions(optionMap);
				questionToBeCreated.setCorrectOptions(correctOptions);
			}
			questionToBeCreated.setPic(Base64.getDecoder().decode(questionObj.getString("Image")));
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
		questionToUpdate.setPic(question.getPic());
		questionToUpdate.setOptions(question.getOptions());
		questionToUpdate.setCorrectOptions(question.getCorrectOptions());

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
