package br.com.cefetrj.ws.quizzing.student.response;

import br.com.cefetrj.ws.quizzing.student.model.Answers;

public class ResultsResponse
{
	private Answers answers;
	private String questions;

	public ResultsResponse(Answers answers, String questions)
	{
		this.answers = answers;
		this.questions = questions;
	}

	public Answers getAnswers()
	{
		return answers;
	}

	public void setAnswers(Answers answers)
	{
		this.answers = answers;
	}

	public String getQuestions()
	{
		return questions;
	}

	public void setQuestions(String questions)
	{
		this.questions = questions;
	}
}
