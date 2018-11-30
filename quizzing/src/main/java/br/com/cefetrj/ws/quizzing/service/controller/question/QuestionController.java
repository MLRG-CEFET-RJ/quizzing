package br.com.cefetrj.ws.quizzing.service.controller.question;


import br.com.cefetrj.ws.quizzing.model.question.Question;
import br.com.cefetrj.ws.quizzing.model.user.User;
import br.com.cefetrj.ws.quizzing.service.dao.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@Path("/question")
@Produces("application/json")
public class QuestionController
{
	@Autowired
	QuestionService questionService;

	//TODO: Remover depois de testar
	@GET
	@Path("/users")
	public List<Question> getQuestions(@Valid User user)
	{
		return questionService.getQuestions(user.getId());
	}

	@POST
	@Path("/new")
	public Response crateUser(@Valid Question question)
	{
		return questionService.createQuestion(question);
	}

	@PUT
	@Path("/edit")
	public Response editUser(@Valid Question question)
	{
		return questionService.editQuestion(question);
	}

	@DELETE
	@Path("/delete")
	public Response deleteUser(@Valid Question question)
	{
		return questionService.deleteQuestion(question);
	}
}