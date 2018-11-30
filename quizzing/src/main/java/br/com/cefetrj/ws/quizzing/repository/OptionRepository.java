package br.com.cefetrj.ws.quizzing.repository;

import br.com.cefetrj.ws.quizzing.model.option.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long>
{
}
