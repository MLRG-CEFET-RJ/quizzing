package br.com.cefetrj.ws.quizzing.repository.solrRepository;

import br.com.cefetrj.ws.quizzing.model.question.QuestionSolr;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
@Qualifier("solrQuestionRepository")
public interface SolrQuestionRepository extends SolrCrudRepository<QuestionSolr, String>
{
	List<QuestionSolr> findAllByQuestionOrTagsOrderByRatingDesc(String t1, String t2);

	List<QuestionSolr> findTop5ByTagsInOrderByRatingDesc(Collection query);
}
