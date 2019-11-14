package br.com.cefetrj.ws.quizzing.student.service;

import br.com.cefetrj.ws.quizzing.student.model.Activity;
import br.com.cefetrj.ws.quizzing.student.model.Answers;
import br.com.cefetrj.ws.quizzing.student.repository.ActivityRepository;
import br.com.cefetrj.ws.quizzing.student.repository.AnswersRepository;
import br.com.cefetrj.ws.quizzing.student.request.AnswersRequest;
import br.com.cefetrj.ws.quizzing.student.response.ActivityResponse;
import br.com.cefetrj.ws.quizzing.student.response.ResultsResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService
{
	@Autowired
	ActivityRepository activityRepository;

	@Autowired
	AnswersRepository answersRepository;

	public Response joinActivity(String code) throws IOException
	{
		final List<Activity> activities = activityRepository.findAllByCode(code);
		final Optional<Activity> activity = activities.stream().filter(a -> !a.getEnded()).findFirst();
		if (activity.isPresent())
		{
			final ActivityResponse response = new ActivityResponse(activity.get());
			return  Response.ok().entity(response).build();
		}
		else
		{
			return Response.status(404).entity("{\"message\": \"This activity os no longer available\"}").build();
		}
	}

	public Response submit(String name, Long id, AnswersRequest answers)
	{
		final Answers userAnswers = new Answers();
		userAnswers.setStudent(name);
		final Activity activity = activityRepository.findById(id).orElseThrow(NotFoundException::new);
		userAnswers.setActivity(activity);
		final ObjectMapper mapper = new ObjectMapper();
		try
		{
			userAnswers.setAnswer(mapper.writeValueAsString(answers));
		}
		catch (JsonProcessingException e)
		{
			return Response.serverError().entity("{\"message\": \"An error occurred, please try again \"}").build();
		}
		answersRepository.save(userAnswers);
		return Response.ok().entity(computeResults(activity, userAnswers)).build();
	}

	private ResultsResponse computeResults(Activity activity, Answers userAnswers)
	{
		final String questions = activity.getQuiz().getQuestions();
		return new ResultsResponse(userAnswers, questions);
	}
}
