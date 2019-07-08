package br.com.cefetrj.ws.quizzing.controller.user;

import br.com.cefetrj.ws.quizzing.model.user.ApplicationUser;
import br.com.cefetrj.ws.quizzing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@Path("/user")
@Produces("application/json")
public class UserController
{
	private final UserService userService;

	@Autowired
	public UserController(UserService userService)
	{
		this.userService = userService;
	}

	//TODO: Remover depois de testar
	@GET
	@Path("/users")
	public List<ApplicationUser> getAllUsers()
	{
		return userService.findAll();
	}

	@PUT
	@Path("/edit")
	public Response editUser(@Valid ApplicationUser user)
	{
		return userService.updateUser(user);
	}

	@DELETE
	@Path("/delete")
	public Response deleteUser(@Valid ApplicationUser user)
	{
		return userService.deleteUser(user);
	}
}
