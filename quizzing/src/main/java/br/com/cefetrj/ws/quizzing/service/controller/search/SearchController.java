package br.com.cefetrj.ws.quizzing.service.controller.search;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/search")
public class SearchController
{
	@GET
	@Produces("text/plain")
	public String helloSearch()
	{
		return "Hello Search";
	}
}
