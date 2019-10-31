package br.com.cefetrj.ws.quizzing.model.question;

import br.com.cefetrj.ws.quizzing.model.tag.Tag;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@SolrDocument(collection = "question")
public class QuestionSolr
{

	@Id
	@Indexed
	private Long id;

	@Indexed
	private String question;

	@Indexed
	private String options;

	@Indexed
	private byte[] pic;

	@Indexed
	private String type;

	@Indexed
	private String tags = "";

	@Indexed
	private int rating;

	public QuestionSolr(Question question)
	{
		this.id = question.getId();
		this.question = question.getQuestion();
		this.options = question.getOptions();
		this.pic = question.getPic();
		this.type = question.getType();
		this.tags = getStringTags(question);
	}

	public QuestionSolr(Long id, String question, String options, byte[] pic, String type, String tags)
	{
		this.id = id;
		this.question = question;
		this.options = options;
		this.pic = pic;
		this.type = type;
		this.tags = tags;
	}

	public QuestionSolr()
	{
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

	public String getOptions()
	{
		return options;
	}

	public void setOptions(String options)
	{
		this.options = options;
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

	public String getTags()
	{
		return tags;
	}

	public void setTags(String tags)
	{
		this.tags = tags;
	}

	public int getRating()
	{
		return rating;
	}

	public void setRating(int rating)
	{
		this.rating = rating;
	}

	@Override
	public String toString()
	{
		return "QuestionSolr{" + "id=" + id + ", question='" + question + '\'' + ", options='" + options + '\'' + ", pic=" + Arrays.toString(pic) + ", type='" + type + '\'' + '}';
	}


	private String getStringTags(Question question)
	{
		Set<Tag> tags = question.getTags();
		List<String> tagsList = new ArrayList<>();
		for (Tag tag : tags)
		{
			tagsList.add(tag.getName());
		}
		return String.join(",", tagsList);
	}
}
