package br.com.cefetrj.ws.quizzing.student.response;

import br.com.cefetrj.ws.quizzing.student.model.Activity;

import java.io.IOException;

public class ActivityResponse
{
	private Long id;
	private QuizResponse quiz;

	public ActivityResponse(Activity activity) throws IOException
	{
		this.quiz = new QuizResponse(activity.getQuiz());
		this.id = activity.getId();
	}
	public QuizResponse getQuiz()
	{
		return quiz;
	}

	public void setQuiz(QuizResponse quiz)
	{
		this.quiz = quiz;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}
}
