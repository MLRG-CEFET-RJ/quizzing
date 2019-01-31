package br.com.cefetrj.ws.quizzing.service.fileUpload;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Base64;

//TODO terminar service de upload de arquivo
@Service
public class FileUploadService
{
	private Logger LOGGER = LoggerFactory.getLogger(FileUploadService.class);

	public Response getQuestionsFromFile(String fileAndParams)
	{
		try
		{
			JSONObject fileAndParamsObject = new JSONObject(fileAndParams);

			byte[] file = Base64.getDecoder().decode(fileAndParamsObject.getString("file"));
			String ignoredPages = fileAndParamsObject.getString("ignoredPages");
			String ignoredWords = fileAndParamsObject.getString("ignoredWords");
			String questionSuffix = fileAndParamsObject.getString("questionSuffix");
			String questionPrefix = fileAndParamsObject.getString("questionPrefix");
			String maxQuestionsNumber = fileAndParamsObject.getString("maxQuestionsNumber");
			String optionsIdentifier = fileAndParamsObject.getString("optionsIdentifier");

		}
		catch (JSONException e)
		{
			LOGGER.error("Ocorreu um erro ao obter o json com os paramentros e o arquivo", e);

			return Response.status(500).entity("{\"message\": \"Internal Server Error\"}").build();
		}
		return null;
	}

	private void string64ToFile(String file64)
	{
		byte[] decode = Base64.getDecoder().decode(file64);
	}

}
