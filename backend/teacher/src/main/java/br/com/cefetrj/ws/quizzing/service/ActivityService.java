package br.com.cefetrj.ws.quizzing.service;

import br.com.cefetrj.ws.quizzing.model.activity.Activity;
import br.com.cefetrj.ws.quizzing.model.activity.Answers;
import br.com.cefetrj.ws.quizzing.model.quiz.Quiz;
import br.com.cefetrj.ws.quizzing.model.user.ApplicationUser;
import br.com.cefetrj.ws.quizzing.repository.jpaRepository.ActivityRepository;
import br.com.cefetrj.ws.quizzing.repository.jpaRepository.AnswersRepository;
import br.com.cefetrj.ws.quizzing.repository.jpaRepository.QuizRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static javax.ws.rs.core.Response.Status.*;

@Service
public class ActivityService
{
	private final ActivityRepository activityRepository;

	private final QuizRespository quizRespository;

	private final AnswersRepository answersRepository;

	private final UserService userService;

	@Autowired
	public ActivityService(ActivityRepository activityRepository, QuizRespository quizRespository, AnswersRepository answersRepository, UserService userService)
	{
		this.activityRepository = activityRepository;
		this.quizRespository = quizRespository;
		this.answersRepository = answersRepository;
		this.userService = userService;
	}

	public List<Activity> getUserActivities(String userAuthorization)
	{
		ApplicationUser user = getUser(userAuthorization);
		return activityRepository.findAllByUserId(user.getId());
	}

	public Response createActivity(String userAuthorization, Long quizID)
	{
		ApplicationUser user = getUser(userAuthorization);
		Quiz quiz = quizRespository.findById(quizID).orElseThrow(() -> new NotFoundException("Quiz not found"));

		Activity activity = new Activity(user, quiz);
		activity.setCode(generateCode(quiz));
		Activity createdActivity = activityRepository.save(activity);
		return Response.status(CREATED).entity(createdActivity).build();
	}

	public Response deleteActivity(String userAuthorization, Long id)
	{
		ApplicationUser user = getUser(userAuthorization);
		Activity activityToBeDeleted = activityRepository.findById(id).orElseThrow(() -> new NotFoundException("Activity not found"));
		if (activityToBeDeleted.getUser().equals(user))
		{
			activityRepository.delete(activityToBeDeleted);

			return Response.status(OK).entity("{\"message\": \"Activity deleted successfully\"}").build();
		}
		else
		{
			return Response.status(UNAUTHORIZED).entity("{\"message\": \"This user can not update this question\"}").build();
		}
	}

	public Response stopActivity(String userAuthorization, Long id)
	{
		ApplicationUser user = getUser(userAuthorization);
		Activity activityToStop = activityRepository.findById(id).orElseThrow(() -> new NotFoundException("Activity not found"));
		if (activityToStop.getUser().equals(user))
		{
			activityToStop.setEnded(true);
			activityRepository.save(activityToStop);

			return Response.status(OK).entity("{\"message\": \"Activity stopped successfully\"}").build();
		}
		else
		{
			return Response.status(UNAUTHORIZED).entity("{\"message\": \"This user can not update this question\"}").build();
		}
	}

	public Response getResults(String userAuthorization, Long id)
	{
		ApplicationUser user = getUser(userAuthorization);
		Activity activity = activityRepository.findById(id).orElseThrow(() -> new NotFoundException("Activity not found"));
		if (activity.getUser().equals(user))
		{
			if (activity.getEnded())
			{
				List<Answers> allByActivity = answersRepository.findAllByActivity(activity);
				return Response.status(OK).entity(allByActivity).build();
			}
			else
			{
				return Response.status(BAD_REQUEST).entity("{\"message\": \"Activity must be stopped\"}").build();
			}

		}
		else
		{
			return Response.status(UNAUTHORIZED).entity("{\"message\": \"This user can not get this results\"}").build();
		}
	}

	private String generateCode(Quiz quiz)
	{
		DateFormat dateFormat = new SimpleDateFormat("ddMMHHmm");
		String name = quiz.getName().replaceAll(" ", "");
		name = (name.length() > 2 ) ? name.substring(0,3).toUpperCase() : name.toUpperCase();
		Date date = new Date();
		String format = dateFormat.format(date);
		return name + format;
	}

	private ApplicationUser getUser(String userAuthorization)
	{
		return userService.getUserByAuthorization(userAuthorization);
	}
}
