package br.com.cefetrj.ws.quizzing.service;

import br.com.cefetrj.sc.dominio.Prova;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Arrays;
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
			int[] ignoredPages = Arrays.stream(fileAndParamsObject.getString("ignoredPages").split(",")).mapToInt(Integer::parseInt).toArray();
			String[] ignoredWords = fileAndParamsObject.getString("ignoredWords").split(",");
			String questionSuffix = fileAndParamsObject.getString("questionSuffix");
			String questionPrefix = fileAndParamsObject.getString("questionPrefix");
			int maxQuestionsNumber = fileAndParamsObject.getInt("maxQuestionsNumber");
			String optionsIdentifier = fileAndParamsObject.getString("optionsIdentifier");

			Prova prova = new Prova("", "", 1, file, optionsIdentifier, questionSuffix, questionPrefix, ignoredPages, maxQuestionsNumber, ignoredWords);

			return Response.ok().entity(prova).build();
		}
		catch (JSONException e)
		{
			LOGGER.error("Ocorreu um erro ao obter o json com os parametros e o arquivo", e);

			return Response.status(500).entity("{\"message\": \"Internal Server Error\"}").build();
		}
	}


}
