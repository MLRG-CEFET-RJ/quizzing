package br.com.cefetrj.ws.quizzing.service;

import br.com.cefetrj.ws.quizzing.model.activity.Activity;
import br.com.cefetrj.ws.quizzing.model.quiz.Quiz;
import br.com.cefetrj.ws.quizzing.model.user.ApplicationUser;
import br.com.cefetrj.ws.quizzing.repository.jpaRepository.ActivityRepository;
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

	private final UserService userService;

	@Autowired
	public ActivityService(ActivityRepository activityRepository, QuizRespository quizRespository, UserService userService)
	{
		this.activityRepository = activityRepository;
		this.quizRespository = quizRespository;
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
		Activity activityToBeStoped = activityRepository.findById(id).orElseThrow(() -> new NotFoundException("Activity not found"));
		if (activityToBeStoped.getUser().equals(user))
		{
			activityToBeStoped.setEnded(true);
			activityRepository.save(activityToBeStoped);

			return Response.status(OK).entity("{\"message\": \"Activity stoped successfully\"}").build();
		}
		else
		{
			return Response.status(UNAUTHORIZED).entity("{\"message\": \"This user can not update this question\"}").build();
		}
	}

	private String generateCode(Quiz quiz)
	{
		DateFormat dateFormat = new SimpleDateFormat("ddMMHHmm");
		String name = quiz.getName();
		Date date = new Date();
		String format = dateFormat.format(date);
		return name + format;
	}

	private ApplicationUser getUser(String userAuthorization)
	{
		return userService.getUserByAuthorization(userAuthorization);
	}
}
