package br.com.cefetrj.ws.quizzing.model.rating;

import br.com.cefetrj.ws.quizzing.constants.RatingEnum;
import br.com.cefetrj.ws.quizzing.model.question.Question;
import br.com.cefetrj.ws.quizzing.model.user.ApplicationUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ratings")
@EntityListeners(AuditingEntityListener.class)
public class Rating implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Enumerated
	private RatingEnum rating;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "question_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Question question;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private ApplicationUser user;

	public Rating()
	{
	}

	public Rating(ApplicationUser user, Question question)
	{
		this.user = user;
		this.question = question;
	}

	public Rating(@NotNull RatingEnum rating, Question question, ApplicationUser user)
	{
		this.rating = rating;
		this.question = question;
		this.user = user;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public RatingEnum getRating()
	{
		return rating;
	}

	public void setRating(RatingEnum rating)
	{
		this.rating = rating;
	}

	public Question getQuestion()
	{
		return question;
	}

	public void setQuestion(Question question)
	{
		this.question = question;
	}

	public ApplicationUser getUser()
	{
		return user;
	}

	public void setUser(ApplicationUser user)
	{
		this.user = user;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Rating))
		{
			return false;
		}
		Rating rating1 = (Rating) o;
		return getId().equals(rating1.getId()) && getRating() == rating1.getRating() && getQuestion().equals(rating1.getQuestion()) && getUser().equals(rating1.getUser());
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(getId(), getRating(), getQuestion(), getUser());
	}

	@Override
	public String toString()
	{
		return "Rating{" + "id=" + id + ", rating=" + rating + ", question=" + question + ", user=" + user + '}';
	}
}
