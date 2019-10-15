package br.com.cefetrj.ws.quizzing.controller.fileupload;

import br.com.cefetrj.ws.quizzing.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@RestController
@Path("/upload/file")
@Produces("application/json")
public class FileUploadController
{
	private final FileUploadService fileUploadService;

	@Autowired
	public FileUploadController(FileUploadService fileUploadService)
	{
		this.fileUploadService = fileUploadService;
	}

	@POST
	public Response uploadFile(String fileAndParams)
	{
		return fileUploadService.getQuestionsFromFile(fileAndParams);
	}

}
