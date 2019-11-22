package br.com.cefetrj.ws.quizzing.controller.activity;

import br.com.cefetrj.ws.quizzing.model.activity.Activity;
import br.com.cefetrj.ws.quizzing.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@Path("/activity")
public class ActivityController
{
	private ActivityService activityService;

	@Autowired
	public ActivityController(ActivityService activityService)
	{
		this.activityService = activityService;
	}

	@GET
	@Path("/activities")
	public List<Activity> getUserActivities(@Context HttpHeaders httpheaders)
	{
		String authorizationHeader = httpheaders.getHeaderString("Authorization");
		return activityService.getUserActivities(authorizationHeader);
	}

	@POST
	@Path("/new/{id}")
	public Response createActivity(@Context HttpHeaders httpheaders, @PathParam("id") Long id)
	{
		String authorizationHeader = httpheaders.getHeaderString("Authorization");
		return activityService.createActivity(authorizationHeader, id);
	}

	@DELETE
	@Path("/delete/{id}")
	public Response getUserActivities(@Context HttpHeaders httpheaders, @PathParam("id") Long id)
	{
		String authorizationHeader = httpheaders.getHeaderString("Authorization");
		return activityService.deleteActivity(authorizationHeader, id);
	}

	@POST
	@Path("/stop/{id}")
	public Response stopActivity(@Context HttpHeaders httpheaders, @PathParam("id") Long id)
	{
		String authorizationHeader = httpheaders.getHeaderString("Authorization");
		return activityService.stopActivity(authorizationHeader, id);
	}

	@GET
	@Path("/{id}/results")
	public Response getResults(@Context HttpHeaders httpHeaders, @PathParam("id") Long id)
	{
		String authorizationHeader = httpHeaders.getHeaderString("Authorization");
		return activityService.getResults(authorizationHeader, id);
	}
}
