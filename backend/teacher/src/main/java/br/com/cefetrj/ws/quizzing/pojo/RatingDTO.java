package br.com.cefetrj.ws.quizzing.pojo;

import br.com.cefetrj.ws.quizzing.constants.RatingEnum;

public class RatingDTO
{
	private Long questionId;
	private RatingEnum rating;

	public Long getQuestionId()
	{
		return questionId;
	}

	public void setQuestionId(Long questionId)
	{
		this.questionId = questionId;
	}

	public RatingEnum getRating()
	{
		return rating;
	}

	public void setRating(RatingEnum rating)
	{
		this.rating = rating;
	}
}
