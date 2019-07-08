package br.com.cefetrj.ws.quizzing.pojo;

import java.util.ArrayList;

public class QuizDTO
{

	private Long id;
	private String name;
	private ArrayList<Id> ids;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public ArrayList<Id> getIds()
	{
		return ids;
	}

	public void setIds(ArrayList<Id> ids)
	{
		this.ids = ids;
	}
}
