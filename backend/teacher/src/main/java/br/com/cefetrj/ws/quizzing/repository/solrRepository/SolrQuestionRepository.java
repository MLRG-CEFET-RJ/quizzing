package br.com.cefetrj.ws.quizzing.repository.solrRepository;

import br.com.cefetrj.ws.quizzing.model.question.QuestionSolr;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("solrQuestionRepository")
public interface SolrQuestionRepository extends SolrCrudRepository<QuestionSolr, String>
{
	List<QuestionSolr> findAllByQuestion(String query);

	List<QuestionSolr> findAllByTags(String query);
}
