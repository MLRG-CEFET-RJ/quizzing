package br.com.cefetrj.ws.quizzing.controller.curation;

import br.com.cefetrj.ws.quizzing.pojo.QuestionDTO;
import br.com.cefetrj.ws.quizzing.service.CurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@Path("/curation")
@Produces("application/json")
public class CurationController
{
	private final CurationService curationService;

	@Autowired
	public CurationController(CurationService curationService)
	{
		this.curationService = curationService;
	}

	@POST
	public Response createFromFile(@Context HttpHeaders httpheaders, @Valid List<QuestionDTO> questions)
	{
		String authorizationHeader = httpheaders.getHeaderString("Authorization");
		return curationService.createFromCuration(authorizationHeader, questions);
	}
}
