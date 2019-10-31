package br.com.cefetrj.ws.quizzing.pojo;

public class OptionsDTO
{
	private String option;
	private char letter;
	private int id;
	private boolean checked;

	public OptionsDTO(String option, char letter, int id, boolean checked)
	{
		this.option = option;
		this.letter = letter;
		this.id = id;
		this.checked = checked;
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

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public boolean isChecked()
	{
		return checked;
	}

	public void setChecked(boolean checked)
	{
		this.checked = checked;
	}
}
