package br.com.cefetrj.ws.quizzing.service;

import br.com.cefetrj.ws.quizzing.model.question.Question;
import br.com.cefetrj.ws.quizzing.model.quiz.Quiz;
import br.com.cefetrj.ws.quizzing.repository.QuizRespository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;

@Service
public class QuizService
{
	private final QuizRespository quizRespository;

	private final QuestionService questionService;

	@Autowired
	public QuizService(QuizRespository quizRespository, QuestionService questionService)
	{
		this.quizRespository = quizRespository;
		this.questionService = questionService;
	}

	public List<Quiz> getQuizzes(Long userId)
	{
		return quizRespository.findByUserId(userId);
	}

	public Response createQuiz(Long userId, String questionsIds)
	{
		Quiz quizToBeCreated = new Quiz();
		JSONArray questions = new JSONArray();

		for (String questionId : questionsIds.split(","))
		{
			Question question = questionService.getQuestion(Long.parseLong(questionId));// TODO: buscar questões pelo id
			JSONObject questionObj = new JSONObject();
			try
			{
				questionObj.put("Question", question.getQuestion());
				questionObj.put("Type", question.getType());
				questionObj.put("Options", question.getOptions());
				questionObj.put("Pic", question.getPic());
				questions.put(question);
			}
			catch (JSONException e)
			{
				return Response.status(500).entity("{\"message\": \"Internal Server Error\"}").build();
			}
		}
		quizToBeCreated.setUserId(userId);
		quizToBeCreated.setQuestions(questions.toString());
		quizRespository.save(quizToBeCreated);

		return Response.status(201).entity("{\"message\": \"Quiz created successfully\"}").build();

	}

	public Response updateQuiz(Quiz quiz)
	{
		return null;
	}

	public Response deleteQuiz(Quiz quiz)
	{
		Quiz quizToBeDeleted = quizRespository.findById(quiz.getId()).orElseThrow(() -> new RuntimeException("Not find"));
		quizRespository.delete(quizToBeDeleted);
		return Response.status(200).entity("{\"message\": \"Quiz deleted successfully\"}").build();
	}
}
