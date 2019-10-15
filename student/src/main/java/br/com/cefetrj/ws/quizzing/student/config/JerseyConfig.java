package br.com.cefetrj.ws.quizzing.student.config;

import br.com.cefetrj.ws.quizzing.student.controller.StudentController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig
{
	public JerseyConfig()
	{
		register(StudentController.class);
	}
}
