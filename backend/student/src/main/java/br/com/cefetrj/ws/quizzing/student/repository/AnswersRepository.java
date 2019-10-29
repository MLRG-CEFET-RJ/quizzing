package br.com.cefetrj.ws.quizzing.student.repository;

import br.com.cefetrj.ws.quizzing.student.model.Activity;
import br.com.cefetrj.ws.quizzing.student.model.Answers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswersRepository extends JpaRepository<Answers, Long>
{
	List<Answers> findAllByStudentAndActivity(String student, Activity activity);
	List<Answers> findAllByActivity(Activity activity);
}
