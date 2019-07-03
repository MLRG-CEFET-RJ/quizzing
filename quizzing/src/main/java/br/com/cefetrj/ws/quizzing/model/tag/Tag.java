package br.com.cefetrj.ws.quizzing.model.tag;

import br.com.cefetrj.ws.quizzing.model.question.Question;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tags")
@EntityListeners(AuditingEntityListener.class)
public class Tag
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 100)
	@NaturalId
	private String name;
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "tags")
	@JsonIgnore
	private Set<Question> questions = new HashSet<>();

	public Tag(@NotBlank @Size(max = 100) String name)
	{
		this.name = name;
	}

	public Tag()
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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Set<Question> getQuestions()
	{
		return questions;
	}

	public void setQuestions(Set<Question> questions)
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
		if (!(o instanceof Tag))
		{
			return false;
		}
		Tag tag1 = (Tag) o;
		return getId().equals(tag1.getId()) && getName().equals(tag1.getName());
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(getId(), getName());
	}

	@Override
	public String toString()
	{
		return "Tag{" + "id=" + id + ", name='" + name + '\'' + '}';
	}
}
