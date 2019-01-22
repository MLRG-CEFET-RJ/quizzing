package br.com.cefetrj.ws.quizzing.controller.activity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/activity")
public class ActivityController
{
	@GET
	@Produces("text/plain")
	public String helloActivity()
	{
		return "Hello Activity";
	}
}
