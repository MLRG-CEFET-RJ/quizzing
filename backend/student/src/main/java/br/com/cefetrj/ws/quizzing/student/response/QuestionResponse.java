package br.com.cefetrj.ws.quizzing.student.response;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class QuestionResponse
{
	private String question;

	private byte[] pic;

	private String type;

	private String options;

	public String getQuestion()
	{
		return question;
	}

	public void setQuestion(String question)
	{
		this.question = question;
	}

	public byte[] getPic()
	{
		return pic;
	}

	public void setPic(byte[] pic)
	{
		this.pic = pic;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getOptions()
	{
		return options;
	}

	public void setOptions(String options)
	{
		this.options = options;
	}
}
