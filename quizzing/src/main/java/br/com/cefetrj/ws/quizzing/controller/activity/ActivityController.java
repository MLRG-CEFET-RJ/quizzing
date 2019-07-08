package br.com.cefetrj.ws.quizzing.controller.activity;

import br.com.cefetrj.ws.quizzing.model.activity.Activity;
import br.com.cefetrj.ws.quizzing.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@Path("/activity/{id}")
public class ActivityController
{
	@PathParam("id")
	String id;
	private ActivityService activityService;

	@Autowired
	public ActivityController(ActivityService activityService)
	{
		this.activityService = activityService;
	}

	@GET
	@Produces("text/plain")
	public String helloActivity()
	{
		return "Hello Activity";
	}

	@GET
	@Path("/activities")
	public List<Activity> getUserActivities()
	{
		return activityService.getUserQuestions(Long.parseLong(id));
	}

	@POST
	@Path("/new")
	public Response createActivity(String quizId)
	{
		return activityService.createActivity(Long.parseLong(id), Long.parseLong(quizId));
	}

	@DELETE
	@Path("/delete")
	public Response getUserActivities(Activity activity)
	{
		return activityService.deleteActivity(activity);
	}

	@POST
	@Path("/stop")
	public Response stopActivity(Activity activity)
	{
		return activityService.stopActivity(activity);
	}
}
