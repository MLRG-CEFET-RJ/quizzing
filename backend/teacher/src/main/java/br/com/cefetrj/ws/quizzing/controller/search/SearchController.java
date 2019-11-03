package br.com.cefetrj.ws.quizzing.controller.search;

import br.com.cefetrj.ws.quizzing.model.question.QuestionSolr;
import br.com.cefetrj.ws.quizzing.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@Path("/search")
@Produces("application/json")
public class SearchController
{
	private QuestionService questionService;

	@Autowired
	public SearchController(QuestionService questionService)
	{
		this.questionService = questionService;
	}

	@GET
	@Path("/questions")
	public List<QuestionSolr> getQuestions(@QueryParam("q") String query)
	{
		return questionService.getQuestions(query);
	}

	@GET
	@Path("/suggestions")
	public List<QuestionSolr> getSuggestions(@QueryParam("q") String query)
	{
		return questionService.getSuggestions(query);
	}
}
