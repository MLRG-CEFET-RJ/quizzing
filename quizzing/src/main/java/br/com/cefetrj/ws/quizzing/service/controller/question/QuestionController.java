package br.com.cefetrj.ws.quizzing.service.controller.question;


import br.com.cefetrj.ws.quizzing.model.question.Question;
import br.com.cefetrj.ws.quizzing.service.dao.QuestionService;
import org.json.JSONObject;
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
	@Autowired
	QuestionService questionService;

	@PathParam("id")
	String id;

	@GET
	@Path("/questions")
	public List<Question> getQuestions()
	{
		return questionService.getQuestions(Long.parseLong(id));
	}

	@POST
	@Path("/new")
	public Response crateQuestion(JSONObject question)
	{
		return questionService.createQuestion(Long.parseLong(id), question);
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