package com.myproject.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.LockModeType;

public interface BaseDao<T, ID extends Serializable> {
	/**
	 * 查找实体对象
	 * 
	 * @param id
	 *            ID
	 * @return 实体对象，若不存在则返回null
	 */
	T find(ID id);

	/**
	 * 查找实体对象
	 * 
	 * @param id
	 *            ID
	 * @param lockModeType
	 *            锁定方式
	 * @return 实体对象，若不存在则返回null
	 */
	T find(ID id, LockModeType lockModeType);

	/**
	 * 持久化实体对象
	 * 
	 * @param entity
	 *            实体对象
	 */
	void persist(T entity);

	/**
	 * 合并实体对象
	 * 
	 * @param entity
	 *            实体对象
	 * @return 实体对象
	 */
	T merge(T entity);
	T save(T entity);
	void remove(ID id);
	/**
	 * 移除实体对象
	 * 
	 * @param entity
	 *            实体对象
	 */
	void remove(T entity);

	/**
	 * 刷新实体对象
	 * 
	 * @param entity
	 *            实体对象
	 */
	void refresh(T entity);

	/**
	 * 刷新实体对象
	 * 
	 * @param entity
	 *            实体对象
	 * @param lockModeType
	 *            锁定方式
	 */
	void refresh(T entity, LockModeType lockModeType);

	/**
	 * 获取实体对象ID
	 * 
	 * @param entity
	 *            实体对象
	 * @return 实体对象ID
	 */
	ID getIdentifier(T entity);

	/**
	 * 判断是否为托管状态
	 * 
	 * @param entity
	 *            实体对象
	 * @return 是否为托管状态
	 */
	boolean isManaged(T entity);

	/**
	 * 设置为游离状态
	 * 
	 * @param entity
	 *            实体对象
	 */
	void detach(T entity);

	/**
	 * 锁定实体对象
	 * 
	 * @param entity
	 *            实体对象
	 * @param lockModeType
	 *            锁定方式
	 */
	void lock(T entity, LockModeType lockModeType);

	/**
	 * 清除缓存
	 */
	void clear();

	/**
	 * 同步数据
	 */
	void flush();

	// -----------SQL PART------------
	/**
	 * 查询对象数量
	 * 
	 * @param sql
	 *            查询语句
	 * @return 对象数量
	 */
	int countBySQL(String sql);
	
	Object findSingleBySQL(String sql);
	/**
	 * 查找实体对象
	 * 
	 * @param sql
	 *            查询语句
	 * @param resultClass
	 *            结果实体类
	 * @return 实体对象，若不存在则返回null
	 */
	<E> E findBySQL(String sql, Class<E> resultClass);

	/**
	 * 无实体结果查询.
	 * 
	 * @param sql
	 *            查询语句
	 * @return 结果对象，若不存在则返回null
	 */
	List<?> findListBySQL(String sql);

	/**
	 * 查找实体对象集合
	 * 
	 * @param sql
	 *            查询语句
	 * @param resultClass
	 *            结果实体类
	 * @return 实体对象集合
	 */
	<E> List<E> findListBySQL(String sql, Class<E> resultClass);

	/**
	 * 查找无实体对象分页
	 * 
	 * @param sql
	 *            查询语句
	 * @param resultClass
	 *            结果实体类
	 * @return 实体对象分页
	 */
	List<?> findPageBySQL(String sql, int page, int pagesize);

	/**
	 * 查找实体对象分页
	 * 
	 * @param sql
	 *            查询语句
	 * @param resultClass
	 *            结果实体类
	 * @return 实体对象分页
	 */
	<E> List<E> findPageBySQL(String sql, int page, int pagesize,
			Class<E> resultClass);
	/**
	 * 执行(新增、更新、删除、存储过程)SQL语句.
	 * 
	 * @param sql
	 *            存储过程
	 *            
	 * @return 整型
	 */
	public int executeBySQL(String sql);
	/**
	 * 执行(新增、更新、删除、存储过程)SQL语句.
	 * 
	 * @param sql
	 *            存储过程
	 * @param map
	 *            键值对
	 * @return 整型
	 */
	int executeBySQL(String sql, Map<String, Object>params);
}
