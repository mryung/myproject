/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.myproject.dao.imp;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.springframework.util.Assert;

import com.myproject.dao.BaseDao;

/**
 * Dao - 基类
 * 
 * @author INV TEAM
 * @version 1.0
 */ 
public abstract class BaseDaoImpl<T, ID extends Serializable> implements BaseDao<T, ID> {
	private Class<T> entityClass;

	/** 别名数 */
	private static volatile long aliasCount = 0;

	@PersistenceContext
	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		Type type = getClass().getGenericSuperclass();
		Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
		entityClass = (Class<T>) parameterizedType[0];
	}

	public T find(ID id) {
		if (id != null) {
			return entityManager.find(entityClass, id);
		}
		return null;
	}

	public T find(ID id, LockModeType lockModeType) {
		if (id != null) {
			if (lockModeType != null) {
				return entityManager.find(entityClass, id, lockModeType);
			} else {
				return entityManager.find(entityClass, id);
			}
		}
		return null;
	}

	public void persist(T entity) {
		Assert.notNull(entity);
		entityManager.persist(entity);
	}

	public T merge(T entity) {
		Assert.notNull(entity);
		return entityManager.merge(entity);
	}
	public T save(T entity) {
		ID id=this.getIdentifier(entity);
		if(id==null){
			this.persist(entity);
		}else{
			this.merge(entity);
		}
		this.flush();
		return entity;
	}
	public void remove(ID id) {
		Object entity = this.find(id);
		if (entity != null) {
			entityManager.remove(entity);
		}
	}
	
	public void remove(T entity) {
		if (entity != null) {
			entityManager.remove(entity);
		}
	}
	public void refresh(T entity) {
		if (entity != null) {
			entityManager.refresh(entity);
		}
	}

	public void refresh(T entity, LockModeType lockModeType) {
		if (entity != null) {
			if (lockModeType != null) {
				entityManager.refresh(entity, lockModeType);
			} else {
				entityManager.refresh(entity);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public ID getIdentifier(T entity) {
		Assert.notNull(entity);
		return (ID) entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
	}

	public boolean isManaged(T entity) {
		return entityManager.contains(entity);
	}

	public void detach(T entity) {
		entityManager.detach(entity);
	}

	public void lock(T entity, LockModeType lockModeType) {
		if (entity != null && lockModeType != null) {
			entityManager.lock(entity, lockModeType);
		}
	}

	public void clear() {
		entityManager.clear();
	}

	public void flush() {
		entityManager.flush();
	}

	private synchronized String getAlias(Selection<?> selection) {
		if (selection != null) {
			String alias = selection.getAlias();
			if (alias == null) {
				if (aliasCount >= 1000) {
					aliasCount = 0;
				}
				alias = "ebpGeneratedAlias" + aliasCount++;
				selection.alias(alias);
			}
			return alias;
		}
		return null;
	}

	private Root<T> getRoot(CriteriaQuery<T> criteriaQuery) {
		if (criteriaQuery != null) {
			return getRoot(criteriaQuery, criteriaQuery.getResultType());
		}
		return null;
	}

	private Root<T> getRoot(CriteriaQuery<?> criteriaQuery, Class<T> clazz) {
		if (criteriaQuery != null && criteriaQuery.getRoots() != null && clazz != null) {
			for (Root<?> root : criteriaQuery.getRoots()) {
				if (clazz.equals(root.getJavaType())) {
					return (Root<T>) root.as(clazz);
				}
			}
		}
		return null;
	}

	private void copyJoins(From<?, ?> from, From<?, ?> to) {
		for (Join<?, ?> join : from.getJoins()) {
			Join<?, ?> toJoin = to.join(join.getAttribute().getName(), join.getJoinType());
			toJoin.alias(getAlias(join));
			copyJoins(join, toJoin);
		}
		for (Fetch<?, ?> fetch : from.getFetches()) {
			Fetch<?, ?> toFetch = to.fetch(fetch.getAttribute().getName());
			copyFetches(fetch, toFetch);
		}
	}

	private void copyFetches(Fetch<?, ?> from, Fetch<?, ?> to) {
		for (Fetch<?, ?> fetch : from.getFetches()) {
			Fetch<?, ?> toFetch = to.fetch(fetch.getAttribute().getName());
			copyFetches(fetch, toFetch);
		}
	}
    //-------------SQL PART---------------
	/**
	 * Pack list.
	 * 
	 * @param <E> element type
	 * @param list
	 * @return the list
	 */
	protected <E> List<E> packList(List<E> list) {
		return (list == null ? new ArrayList<E>() : list);
	}
	public int countBySQL(String sql) {
		Object object = entityManager.createNativeQuery(sql).getSingleResult();
		return Integer.parseInt(object.toString());
	}
	public Object findSingleBySQL(String sql) {
	    Object object = entityManager.createNativeQuery(sql).getSingleResult();
		return object;
	}
	@SuppressWarnings("unchecked")
	public <E> E findBySQL(String sql, Class <E> resultClass) {
		List<E>list = packList(entityManager.createNativeQuery(sql, resultClass)
				.getResultList());
		if(list==null || list.isEmpty()) return null;
		return list.get(0);
	}
	/**
	 * 无实体结果查询.
	 *
	 * @param sql the sql
	 * @param params the params
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<?> findListBySQL(String sql) {
		Query query = entityManager.createNativeQuery(sql);
		return packList(query.getResultList());
	}
	@SuppressWarnings("unchecked")
	public <E> List<E> findListBySQL(String sql, Class<E>resultClass) {
		return packList(entityManager.createNativeQuery(sql, resultClass)
				.getResultList());
	}
	@SuppressWarnings("unchecked")
	public List<?> findPageBySQL(String sql, int page, int pagesize) {
		int firstResult = (page - 1) * pagesize;
		return packList(entityManager.createNativeQuery(sql)
				.setFirstResult(firstResult).setMaxResults(pagesize)
				.getResultList());
	}
	@SuppressWarnings("unchecked")
	public <E> List<E> findPageBySQL(String sql, int page,
			int pagesize, Class<E>resultClass) {
		int firstResult = (page - 1) * pagesize;
		return packList(entityManager.createNativeQuery(sql, resultClass)
				.setFirstResult(firstResult).setMaxResults(pagesize)
				.getResultList());
	}
	public int executeBySQL(String sql) {
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
		return 1;
	}
	public int executeBySQL(String sql, Map<String, Object>params) {
		Query query = entityManager.createNativeQuery(sql);
		if (params != null)
			for (Entry<String, Object> item : params.entrySet()) {
				query.setParameter(item.getKey(), item.getValue());
			}
		return query.executeUpdate();
	}

}