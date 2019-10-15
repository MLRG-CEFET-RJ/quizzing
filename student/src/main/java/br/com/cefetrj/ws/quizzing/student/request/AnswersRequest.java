package br.com.cefetrj.ws.quizzing.student.request;

import java.util.List;

public class AnswersRequest
{
	private List<AnswerResquest> answers;

	public List<AnswerResquest> getAnswers()
	{
		return answers;
	}

	public void setAnswers(List<AnswerResquest> answers)
	{
		this.answers = answers;
	}
}
