package br.com.cefetrj.ws.quizzing.service;

import br.com.cefetrj.ws.quizzing.model.question.Question;
import br.com.cefetrj.ws.quizzing.model.quiz.Quiz;
import br.com.cefetrj.ws.quizzing.repository.jpaRepository.QuizRespository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;

@Service
public class QuizService
{
	private final QuizRespository quizRespository;

	private final QuestionService questionService;

	private final Logger LOGGER = LoggerFactory.getLogger(QuizService.class);

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
			Question question = questionService.getQuestion(Long.parseLong(questionId));
			try
			{
				JSONObject questionObj = generateQuestionJSONObj(question);
				questions.put(questionObj);
			}
			catch (JSONException e)
			{
				LOGGER.error("Ocorreu um erro ao criar o objeto Json", e);
				return Response.status(500).entity("{\"message\": \"Internal Server Error\"}").build();
			}
		}
		quizToBeCreated.setUserId(userId);
		quizToBeCreated.setQuestions(questions.toString());
		quizRespository.save(quizToBeCreated);

		return Response.status(201).entity("{\"message\": \"Quiz created successfully\"}").build();

	}

	public Response updateQuiz(String quizId, String newQuestions)
	{
		Quiz quizToBeUpdated = quizRespository.findById(Long.parseLong(quizId)).orElseThrow(() -> new RuntimeException("Not found"));
		try
		{
			JSONObject obj = new JSONObject(newQuestions);
			JSONArray questions = new JSONArray(obj.getString("questions"));
			for (String newQuestion : obj.getString("newQuestions").split(","))
			{
				Question question = questionService.getQuestion(Long.parseLong(newQuestion));
				JSONObject questionObj = generateQuestionJSONObj(question);
				questions.put(questionObj);
			}

			quizToBeUpdated.setQuestions(questions.toString());
			quizRespository.save(quizToBeUpdated);

			return Response.status(200).entity("{\"message\": \"Quiz successfully updated\"}").build();
		}
		catch (JSONException e)
		{
			LOGGER.error("Erro ao criar o objeto Json", e);
			return Response.status(500).entity("{\"message\": \"Internal Server Error\"}").build();
		}
	}

	public Response deleteQuiz(Quiz quiz)
	{
		Quiz quizToBeDeleted = quizRespository.findById(quiz.getId()).orElseThrow(() -> new RuntimeException("Not found"));
		quizRespository.delete(quizToBeDeleted);
		return Response.status(200).entity("{\"message\": \"Quiz deleted successfully\"}").build();
	}

	private JSONObject generateQuestionJSONObj(Question question) throws JSONException
	{

		JSONObject questionObj = new JSONObject();
		questionObj.put("Question", question.getQuestion());
		questionObj.put("Type", question.getType());
		questionObj.put("Options", question.getOptions());
		questionObj.put("Pic", question.getPic());
		questionObj.put("Answer", question.getAnswer());
		return questionObj;
	}
}
