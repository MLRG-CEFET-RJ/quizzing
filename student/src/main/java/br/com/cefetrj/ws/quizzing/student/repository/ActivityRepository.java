package br.com.cefetrj.ws.quizzing.student.repository;

import br.com.cefetrj.ws.quizzing.student.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long>
{
	List<Activity> findAllByCode(String code);
}
