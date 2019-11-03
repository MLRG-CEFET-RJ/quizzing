package br.com.cefetrj.ws.quizzing.model.quiz;

import br.com.cefetrj.ws.quizzing.model.tag.Tag;
import br.com.cefetrj.ws.quizzing.model.user.ApplicationUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "quiz_id")
	private Set<Tag> tags = new HashSet<>();

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

	public Set<Tag> getTags()
	{
		return tags;
	}

	public void setTags(Set<Tag> tags)
	{
		this.tags = tags;
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
