package br.com.cefetrj.ws.quizzing.controller.quiz;

import br.com.cefetrj.ws.quizzing.model.quiz.Quiz;
import br.com.cefetrj.ws.quizzing.pojo.QuizDTO;
import br.com.cefetrj.ws.quizzing.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@Path("/quiz")
@Produces("application/json")
public class QuizController
{
	private final QuizService quizService;

	@Autowired
	public QuizController(QuizService quizService)
	{
		this.quizService = quizService;
	}

	@GET
	@Path("/quizzes")
	public List<Quiz> getQuizzes(@Context HttpHeaders httpheaders)
	{
		String authorizationHeader = httpheaders.getHeaderString("Authorization");
		return quizService.getQuizzes(authorizationHeader);
	}

	@POST
	@Path("/new")
	public Response createQuiz(@Context HttpHeaders httpheaders, @Valid QuizDTO quiz)
	{
		String authorizationHeader = httpheaders.getHeaderString("Authorization");
		return quizService.createQuiz(authorizationHeader, quiz);
	}

	@PUT
	@Path("/edit")
	public Response editQuiz(@Context HttpHeaders httpheaders, @Valid QuizDTO quizDTO)
	{
		String authorizationHeader = httpheaders.getHeaderString("Authorization");
		return quizService.updateQuiz(authorizationHeader, quizDTO);
	}

	@DELETE
	@Path("/delete")
	public Response deleteQuiz(@Context HttpHeaders httpheaders, @Valid Quiz quiz)
	{
		String authorizationHeader = httpheaders.getHeaderString("Authorization");
		return quizService.deleteQuiz(authorizationHeader, quiz);
	}

}
