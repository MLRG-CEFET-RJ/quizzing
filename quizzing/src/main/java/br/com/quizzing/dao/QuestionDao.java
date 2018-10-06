package br.com.quizzing.dao;

import br.com.quizzing.model.Question;

public class QuestionDao extends GenericDao<Question, Long>
{
	public QuestionDao() 
	{
		super(Question.class);
	} 
}
