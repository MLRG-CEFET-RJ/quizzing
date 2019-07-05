package br.com.cefetrj.ws.quizzing.service;

import br.com.cefetrj.ws.quizzing.model.user.User;
import br.com.cefetrj.ws.quizzing.repository.jpaRepository.UserRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.Status.*;


@Service
public class UserService
{
	private final UserRepository userRepository;

	private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	public UserService(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}

	public  Response createUser(User user)
	{
		JSONObject responseObj = new JSONObject();
		User createdUser;

		if( user.getName() == null || user.getEmail() == null || user.getPassword() == null)
		{
			return Response.status(BAD_REQUEST).entity("Please provide all mandatory inputs").build();
		}
		try
		{
			createdUser = userRepository.save(user);
		}
		catch (Exception e)
		{
			LOGGER.error("Email já existente", e);
			return duplicatedEmailResponse(responseObj);
		}

		try
		{
			responseObj.put("message", "User created successfully");
			responseObj.put("UserName", createdUser.getName());
			responseObj.put("UserEmail", createdUser.getEmail());
		}
		catch (JSONException e)
		{
			LOGGER.error("Erro ao criar o objeto JSON", e);
			return Response.status(CREATED).entity(responseObj.toString()).build();
		}
		return Response.status(CREATED).entity(responseObj.toString()).build();

	}

	//TODO: Adicionar verificação de login em todos os métodos
	public Response updateUser(User user)
	{

		JSONObject responseObj = new JSONObject();
		User userToUpdate = userRepository.findById(user.getId())
		                                  .orElseThrow( () -> new RuntimeException("Not Found") );
		userToUpdate.setName(user.getName());
		userToUpdate.setEmail(user.getEmail());
		userToUpdate.setPassword(user.getPassword());

		try
		{
			userRepository.save(userToUpdate);
			try
			{
				responseObj.put("message", "User successfully updated");
				responseObj.put("UserName", userToUpdate.getName());
				responseObj.put("UserEmail", userToUpdate.getEmail());
			}
			catch (JSONException e)
			{
				LOGGER.error("Erro ao criar o objeto JSON", e);
				return Response.status(OK).entity(responseObj.toString()).build();
			}
			return Response.status(OK).entity(responseObj.toString()).build();
		}
		catch (Exception e)
		{
			LOGGER.error("Email já existente", e);
			return duplicatedEmailResponse(responseObj);
		}
	}

	public Response deleteUser(User user)
	{
		User userToBeDeleted = userRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("Not Found"));
		userRepository.delete(userToBeDeleted);
		return Response.status(OK).entity("{\"message\": \"User deleted successfully\"}").build();
	}

	private Response duplicatedEmailResponse(JSONObject obj)
	{
		try
		{
			obj.put("message", "Email is already in use");
		}
		catch (JSONException e1)
		{
			LOGGER.error("Erro ao criar o objeto JSON", e1);
			return Response.status(BAD_REQUEST).entity(obj.toString()).build();
		}
		return Response.status(BAD_REQUEST).entity(obj.toString()).build();
	}

	// TODO: Remover depois de testar
	public List<User> findAll()
	{
		return userRepository.findAll();
	}
}
