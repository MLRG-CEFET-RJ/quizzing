package br.com.cefetrj.ws.quizzing.repository.jpaRepository;

import br.com.cefetrj.ws.quizzing.model.user.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long>
{
	Optional<ApplicationUser> findByUsername(String email);
}
