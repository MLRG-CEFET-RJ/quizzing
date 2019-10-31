package br.com.cefetrj.ws.quizzing.pojo;

import br.com.cefetrj.ws.quizzing.model.tag.Tag;

import java.util.ArrayList;
import java.util.Set;

public class QuestionDTO
{
	private Long id;
	private String question;
	private String type;
	private String image;
	private ArrayList<OptionsDTO> options;
	private Set<Tag> tags;

	public QuestionDTO(Long id, String question, String type, String image, ArrayList<OptionsDTO> options, Set<Tag> tags)
	{
		this.id = id;
		this.question = question;
		this.type = type;
		this.image = image;
		this.options = options;
		this.tags = tags;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
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

	public Set<Tag> getTags()
	{
		return tags;
	}

	public void setTags(Set<Tag> tags)
	{
		this.tags = tags;
	}
}
