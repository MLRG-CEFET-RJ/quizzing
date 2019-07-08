package br.com.cefetrj.ws.quizzing.service;

import br.com.cefetrj.ws.quizzing.model.question.Question;
import br.com.cefetrj.ws.quizzing.model.quiz.Quiz;
import br.com.cefetrj.ws.quizzing.model.user.ApplicationUser;
import br.com.cefetrj.ws.quizzing.pojo.Id;
import br.com.cefetrj.ws.quizzing.pojo.QuizDTO;
import br.com.cefetrj.ws.quizzing.repository.jpaRepository.QuizRespository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.Response.Status.*;

@Service
public class QuizService
{
	private final QuizRespository quizRespository;

	private final UserService userService;
	private QuestionService questionService;

	private final Logger LOGGER = LoggerFactory.getLogger(QuizService.class);

	@Autowired
	public QuizService(QuizRespository quizRespository, UserService userService, QuestionService questionService)
	{
		this.quizRespository = quizRespository;
		this.userService = userService;
		this.questionService = questionService;
	}

	public List<Quiz> getQuizzes(String userAuthorization)
	{
		ApplicationUser user = userService.getUserByAuthorization(userAuthorization);
		return quizRespository.findByUserId(user.getId());
	}

	public Response createQuiz(String userAuthorization, QuizDTO quizDTO)
	{
		try
		{
			ApplicationUser user = userService.getUserByAuthorization(userAuthorization);
			Quiz quizToBeCreated = new Quiz();
			quizToBeCreated.setUser(user);
			quizToBeCreated.setName(quizDTO.getName());
			ObjectMapper mapper = new ObjectMapper();
			String questions = mapper.writeValueAsString(getQuestionsList(quizDTO.getIds()));

			quizToBeCreated.setQuestions(questions);
			quizRespository.save(quizToBeCreated);

			return Response.status(CREATED).entity("{\"message\": \"Quiz created successfully\"}").build();
		}
		catch (JsonProcessingException e)
		{
			LOGGER.error("Ocorreu um erro ao gerar o quiz {}", quizDTO, e);
			return Response.status(INTERNAL_SERVER_ERROR).entity("{\"message\": \"Internal Server Error\"}").build();
		}
	}

	public Response updateQuiz(String userAuthorization, QuizDTO quizDTO)
	{
		ApplicationUser user = userService.getUserByAuthorization(userAuthorization);
		Quiz quizToBeUpdated = quizRespository.findById(quizDTO.getId()).orElseThrow(() -> new NotFoundException("Quiz not found"));
		if (quizToBeUpdated.getUser().equals(user))
		{
			try
			{
				quizToBeUpdated.setName(quizDTO.getName());

				ObjectMapper mapper = new ObjectMapper();
				String questions = mapper.writeValueAsString(getQuestionsList(quizDTO.getIds()));
				quizToBeUpdated.setQuestions(questions);
				return Response.status(OK).entity(quizRespository.save(quizToBeUpdated)).build();

			}
			catch (JsonProcessingException e)
			{
				LOGGER.error("Ocorreu um erro ao gerar o quiz {}", quizDTO, e);
				return Response.status(INTERNAL_SERVER_ERROR).entity("{\"message\": \"Internal Server Error\"}").build();
			}
		}
		else
		{
			return Response.status(UNAUTHORIZED).entity("{\"message\": \"This User can not update this Quiz\"}").build();
		}
	}

	public Response deleteQuiz(String userAuthorization, Quiz quiz)
	{
		ApplicationUser user = userService.getUserByAuthorization(userAuthorization);
		Quiz quizToBeDeleted = quizRespository.findById(quiz.getId()).orElseThrow(() -> new NotFoundException("Quiz not found"));
		if (quizToBeDeleted.getUser().equals(user))
		{
			quizRespository.delete(quizToBeDeleted);
			return Response.status(OK).entity("{\"message\": \"Quiz deleted successfully\"}").build();
		}
		else
		{
			return Response.status(UNAUTHORIZED).entity("{\"message\": \"This User can not delete this Quiz\"}").build();
		}
	}

	private List<Question> getQuestionsList(ArrayList<Id> ids)
	{
		List<Question> questions = new ArrayList<>();
		for (Id id : ids)
		{
			Question question = questionService.getQuestionById(id.getId());
			if (null != question)
			{
				questions.add(question);
			}
		}
		return questions;
	}

}
