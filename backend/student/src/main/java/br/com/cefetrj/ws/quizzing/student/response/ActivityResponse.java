package br.com.cefetrj.ws.quizzing.student.response;

import br.com.cefetrj.ws.quizzing.student.model.Activity;

public class ActivityResponse
{
	private QuizResponse quiz;

	public ActivityResponse(Activity activity)
	{
		this.quiz = new QuizResponse(activity.getQuiz());
	}
	public QuizResponse getQuiz()
	{
		return quiz;
	}

	public void setQuiz(QuizResponse quiz)
	{
		this.quiz = quiz;
	}
}
