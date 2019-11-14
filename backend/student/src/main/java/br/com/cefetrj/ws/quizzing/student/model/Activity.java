package br.com.cefetrj.ws.quizzing.student.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "activities")
@EntityListeners(AuditingEntityListener.class)
public class Activity implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String code;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private ApplicationUser user;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "quiz_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Quiz quiz;

	private Boolean isEnded = false;

	@Lob
	private String results;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public ApplicationUser getUser()
	{
		return user;
	}

	public void setUser(ApplicationUser user)
	{
		this.user = user;
	}

	public Quiz getQuiz()
	{
		return quiz;
	}

	public void setQuiz(Quiz quiz)
	{
		this.quiz = quiz;
	}

	public Boolean getEnded()
	{
		return isEnded;
	}

	public void setEnded(Boolean ended)
	{
		isEnded = ended;
	}

	public String getResults()
	{
		return results;
	}

	public void setResults(String results)
	{
		this.results = results;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Activity))
		{
			return false;
		}
		Activity activity = (Activity) o;
		return getId().equals(activity.getId()) && getUser().equals(activity.getUser()) && getQuiz().equals(activity.getQuiz());
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(getId(), getUser(), getQuiz());
	}

	@Override
	public String toString()
	{
		return "{\"Activity\":{" + "                				\"id\":\"" + id + "\"" + ",                 				\"code\":\"" + code + "\"" + ",                 				\"user\":" + user + ",                 				\"quiz\":" + quiz + ",                 				\"isEnded\":\"" + isEnded + "\"" + ",                 				\"results\":\"" + results + "\"" + "}}";
	}
}
