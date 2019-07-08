package br.com.cefetrj.ws.quizzing.service;

import br.com.cefetrj.ws.quizzing.model.activity.Activity;
import br.com.cefetrj.ws.quizzing.model.quiz.Quiz;
import br.com.cefetrj.ws.quizzing.model.user.ApplicationUser;
import br.com.cefetrj.ws.quizzing.repository.jpaRepository.ActivityRepository;
import br.com.cefetrj.ws.quizzing.repository.jpaRepository.QuizRespository;
import br.com.cefetrj.ws.quizzing.repository.jpaRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.OK;

@Service
public class ActivityService
{
	private final ActivityRepository activityRepository;

	private final UserRepository userRepository;

	private final QuizRespository quizRespository;

	@Autowired
	public ActivityService(ActivityRepository activityRepository, UserRepository userRepository, QuizRespository quizRespository)
	{
		this.activityRepository = activityRepository;
		this.userRepository = userRepository;
		this.quizRespository = quizRespository;
	}

	public List<Activity> getUserQuestions(Long userId)
	{
		return activityRepository.findAllByUserId(userId);
	}

	public Response createActivity(Long userId, Long quizID)
	{
		ApplicationUser user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("ApplicationUser not found"));
		Quiz quiz = quizRespository.findById(quizID).orElseThrow(() -> new NotFoundException("Quiz not found"));

		Activity activity = new Activity(user, quiz);
		activity.setCode(generateCode());
		Activity createdActivity = activityRepository.save(activity);
		return Response.status(CREATED).entity(createdActivity).build();
	}

	public Response deleteActivity(Activity activity)
	{
		Activity activityToBeDeleted = activityRepository.findById(activity.getId()).orElseThrow(() -> new NotFoundException("Activity not found"));
		activityRepository.delete(activityToBeDeleted);

		return Response.status(OK).entity("{\"message\": \"Activity deleted successfully\"}").build();
	}

	public Response stopActivity(Activity activity)
	{
		Activity activityToBeStoped = activityRepository.findById(activity.getId()).orElseThrow(() -> new NotFoundException("Activity not found"));
		activityToBeStoped.setEnded(true);
		activityRepository.save(activityToBeStoped);

		return Response.status(OK).entity("{\"message\": \"Activity stoped successfully\"}").build();
	}

	private String generateCode()
	{
		//TODO: Implementar geração do código
		return "";
	}
}
