package br.com.cefetrj.ws.quizzing.student.response;

import br.com.cefetrj.ws.quizzing.student.model.Quiz;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

class QuizResponse
{
	private String name;

	private List<QuestionResponse> questions;

	QuizResponse(Quiz quiz) throws IOException
	{
		this.name = quiz.getName();

		final ObjectMapper mapper = new ObjectMapper();
		this.questions = mapper.readValue(quiz.getQuestions(), new TypeReference<List<QuestionResponse>>() {});
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<QuestionResponse> getQuestions()
	{
		return questions;
	}

	public void setQuestions(List<QuestionResponse> questions)
	{
		this.questions = questions;
	}
}
