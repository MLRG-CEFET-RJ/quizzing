package br.com.cefetrj.ws.quizzing.controller.question;


import br.com.cefetrj.ws.quizzing.model.question.Question;
import br.com.cefetrj.ws.quizzing.pojo.QuestionDTO;
import br.com.cefetrj.ws.quizzing.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@Path("/question/{id}")
@Produces("application/json")
public class QuestionController
{
	private final QuestionService questionService;

	@PathParam("id")
	String id;

	@Autowired
	public QuestionController(QuestionService questionService)
	{
		this.questionService = questionService;
	}

	@GET
	@Path("/questions")
	public List<Question> getUserQuestions()
	{
		return questionService.getUserQuestions(Long.parseLong(id));
	}

	@POST
	@Path("/new")
	@Consumes("application/json")
	public Response crateQuestion(@Valid QuestionDTO questionDTO)
	{
		return questionService.createQuestion(Long.parseLong(id), questionDTO);
	}

	@PUT
	@Path("/edit")
	public Response editQuestion(@Valid Question question)
	{
		return questionService.updateQuestion(question);
	}

	@DELETE
	@Path("/delete")
	public Response deleteQuestion(@Valid Question question)
	{
		return questionService.deleteQuestion(question);
	}
}
