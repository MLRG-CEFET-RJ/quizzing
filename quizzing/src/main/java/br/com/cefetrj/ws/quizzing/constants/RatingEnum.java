package br.com.cefetrj.ws.quizzing.constants;

public enum RatingEnum
{
	ONE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5);

	private int value;

	RatingEnum(int value)
	{
		this.value = value;
	}

	public int getValue()
	{
		return value;
	}
}
