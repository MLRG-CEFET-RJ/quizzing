package br.com.cefetrj.ws.quizzing.controller.curation;

import br.com.cefetrj.ws.quizzing.model.question.QuestionDTO;
import br.com.cefetrj.ws.quizzing.service.QuestionService;
import br.com.cefetrj.ws.quizzing.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@RestController
@Path("/curation/{id}")
@Produces("application/json")
public class CurationController
{

	private final QuestionService questionService;

	private final QuizService quizService;

	@PathParam("id")
	String id;


	@Autowired
	public CurationController(QuestionService questionService, QuizService quizService)
	{
		this.questionService = questionService;
		this.quizService = quizService;
	}

	@POST
	public Response createFromFile(ArrayList<QuestionDTO> questionsDTO)
	{
		long userId = Long.parseLong(id);
		final ArrayList<Long> ids = questionService.createMultipleQuestions(userId, questionsDTO);
		StringBuilder idsStr = new StringBuilder();
		for (Long id : ids)
		{
			idsStr.append(id);
		}
		return quizService.createQuiz(userId, idsStr.toString());
	}
}
