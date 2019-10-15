package br.com.cefetrj.ws.quizzing.student.controller;

import br.com.cefetrj.ws.quizzing.student.request.AnswersRequest;
import br.com.cefetrj.ws.quizzing.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@RestController
@Path("/rest/student/{name}")
@Produces("application/json")
public class StudentController
{
	@Autowired
	StudentService studentService;

	@PathParam("name")
	String name;

	@POST
	@Path("/activity/{id}")
	public Response joinActivity(@PathVariable String id)
	{
		return studentService.joinActivity(id);
	}

	@POST
	@Path("/activity/{id}/answares")
	@Consumes("application/json")
	public Response submitAnswers(@PathVariable String id, @RequestBody AnswersRequest answers)
	{
		return studentService.submit(name, id, answers);
	}
}
