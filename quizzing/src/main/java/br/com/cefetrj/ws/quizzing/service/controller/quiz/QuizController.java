package br.com.cefetrj.ws.quizzing.service.controller.quiz;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/quiz")
public class QuizController
{
	@GET
	@Produces("text/plain")
	public String helloQuiz()
	{
		return "Hello Quiz";
	}
}
