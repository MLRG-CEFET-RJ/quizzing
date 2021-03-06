package br.com.cefetrj.ws.quizzing.model.activity;

import br.com.cefetrj.ws.quizzing.model.quiz.Quiz;
import br.com.cefetrj.ws.quizzing.model.user.ApplicationUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
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

	@Temporal(TemporalType.DATE)
	private Date createdDate;

	@Lob
	private String results;

	public Activity()
	{
	}

	public Activity(ApplicationUser user, Quiz quiz)
	{
		this.user = user;
		this.quiz = quiz;
		this.createdDate = new Date();
	}

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

	public Date getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
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
		return getId().equals(activity.getId()) && getCode().equals(activity.getCode()) && getUser().equals(activity.getUser()) && getQuiz().equals(activity.getQuiz()) && Objects.equals(isEnded, activity.isEnded) && Objects.equals(getResults(), activity.getResults());
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(getId(), getCode(), getUser(), getQuiz(), isEnded, getResults());
	}

	@Override
	public String toString()
	{
		return "Activity{" + "id=" + id + ", code='" + code + '\'' + ", user=" + user + ", quiz=" + quiz + ", isEnded=" + isEnded + ", createdDate=" + createdDate + ", results='" + results + '\'' + '}';
	}
}
