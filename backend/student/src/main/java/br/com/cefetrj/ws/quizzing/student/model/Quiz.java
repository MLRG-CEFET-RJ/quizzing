package br.com.cefetrj.ws.quizzing.student.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "quiz")
@EntityListeners(AuditingEntityListener.class)
public class Quiz implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private ApplicationUser user;

	@Lob
	@NotBlank
	private String questions;

	public Quiz()
	{
	}

	public Quiz(@NotBlank String name, ApplicationUser user, @NotBlank String questions)
	{
		this.name = name;
		this.user = user;
		this.questions = questions;
	}

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

	public ApplicationUser getUser()
	{
		return user;
	}

	public void setUser(ApplicationUser user)
	{
		this.user = user;
	}

	public String getQuestions()
	{
		return questions;
	}

	public void setQuestions(String questions)
	{
		this.questions = questions;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Quiz))
		{
			return false;
		}
		Quiz quiz = (Quiz) o;
		return getId().equals(quiz.getId()) && getName().equals(quiz.getName()) && getUser().equals(quiz.getUser()) && getQuestions().equals(quiz.getQuestions());
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(getId(), getName(), getUser(), getQuestions());
	}

	@Override
	public String toString()
	{
		return "Quiz{" + "id=" + id + ", name='" + name + '\'' + ", user=" + user + '}';
	}
}
