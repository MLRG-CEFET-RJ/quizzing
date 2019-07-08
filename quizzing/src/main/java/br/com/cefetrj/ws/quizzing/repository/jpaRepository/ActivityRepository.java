package br.com.cefetrj.ws.quizzing.repository.jpaRepository;

import br.com.cefetrj.ws.quizzing.model.activity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long>
{
	List<Activity> findAllByUserId(Long userId);

	List<Activity> findAllByCode(String code);
}
