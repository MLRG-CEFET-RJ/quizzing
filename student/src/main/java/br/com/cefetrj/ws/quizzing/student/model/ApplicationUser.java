package br.com.cefetrj.ws.quizzing.student.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class ApplicationUser implements Serializable
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private  String name;

	@NotBlank
	@Column(unique = true)
	private String username;

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

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
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
		if (!(o instanceof ApplicationUser))
		{
			return false;
		}
		ApplicationUser user = (ApplicationUser) o;
		return getId().equals(user.getId()) && getName().equals(user.getName()) && getUsername().equals(user.getUsername()) && getPassword().equals(user.getPassword());
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(getId(), getName(), getUsername(), getPassword());
	}

	@Override
	public String toString()
	{
		return "ApplicationUser{" + "id=" + id + ", name='" + name + '\'' + ", username='" + username + '\'' + '}';
	}
}
