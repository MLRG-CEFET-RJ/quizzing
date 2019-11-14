package br.com.cefetrj.ws.quizzing.student.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "answers")
@EntityListeners(AuditingEntityListener.class)
public class Answers implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String student;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "activity_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Activity activity;

	private String answer;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getStudent()
	{
		return student;
	}

	public void setStudent(String student)
	{
		this.student = student;
	}

	public Activity getActivity()
	{
		return activity;
	}

	public void setActivity(Activity activity)
	{
		this.activity = activity;
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
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Answers))
		{
			return false;
		}
		Answers answers = (Answers) o;
		return getId().equals(answers.getId()) && getStudent().equals(answers.getStudent()) && getActivity().equals(answers.getActivity()) && getAnswer().equals(answers.getAnswer());
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(getId(), getStudent(), getActivity(), getAnswer());
	}

	@Override
	public String toString()
	{
		return "{\"Answers\":{" + "                				\"id\":\"" + id + "\"" + ",                 				\"student\":\"" + student + "\"" + ",                 				\"activity\":" + activity + ",                 				\"answer\":\"" + answer + "\"" + "}}";
	}
}
