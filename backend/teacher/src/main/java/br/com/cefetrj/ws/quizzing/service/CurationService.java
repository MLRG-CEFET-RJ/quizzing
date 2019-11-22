package br.com.cefetrj.ws.quizzing.service;

import br.com.cefetrj.ws.quizzing.pojo.QuestionDTO;
import br.com.cefetrj.ws.quizzing.pojo.QuizDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;

@Service
public class CurationService
{
	@Autowired
	QuizService quizService;

	@Autowired
	QuestionService questionService;

	public Response createFromCuration(String userAuthorization, List<QuestionDTO> questions)
	{
		QuizDTO quizDTO = questionService.createQuestions(userAuthorization, questions);
		return quizService.createQuiz(userAuthorization, quizDTO);
	}
}
