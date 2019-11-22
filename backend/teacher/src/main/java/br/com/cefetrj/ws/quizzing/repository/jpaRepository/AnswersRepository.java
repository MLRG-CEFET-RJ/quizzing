package br.com.cefetrj.ws.quizzing.repository.jpaRepository;

import br.com.cefetrj.ws.quizzing.model.activity.Activity;
import br.com.cefetrj.ws.quizzing.model.activity.Answers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswersRepository extends JpaRepository<Answers, Long>
{
	List<Answers> findAllByStudentAndActivity(String student, Activity activity);
	List<Answers> findAllByActivity(Activity activity);
}
