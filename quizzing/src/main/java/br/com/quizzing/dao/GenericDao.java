package br.com.quizzing.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.Valid;


public abstract class GenericDao<T, I extends Serializable> 
{
	   @Inject
	   protected EntityManager entityManager;

	   private Class<T> persistedClass;

	   protected GenericDao() 
	   {
	   }

	   protected GenericDao(Class<T> persistedClass) 
	   {
	       this();
	       this.persistedClass = persistedClass;
	   }

	   public T saveAndUpdate(@Valid T entity) 
	   {
	       EntityTransaction t = entityManager.getTransaction();
	       t.begin();
	       entityManager.persist(entity);
	       entityManager.flush();
	       t.commit();
	       return entity;
	   }

	   public void remove(T entity) 
	   {
	       EntityTransaction tx = entityManager.getTransaction();
	       tx.begin();
	       T mergedEntity = entityManager.merge(entity);
	       entityManager.remove(mergedEntity);
	       entityManager.flush();
	       tx.commit();
	   }

	   public List<T> getList() 
	   {
	       CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	       CriteriaQuery<T> query = builder.createQuery(persistedClass);
	       query.from(persistedClass);
	       return entityManager.createQuery(query).getResultList();
	   }

	   public T getById(I id) 
	   {
	       return entityManager.find(persistedClass, id);
	   }
}