package com.kingtech.common.dynamicquery;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.hql.internal.ast.QueryTranslatorImpl;
import org.hibernate.hql.spi.QueryTranslator;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;

import com.kingtech.dao.entity.base.BaseEntity;

/**
 * 动态jpql/nativesql查询的实现类
 * 
 * @author wanghui
 * @lastModified wanghui 2014-9-11 10:26:22
 */
@Repository
public class DynamicQueryImpl implements DynamicQuery {

	@PersistenceContext
	private EntityManager em;
	
	public EntityManager getEntityManager(){
		return em;
	}
	
	Logger logger = LoggerFactory.getLogger(DynamicQueryImpl.class);
	
	/**
	 * 从EntityManager获取Hibernate的Session
	 * 下面所有方式的实现都基于Hibernate
	 */
	private Session getHibernateSession() { 
		return em.unwrap(Session.class);
	}
	
	/**
	 * 获取Hibernate的SessionFactory对象
	 * @return
	 */
	private SessionFactory getHibernateSessionFactory() {
		return getHibernateSession().getSessionFactory();
	}
	
	// -====================================== jpql查询 ======================================-

	@Override
	public <T> T querySingleResult(Class<T> resultClass, String jpqlName, Map paramsMap) {
		return createTypedQueryByQueryName(resultClass, jpqlName, paramsMap).getSingleResult();
	}
	
	@Override
	public <T> List<T> query(Class<T> resultClass, String jpqlName,  Map paramsMap) {
		return createTypedQueryByQueryName(resultClass, jpqlName, paramsMap).getResultList();
	}

	@Override
	public <T> List<T> queryPagingList(Class<T> resultClass, Pageable pageable, String jpqlName,  Map paramsMap) {
		Integer pageNumber = pageable.getPageNumber();
		Integer pageSize = pageable.getPageSize();
		Integer startPosition = pageNumber * pageSize;
		return createTypedQueryByQueryName(resultClass, jpqlName, paramsMap).setFirstResult(startPosition).setMaxResults(pageSize).getResultList();
	}
	//queryCount 生成sql有问题.
    @Override
    public <T> Page<T> query(Class<T> resultClass, Pageable pageable, String qlString, Object... params) {
        List<T> rows = queryPagingListByJpql(resultClass, pageable, qlString, params);
        Long total = queryCount(qlString, params);
        return new PageImpl<T>(rows, pageable, total);
    }
    
    private <T> List<T> queryPagingListByJpql(Class<T> resultClass, Pageable pageable, String qlString, Object... params) {
        Integer pageNumber = pageable.getPageNumber();
        Integer pageSize = pageable.getPageSize();
        Integer startPosition = pageNumber * pageSize;
        return createTypedQueryByJpql(resultClass, qlString, params).setFirstResult(startPosition).setMaxResults(pageSize).getResultList();
    }
    
    private <T> TypedQuery<T> createTypedQueryByJpql(Class<T> resultClass, String qlString, Object... params) {
        TypedQuery<T> q = em.createQuery(qlString, resultClass);
        for (int i = 0; i < params.length; i++) {
            q.setParameter(i + 1, params[i]); // 与Hiberante不同,jpa query从位置1开始
        }
        return q;
    }
	
	@Override
	public Long queryCount(String jpql, Object... params) {
		jpql = StringUtils.substringBefore(jpql, "order by"); // 去掉order by, 提升执行效率
		
		// 去重和分组只能使用NativeSQL统计查询
		if (jpql.contains("distinct") || jpql.contains("group by")) {
			String countSql = generateCountSql(jpql);
			Object count = createNativeQuery(countSql, params).getSingleResult();
			return ((Number) count).longValue();
		} else { // 使用jpql统计查询
			String countJpql = generateCountJpql(jpql);
			return (Long) createQueryByJpql(countJpql, params).getSingleResult();
		}
	}
	
	@Override
	public int executeUpdate(String jpql, Object... params) {
		return createQueryByJpql(jpql, params).executeUpdate();
	}
	
	@Override
	public int executeUpdate(String jpql, Map<String, Object> paramMap) {
		return createQueryByJpql(jpql, paramMap).executeUpdate();
	}
	
	@Override
	public int executeNamedUpdate(String jpqlQueryName,Map<String,Object> paramsMap){
		return createTypedQueryByQueryName(jpqlQueryName, paramsMap).executeUpdate();
	}
	
