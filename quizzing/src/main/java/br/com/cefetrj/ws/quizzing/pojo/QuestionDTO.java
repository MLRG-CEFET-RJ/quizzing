package br.com.cefetrj.ws.quizzing.pojo;

import java.util.ArrayList;

public class QuestionDTO
{
	private String question;
	private String type;
	private String image;
	private String answer;
	private ArrayList<OptionsDTO> options;

	public QuestionDTO(String question, String type, String image, String answer, ArrayList<OptionsDTO> options)
	{
		this.question = question;
		this.type = type;
		this.image = image;
		this.answer = answer;
		this.options = options;
	}

	public String getQuestion()
	{
		return question;
	}

	public void setQuestion(String question)
	{
		this.question = question;
	}

	public String getImage()
	{
		return image;
	}

	public void setImage(String image)
	{
		this.image = image;
	}

	public String getAnswer()
	{
		return answer;
	}

	public void setAnswer(String answer)
	{
		this.answer = answer;
	}

	public ArrayList<OptionsDTO> getOptions()
	{
		return options;
	}

	public void setOptions(ArrayList<OptionsDTO> options)
	{
		this.options = options;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}
}
