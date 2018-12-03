package br.com.cefetrj.ws.quizzing.model.question;

import br.com.cefetrj.ws.quizzing.model.option.Option;
import br.com.cefetrj.ws.quizzing.model.user.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "questions")
@EntityListeners(AuditingEntityListener.class)
public class Question implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String question;

	@NotBlank
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "option_id")
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private List<Option> options;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "option_id")
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private List<Option> correctOptions;

	@Lob
	@Column(name="pic")
	private byte[] pic;

	@NotBlank
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id")
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private User user;

	public Long getId()
	{
		return id;
	}

	public List<Option> getOptions()
	{
		return options;
	}

	public void setOptions(List<Option> options)
	{
		this.options = options;
	}

	public List<Option> getCorrectOptions()
	{
		return correctOptions;
	}

	public void setCorrectOptions(List<Option> correctOptions)
	{
		this.correctOptions = correctOptions;
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

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public byte[] getPic()
	{
		return pic;
	}

	public void setPic(byte[] pic)
	{
		this.pic = pic;
	}
}
