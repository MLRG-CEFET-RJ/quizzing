package br.com.cefetrj.ws.quizzing.service;

import br.com.cefetrj.sc.dominio.Prova;
import br.com.cefetrj.ws.quizzing.pojo.Import;
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

	public Response getQuestionsFromFile(Import data)
	{
		byte[] file = Base64.getDecoder().decode(data.getFile());
		int[] ignoredPages = Arrays.stream(data.getIgnoredPages().replaceAll(" ", "").split(",")).mapToInt(Integer::parseInt).toArray();
		String[] ignoredWords = data.getIgnoredWords().split(",");
		String questionSuffix = data.getQuestionSuffix();
		String questionPrefix = data.getQuestionPrefix();
		int maxQuestionsNumber = data.getMaxQuestionsNumber();
		String optionsIdentifier = data.getOptionsIdentifier();

		Prova prova = new Prova("file.pdf", "quizzing", 2000, file, optionsIdentifier, questionSuffix, questionPrefix, ignoredPages, maxQuestionsNumber, ignoredWords);

		return Response.ok().entity(prova.getQuestoes()).build();
	}


}
