package br.com.cefetrj.ws.quizzing.model.question;

import org.json.JSONArray;

import java.util.ArrayList;

public class QuestionDTO
{
	private String question;
	private String type;
	private String image;
	private String answare;
	private ArrayList<OptionsDTO> options;

	public QuestionDTO(String question, String type, String image, String answare, ArrayList<OptionsDTO> options)
	{
		this.question = question;
		this.type = type;
		this.image = image;
		this.answare = answare;
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

	public String getAnsware()
	{
		return answare;
	}

	public void setAnsware(String answare)
	{
		this.answare = answare;
	}

	public String getOptions()
	{
		return new JSONArray(this.options).toString();
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
