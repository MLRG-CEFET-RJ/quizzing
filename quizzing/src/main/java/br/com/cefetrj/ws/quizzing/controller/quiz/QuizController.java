package br.com.cefetrj.ws.quizzing.controller.quiz;

import br.com.cefetrj.ws.quizzing.model.quiz.Quiz;
import br.com.cefetrj.ws.quizzing.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@Path("/quiz/{id}")
@Produces("application/json")
public class QuizController
{
	private final QuizService quizService;

	@PathParam("id")
	String id;

	@Autowired
	public QuizController(QuizService quizService)
	{
		this.quizService = quizService;
	}

	@GET
	@Path("quizzes")
	public List<Quiz> getQuizzes()
	{
		return quizService.getQuizzes(Long.parseLong(id));
	}

	@POST
	@Path("new")
	@Consumes(MediaType.TEXT_PLAIN)
	public Response createQuiz(String questionsIds)
	{
		return quizService.createQuiz(Long.parseLong(id), questionsIds);
	}

	@PUT
	@Path("edit/{quizId}")
	@Consumes(MediaType.TEXT_PLAIN)
	public Response editQuiz(@PathParam("quizId") String quizId, String newQuestions)
	{
		return quizService.updateQuiz(quizId, newQuestions);
	}

	@DELETE
	@Path("delete")
	public Response deleteQuiz(@Valid Quiz quiz)
	{
		return quizService.deleteQuiz(quiz);
	}

}
