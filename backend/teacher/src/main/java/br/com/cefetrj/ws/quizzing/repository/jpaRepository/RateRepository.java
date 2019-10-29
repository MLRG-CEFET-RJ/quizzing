package br.com.cefetrj.ws.quizzing.repository.jpaRepository;

import br.com.cefetrj.ws.quizzing.model.question.Question;
import br.com.cefetrj.ws.quizzing.model.rating.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RateRepository extends JpaRepository<Rating, Long>
{
	List<Rating> findAllByQuestion(Question quesion);

	Optional<Rating> findByUserIdAndQuestionId(Long userId, Long questionId);
}
