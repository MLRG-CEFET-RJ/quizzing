package br.com.quizzing.rest;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.quizzing.model.Question;

@Path("/question")
public class RestQuestao 
{
	@PUT
	public Response saveQuestion(Question question) throws InstantiationException, IllegalAccessException, ClassNotFoundException 
	{
		 return Response.status(Status.OK).build();
	}
}
