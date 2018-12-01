package br.com.cefetrj.ws.quizzing.config;

import br.com.cefetrj.ws.quizzing.service.controller.activity.ActivityController;
import br.com.cefetrj.ws.quizzing.service.controller.login.LoginController;
import br.com.cefetrj.ws.quizzing.service.controller.question.QuestionController;
import br.com.cefetrj.ws.quizzing.service.controller.quiz.QuizController;
import br.com.cefetrj.ws.quizzing.service.controller.search.SearchController;
import br.com.cefetrj.ws.quizzing.service.controller.user.UserController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig
{
	public JerseyConfig()
	{
		register(ActivityController.class);
		register(UserController.class);
		register(LoginController.class);
		register(QuestionController.class);
		register(QuizController.class);
		register(SearchController.class);
	}
}