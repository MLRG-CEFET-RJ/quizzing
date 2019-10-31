package br.com.cefetrj.ws.quizzing.controller.question;

import br.com.cefetrj.ws.quizzing.model.question.Question;
import br.com.cefetrj.ws.quizzing.pojo.QuestionDTO;
import br.com.cefetrj.ws.quizzing.pojo.RatingDTO;
import br.com.cefetrj.ws.quizzing.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@Path("/question")
@Produces("application/json")
public class QuestionController
{
	private final QuestionService questionService;

	@Autowired
	public QuestionController(QuestionService questionService)
	{
		this.questionService = questionService;
	}

	@GET
	@Path("/questions")
	public List<Question> getUserQuestions(@Context HttpHeaders httpheaders)
	{
		String authorizationHeader = httpheaders.getHeaderString("Authorization");
		return questionService.getUserQuestions(authorizationHeader);
	}

	@GET
	@Path("/questions/{id}")
	public Question getUserQuestions(@Context HttpHeaders httpheaders, @PathParam("id") Long id)
	{
		String authorizationHeader = httpheaders.getHeaderString("Authorization");
		return questionService.getUserQuestion(authorizationHeader, id);
	}

	@POST
	@Path("/new")
	@Consumes("application/json")
	public Response createQuestion(@Context HttpHeaders httpheaders, @Valid QuestionDTO questionDTO)
	{
		String authorizationHeader = httpheaders.getHeaderString("Authorization");
		return questionService.createQuestion(authorizationHeader, questionDTO);
	}

	@PUT
	@Path("/edit")
	public Response editQuestion(@Context HttpHeaders httpheaders, @Valid QuestionDTO questionDTO)
	{
		String authorizationHeader = httpheaders.getHeaderString("Authorization");
		return questionService.updateQuestion(authorizationHeader, questionDTO);
	}

	@DELETE
	@Path("/delete/{id}")
	public Response deleteQuestion(@Context HttpHeaders httpheaders, @PathParam("id") Long id)
	{
		String authorizationHeader = httpheaders.getHeaderString("Authorization");
		return questionService.deleteQuestion(authorizationHeader, id);
	}

	@POST
	@Path("/rate")
	public Response rateQuestion(@Context HttpHeaders httpheaders, @Valid RatingDTO rating)
	{
		String authorizationHeader = httpheaders.getHeaderString("Authorization");
		return questionService.rateQuestion(authorizationHeader, rating);
	}
}
