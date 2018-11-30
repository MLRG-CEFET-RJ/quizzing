package br.com.cefetrj.ws.quizzing.service.controller.user;

import br.com.cefetrj.ws.quizzing.model.user.User;
import br.com.cefetrj.ws.quizzing.service.dao.UserService;
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
	@Autowired
	UserService userService;

	//TODO: Remover depois de testar
	@GET
	@Path("/users")
	public List<User> getAllUsers()
	{
		return userService.findAll();
	}

	@POST
	@Path("/new")
	public Response crateUser(@Valid User user)
	{
		return userService.createUser(user);
	}

	@PUT
	@Path("/edit")
	public Response editUser(@Valid User user)
	{
		return userService.editUser(user);
	}

	@DELETE
	@Path("/delete")
	public Response deleteUser(@Valid User user)
	{
		return userService.deleteUser(user);
	}
}
