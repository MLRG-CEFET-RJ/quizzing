package br.com.cefetrj.ws.quizzing.service.dao;

import br.com.cefetrj.ws.quizzing.model.user.User;
import br.com.cefetrj.ws.quizzing.repository.UserRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;


@Service
public class UserService
{
	@Autowired
	UserRepository userRepository;

	public  Response createUser(User user)
	{
		JSONObject obj = new JSONObject();

		if( user.getName() == null || user.getEmail() == null || user.getPassword() == null)
		{
			return Response.status(400).entity("Please provide all mandatory inputs").build();
		}
		try
		{
			userRepository.save(user);
		}
		catch (Exception e)
		{
			return duplicatedEmailResponse(obj);
		}

		try
		{
			obj.put("message", "User created successfully");
			obj.put("UserName", user.getName());
			obj.put("UserEmail", user.getEmail());
		}
		catch (JSONException e)
		{
			return Response.status(201).entity(obj.toString()).build();
		}
		return Response.status(201).entity(obj.toString()).build();

	}

//TODO: Adicionar verificação de login em todos os métodos
	public Response editUser(User user)
	{

		JSONObject obj = new JSONObject();
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
				obj.put("message", "User successfully updated");
				obj.put("UserName", user.getName());
				obj.put("UserEmail", user.getEmail());
			}
			catch (JSONException e)
			{
				return  Response.status(200).entity(obj.toString()).build();
			}
			return  Response.status(200).entity(obj.toString()).build();
		}
		catch (Exception e)
		{
			return duplicatedEmailResponse(obj);
		}
	}

	public Response deleteUser(User user)
	{
		User userTobedeleted = userRepository.findById(user.getId())
				.orElseThrow(() -> new RuntimeException("Not find"));
		userRepository.delete(userTobedeleted);
		return  Response.status(200).entity("{\"message\": \"User deleted successfully\"}").build();
	}

	private Response duplicatedEmailResponse(JSONObject obj)
	{
		try
		{
			obj.put("message", "Email is already in use");
		}
		catch (JSONException e1)
		{
			return Response.status(409).entity(obj.toString()).build();
		}
		return Response.status(409).entity(obj.toString()).build();
	}



	// TODO: Remover depois de testar
	public List<User> findAll()
	{
		return userRepository.findAll();
	}
}
