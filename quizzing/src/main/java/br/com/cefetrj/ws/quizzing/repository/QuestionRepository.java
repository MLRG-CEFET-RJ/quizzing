package br.com.cefetrj.ws.quizzing.repository;

import br.com.cefetrj.ws.quizzing.model.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>
{
	@Query("select q from questions where q.user_id = :userId")
	List<Question> findAllUserQuestions(@Param("userId") Long userId);
}
