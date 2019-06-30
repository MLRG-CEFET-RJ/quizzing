package br.com.cefetrj.ws.quizzing.model.question;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.Arrays;

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
	private String answer;

	public QuestionSolr(Question question)
	{
		this.id = question.getId();
		this.question = question.getQuestion();
		this.options = question.getOptions();
		this.pic = question.getPic();
		this.type = question.getType();
		this.answer = question.getAnswer();
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

	public String getAnswer()
	{
		return answer;
	}

	public void setAnswer(String answer)
	{
		this.answer = answer;
	}

	@Override
	public String toString()
	{
		return "QuestionSolr{" + "id=" + id + ", question='" + question + '\'' + ", options='" + options + '\'' + ", pic=" + Arrays.toString(pic) + ", type='" + type + '\'' + ", answer='" + answer + '\'' + '}';
	}
}
