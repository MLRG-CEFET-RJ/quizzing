package br.com.cefetrj.ws.quizzing.controller.curation;

import br.com.cefetrj.ws.quizzing.pojo.QuizDTO;
import br.com.cefetrj.ws.quizzing.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@RestController
@Path("/curation")
@Produces("application/json")
public class CurationController
{
	private final QuizService quizService;

	@Autowired
	public CurationController(QuizService quizService)
	{
		this.quizService = quizService;
	}

	@POST
	public Response createFromFile(@Context HttpHeaders httpheaders, @Valid QuizDTO quizDTO)
	{
		String authorizationHeader = httpheaders.getHeaderString("Authorization");
		return quizService.createQuiz(authorizationHeader, quizDTO);
	}
}
