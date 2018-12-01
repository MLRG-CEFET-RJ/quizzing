package br.com.cefetrj.ws.quizzing.service.dao;

import br.com.cefetrj.ws.quizzing.model.question.Question;
import br.com.cefetrj.ws.quizzing.repository.OptionRepository;
import br.com.cefetrj.ws.quizzing.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;

@Service
public class QuestionService
{
	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	OptionRepository optionRepository;

	public List<Question> getQuestions(Long userId)
	{
		return null;
	}

	public Response createQuestion(Question question)
	{
		return null;
	}

	public Response updateQuestion(Question question)
	{
		return null;
	}

	public Response deleteQuestion(Question question)
	{
		return null;
	}

}
