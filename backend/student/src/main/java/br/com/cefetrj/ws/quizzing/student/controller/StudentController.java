package br.com.cefetrj.ws.quizzing.student.controller;

import br.com.cefetrj.ws.quizzing.student.request.AnswersRequest;
import br.com.cefetrj.ws.quizzing.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;

@RestController
@Path("/rest/student")
@Produces("application/json")
public class StudentController
{
	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService)
	{
		this.studentService = studentService;
	}

	@POST
	@Path("/activity/{code}")
	public Response joinActivity(@PathParam("code") String code) throws IOException
	{
		return studentService.joinActivity(code);
	}

	@POST
	@Path("/{name}/activity/{id}/answers")
	@Consumes("application/json")
	public Response submitAnswers(@PathParam("name") String name, @PathParam("id") Long id, @Valid AnswersRequest answers)
	{
		return studentService.submit(name, id, answers);
	}
}
