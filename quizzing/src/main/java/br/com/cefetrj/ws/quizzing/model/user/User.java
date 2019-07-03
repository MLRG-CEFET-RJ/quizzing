package br.com.cefetrj.ws.quizzing.model.user;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private  String name;

	@NotBlank
	@Column(unique = true)
	private  String email;

	@NotBlank
	private  String password;

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

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof User))
		{
			return false;
		}
		User user = (User) o;
		return getId().equals(user.getId()) && getName().equals(user.getName()) && getEmail().equals(user.getEmail()) && getPassword().equals(user.getPassword());
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(getId(), getName(), getEmail(), getPassword());
	}

	@Override
	public String toString()
	{
		return "User{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + '}';
	}
}
