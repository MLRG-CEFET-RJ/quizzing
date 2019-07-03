package br.com.cefetrj.ws.quizzing.model.quiz;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

	@NotNull
	private Long userId;

	@Lob
	@NotBlank
	private String questions;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
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
		return getId().equals(quiz.getId()) && getUserId().equals(quiz.getUserId()) && getQuestions().equals(quiz.getQuestions());
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(getId(), getUserId(), getQuestions());
	}

	@Override
	public String toString()
	{
		return "Quiz{" + "id=" + id + ", userId=" + userId + ", questions='" + questions + '\'' + '}';
	}
}
