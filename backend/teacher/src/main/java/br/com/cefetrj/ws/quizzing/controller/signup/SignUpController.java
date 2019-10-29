package br.com.cefetrj.ws.quizzing.controller.signup;

import br.com.cefetrj.ws.quizzing.model.user.ApplicationUser;
import br.com.cefetrj.ws.quizzing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RestController
@Path("/users")
public class SignUpController
{
	private final UserService userService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public SignUpController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder)
	{
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("sign-up")
	public Response signUp(ApplicationUser user)
	{
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userService.createUser(user);
	}
}
