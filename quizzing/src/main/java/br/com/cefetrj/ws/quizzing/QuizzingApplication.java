package br.com.cefetrj.ws.quizzing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class QuizzingApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(QuizzingApplication.class, args);
	}
}
