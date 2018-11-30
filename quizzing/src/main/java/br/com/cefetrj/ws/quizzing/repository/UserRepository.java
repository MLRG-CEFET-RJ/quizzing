package br.com.cefetrj.ws.quizzing.repository;

import br.com.cefetrj.ws.quizzing.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
}
