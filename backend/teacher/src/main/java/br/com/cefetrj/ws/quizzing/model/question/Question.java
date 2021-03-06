package br.com.cefetrj.ws.quizzing.model.question;

import br.com.cefetrj.ws.quizzing.model.tag.Tag;
import br.com.cefetrj.ws.quizzing.model.user.ApplicationUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "questions")
@EntityListeners(AuditingEntityListener.class)
public class Question implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Lob
	private String question;

	@Lob
	private String options;

	private String answer;

	@Lob
	@Column(name = "pic")
	@JsonProperty("image")
	private byte[] pic;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private ApplicationUser user;

	@NotBlank
	private String type;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.MERGE})
	@JoinTable(name = "question_tags", joinColumns = {@JoinColumn(name = "question_id")}, inverseJoinColumns = {@JoinColumn(name = "tag_id")})
	private Set<Tag> tags = new HashSet<>();

	public Question(@NotBlank String question, String options, String answer, byte[] pic, ApplicationUser user, @NotBlank String type, Set<Tag> tags)
	{
		this.question = question;
		this.options = options;
		this.answer = answer;
		this.pic = pic;
		this.user = user;
		this.type = type;
		this.tags = tags;
	}

	public Question()
	{
	}

	public Long getId()
	{
		return id;
	}

	public String getOptions()
	{
		return options;
	}

	public void setOptions(String options)
	{
		this.options = options;
	}

	public String getAnswer()
	{
		return answer;
	}

	public void setAnswer(String answer)
	{
		this.answer = answer;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public ApplicationUser getUser()
	{
		return user;
	}

	public void setUser(ApplicationUser user)
	{
		this.user = user;
	}

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
		if (!(o instanceof Question))
		{
			return false;
		}
		Question question1 = (Question) o;
		return getId().equals(question1.getId()) && getQuestion().equals(question1.getQuestion()) && Objects.equals(getOptions(), question1.getOptions()) && Arrays.equals(getPic(), question1.getPic()) && getUser().equals(question1.getUser()) && getType().equals(question1.getType());
	}

	@Override
	public int hashCode()
	{
		int result = Objects.hash(getId(), getQuestion(), getOptions(), getUser(), getType());
		result = 31 * result + Arrays.hashCode(getPic());
		return result;
	}

	@Override
	public String toString()
	{
		return "Question{" + "id=" + id + ", question='" + question + '\'' + ", options='" + options + '\'' + ", pic=" + Arrays.toString(pic) + ", user=" + user + ", type='" + type + '\'' + '}';
	}
}
