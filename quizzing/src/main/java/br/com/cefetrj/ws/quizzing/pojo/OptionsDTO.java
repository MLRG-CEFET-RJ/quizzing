package br.com.cefetrj.ws.quizzing.pojo;

public class OptionsDTO
{
	private String option;
	private char letter;
	private String id;

	public OptionsDTO(String option, char letter, String id)
	{
		this.option = option;
		this.letter = letter;
		this.id = id;
	}

	public String getOption()
	{
		return option;
	}

	public void setOption(String option)
	{
		this.option = option;
	}

	public char getLetter()
	{
		return letter;
	}

	public void setLetter(char letter)
	{
		this.letter = letter;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}
}
