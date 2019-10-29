package br.com.cefetrj.ws.quizzing.repository.jpaRepository;

import br.com.cefetrj.ws.quizzing.model.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>
{
	Optional<Tag> findByName(String name);
}