	// -====================================== 本地查询 ======================================-
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> nativeQuery(Class<T> resultClass, String nativeSql, Object... params) {
		Query query = getNativeQuery(resultClass, nativeSql, params);
		if(resultClass.isAnnotationPresent(NativeQueryResultEntity.class)) {
			List<T> ret = new ArrayList<T>();
			for(Object result : query.getResultList()) {
				if(result instanceof Object[]) {
					Field[] fileds = resultClass.getDeclaredFields();
					try {
						T t = resultClass.newInstance();
						Object[] resultArray = (Object[])result;
						for (int i = 0, j = 0; i < resultArray.length; ) {
							if(Modifier.isStatic(fileds[j].getModifiers()) || Modifier.isFinal(fileds[j].getModifiers())) {
								j++;
								continue;
							}
							fileds[j].setAccessible(true);
							if(fileds[j].getType().isEnum()){
								fileds[j].set(t, Enum.valueOf((Class)fileds[j].getType(), String.valueOf(resultArray[i])));
							}else if(fileds[j].getType().getName().equals("int")){
								fileds[j].set(t, resultArray[i] instanceof Integer ? ((Integer)resultArray[i]).intValue() : ((BigInteger)resultArray[i]).intValue());
							}else if(fileds[j].getType().getName().equals("boolean")){
								if(resultArray[i] instanceof Boolean){
									fileds[j].set(t, ((Boolean)resultArray[i]).booleanValue());
								}else{
									fileds[j].set(t, resultArray[i] instanceof Integer ? ((Integer)resultArray[i]).intValue()==1 : ((BigInteger)resultArray[i]).intValue()==1);
								}
							}else{
								fileds[j].set(t, resultArray[i]);
							}
							i++;
							j++;
		                }
						ret.add(t);
					} catch (ReflectiveOperationException e) {
						logger.error("Class with NativeQueryResultEntity annotation must supply no arg construct and ", e);
					}
				} else {
					logger.error("Class with NativeQueryResultEntity annotation must return object array");
				}
			}
			return ret;
		} else {
			return query.getResultList();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> nativeQueryList(Class<T> resultClass, String nativeSql, Object... params) {
		return getNativeQuery(resultClass, nativeSql, params).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T nativeQuerySingleResult(Class<T> resultClass, String nativeSql, Object... params) {
		return (T) getNativeQuery(resultClass, nativeSql, params).getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> nativeQueryPagingList(Class<T> resultClass, Pageable pageable, String nativeSql, Object... params) {
		Integer pageNumber = pageable.getPageNumber();
		Integer pageSize = pageable.getPageSize();
		Integer startPosition = pageNumber * pageSize;
		return getNativeQuery(resultClass, nativeSql, params).setFirstResult(startPosition).setMaxResults(pageSize).getResultList();
	}
	
	@Override
	public <T> Page<T> nativeQuery(Class<T> resultClass, Pageable pageable, String nativeSql, Object... params) {
		List<T> rows = nativeQueryPagingList(resultClass, pageable, nativeSql, params);
		Long total = nativeQueryCount(nativeSql, params);
		return new PageImpl<T>(rows, pageable, total);
	}
	
	@Override
	public Long nativeQueryCount(String nativeSql, Object... params) {
		nativeSql = StringUtils.substringBefore(nativeSql, "order by"); // 去掉order by, 提升执行效率
		String countSql = "select count(*) from (" + nativeSql + ") _count";
		Object count = createNativeQuery(countSql, params).getSingleResult();
		return ((Number) count).longValue();
	}
	
	@Override
	public int nativeExecuteUpdate(String nativeSql, Object... params) {
		return createNativeQuery(nativeSql, params).executeUpdate();
	}
	
	// -=================================== 其它 ===========================================-
	
	private Query createQueryByJpql(String jpql, Object... params) {
		Query q = em.createQuery(jpql);
		for (int i = 0; i < params.length; i++) {
			q.setParameter(i + 1, params[i]); // 与Hiberante不同,jpa query从位置1开始
		}
		return q;
	}
	
	private Query createQueryByJpql(String jpql, Map<String, Object> paramMap) {
		Query q = em.createQuery(jpql);
		Iterator<String> it = paramMap.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			q.setParameter(key, paramMap.get(key));
		}
		return q;
	}
	
	private <T> TypedQuery<T> createTypedQueryByQueryName(Class<T> resultClass, String jpqlName, Map paramsMap) {
		TypedQuery<T> q = em.createNamedQuery(jpqlName, resultClass);
		Iterator<String> it = paramsMap.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			q.setParameter(key, paramsMap.get(key));
		}
		return q;
	}
	
	//更新及删除操作不能指定class
	private Query createTypedQueryByQueryName(String jpqlName, Map paramsMap) {
		Query q = em.createNamedQuery(jpqlName);
		Iterator<String> it = paramsMap.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			q.setParameter(key, paramsMap.get(key));
		}
		return q;
	}	
	
	
	private <T> Query getNativeQuery(Class<T> resultClass, String sql, Object... params) {
		Query q = isEntity(resultClass) ? 
				createNativeQuery(resultClass, sql, params) : createNativeQuery(sql, params);
		return q;
	}
	
	private <T> Boolean isEntity(Class<T> resultClass) {
		return BaseEntity.class.isAssignableFrom(resultClass);
	}
	
	private Query createNativeQuery(String sql, Object... params) {
		Query q = em.createNativeQuery(sql);
		for (int i = 0; i < params.length; i++) {
			q.setParameter(i + 1, params[i]); // 与Hiberante不同,jpa query从位置1开始
		}
		return q;
	}
	
	private <T> Query createNativeQuery(Class<T> resultClass, String sql, Object... params) {
		Query q = em.createNativeQuery(sql, resultClass);
		for (int i = 0; i < params.length; i++) {
			q.setParameter(i + 1, params[i]); // 与Hiberante不同,jpa query从位置1开始
		}
		return q;
	}
	
	/**
	 * 执行统计查询
	 * @param jpql
	 * @param params 命名参数
	 * @return
	 */
	private String generateCountJpql(String jpql) {
		return "select count(*) from " + StringUtils.substringAfter(jpql, "from");
	}

	/**
	 * 通过jpql生成统计sql
	 * @param jpql
	 * @return
	 */
	private String generateCountSql(String jpql) {
		return "select count(*) c from (" + jpqlToSql(jpql) + ") _count";
	}

	/**
	 * 通过hibernate的翻译器(QueryTranslator)将jpql翻译成sql
	 * @param jpql
	 * @return
	 */
	private String jpqlToSql(String jpql) {
		QueryTranslator qt = new QueryTranslatorImpl(jpql, jpql,
				Collections.EMPTY_MAP,
				(SessionFactoryImplementor) getHibernateSessionFactory());
		qt.compile(Collections.EMPTY_MAP, false);
		return qt.getSQLString();
	}

	public <T> List<T> directQuery(Class<T> resultClass, String jpql,  Map<String, Object> paramsMap) {
		return getDirectQuery(resultClass, jpql, paramsMap).getResultList();
	}

	private <T> TypedQuery<T> getDirectQuery(Class<T> resultClass, String jpql,
			Map<String, Object> paramsMap) {
		TypedQuery<T> q = em.createQuery(jpql, resultClass);
		
		for(Iterator<String> it = paramsMap.keySet().iterator(); it.hasNext();){
			String key = it.next();
			q.setParameter(key, paramsMap.get(key));
		}
		return q;
	}

	@Override
	public <T> T directQuerySingleResult(Class<T> resultClass, String jpql,
			Map<String, Object> paramsMap) {
		try {
			return getDirectQuery(resultClass, jpql, paramsMap).getSingleResult();
		} catch(NoResultException ex) {
			return null;
		}
	}

	@Override
	public <T> List<T> directQueryPagingList(Class<T> resultClass,
			Pageable pageable, String jpql, Map<String, Object> paramsMap) {
		Integer pageNumber = pageable.getPageNumber();
		Integer pageSize = pageable.getPageSize();
		Integer startPosition = pageNumber * pageSize;
		return getDirectQuery(resultClass, jpql, paramsMap).setFirstResult(startPosition).setMaxResults(pageSize).getResultList();
	}
	
	@Override
	public Query createQueryByJpqlString(String qlString){
		return this.em.createQuery(qlString);
	}
	
	@Override
	public <T> TypedQuery<T> createTypedQueryByJpqlString(String qlString,Class<T> resultClass){
		return this.em.createQuery(qlString, resultClass);
	}
	
	public String countForQuery(String qlString){
		return QueryUtils.createCountQueryFor(qlString);
	}
	
	// -================================== Map查询 ========================================-
	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> queryMapSingleResult(String jpql, Object... params) {
		return (Map<String, Object>) createQueryMap(jpql, params).getSingleResult();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryMap(String jpql, Object... params) {
		return createQueryMap(jpql, params).getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryMapPagingList(Pageable pageable, String jpql, Object... params) {
		Integer pageNumber = pageable.getPageNumber();
		Integer pageSize = pageable.getPageSize();
		Integer startPosition = pageNumber * pageSize;
		return createQueryMap(jpql, params).setFirstResult(startPosition).setMaxResults(pageSize).getResultList();
	}
	
	@Override
	public Page<Map<String, Object>> queryMap(Pageable pageable, String jpql, Object... params) {
		List<Map<String, Object>> rows = queryMapPagingList(pageable, jpql, params);
		Long total = queryCount(jpql, params);
		return new PageImpl<Map<String, Object>>(rows, pageable, total);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> nativeQueryMapSingleResult(String nativeSql, Object... params) {
		return (Map<String, Object>) createNativeQueryMap(nativeSql, params).getSingleResult();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> nativeQueryMap(String nativeSql, Object... params) {
		return createNativeQueryMap(nativeSql, params).getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> nativeQueryMapPagingList(Pageable pageable, String nativeSql, Object... params) {
		Integer pageNumber = pageable.getPageNumber();
		Integer pageSize = pageable.getPageSize();
		Integer startPosition = pageNumber * pageSize;
		return createNativeQueryMap(nativeSql, params).setFirstResult(startPosition).setMaxResults(pageSize).getResultList();
	}
	
	@Override
	public Page<Map<String, Object>> nativeQueryMap(Pageable pageable, String nativeSql, Object... params) {
		List<Map<String, Object>> rows = nativeQueryMapPagingList(pageable, nativeSql, params);
		Long total = nativeQueryCount(nativeSql, params);
		return new PageImpl<Map<String, Object>>(rows, pageable, total);
	}
	
	private Query createQueryMap(String jpql, Object... params) {
		Query q = createQueryByJpql(jpql, params);
		q.unwrap(org.hibernate.Query.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return q;
	}
	
	private Query createNativeQueryMap(String jpql, Object... params) {
		Query q = createNativeQuery(jpql, params);
		q.unwrap(org.hibernate.SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return q;
	}
}
