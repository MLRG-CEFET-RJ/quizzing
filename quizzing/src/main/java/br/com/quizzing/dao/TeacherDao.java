package br.com.quizzing.dao;

import br.com.quizzing.model.Teacher;

public class TeacherDao extends GenericDao<Teacher, Long>
{
	public TeacherDao() 
	{
		super(Teacher.class);
	} 
}
