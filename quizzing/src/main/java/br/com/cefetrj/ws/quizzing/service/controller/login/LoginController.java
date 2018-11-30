package br.com.cefetrj.ws.quizzing.service.controller.login;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/login")
public class LoginController
{
	@GET
	@Produces("text/plain")
	public String helloLogin()
	{
		return "Hello Login";
	}
}
