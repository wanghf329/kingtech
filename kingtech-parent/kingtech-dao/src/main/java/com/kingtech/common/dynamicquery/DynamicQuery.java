package com.kingtech.common.dynamicquery;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 扩展SpringDataJpa, 支持动态jpql/nativesql查询并支持分页查询<br/>
 * 使用方法：注入ServiceImpl
 * 
 * @author wanghui
 * @lastModified wanghui 2014-9-11 10:26:22
 */
public interface DynamicQuery {
	
	// -====================================== jpql查询 ======================================-
	
	/**
	 * 执行jpql查询
	 * @param resultClass 查询结果类型
	 * @param jpqlName
	 * @param params 占位符参数(例如?1)绑定的参数值
	 * @return
	 */
	@Deprecated
	<T> List<T> query(Class<T> resultClass, String jpqlName,  Map paramsMap);
	
	/**
	 * 执行jpql查询一行
	 * @param resultClass 查询结果类型
	 * @param jpqlName
	 * @param params 占位符参数(例如?1)绑定的参数值
	 * @return
	 */
	@Deprecated
	<T> T querySingleResult(Class<T> resultClass, String jpqlName,  Map paramsMap);
	
	/**
	 * 执行jpql分页查询
	 * @param resultClass 查询结果类型
	 * @param pageable 分页数据
	 * @param jpqlName
	 * @param params 占位符参数(例如?1)绑定的参数值
	 * @return 分页结果
	 */
	@Deprecated
	<T> List<T> queryPagingList(Class<T> resultClass, Pageable pageable, String jpqlName,  Map paramsMap);
	
	/**
	 * 这个参数有问题
	 * 传的是语句
	 * @param resultClass
	 * @param pageable
	 * @param qlString
	 * @param params
	 * @return
	 * @author wh
	 */
	@Deprecated
	<T> Page<T> query(Class<T> resultClass, Pageable pageable, String qlString, Object... params);
	
	/**
	 * 执行jpql统计查询
	 * @param jpql
	 * @param params 占位符参数(例如?1)绑定的参数值
	 * @return 统计条数
	 */
	@Deprecated
	Long queryCount(String qlString, Object... params);
	
	/**
	 * 执行jpql的update,delete操作
	 * @param nativeSql
	 * @param params
	 * @return
	 */
	int executeUpdate(String jpql, Object... params);
	
	
    int executeUpdate(String jpql, Map<String, Object> paramMap);
    
    @Deprecated
    int executeNamedUpdate(String named,Map<String,Object> paramsMap);
	
	// -====================================== 本地查询 ======================================-
	
	/**
	 * 执行nativeSql查询
	 * @param resultClass 查询结果类型
	 * @param nativeSql
	 * @param params 占位符参数(例如?1)绑定的参数值
	 * @return
	 */
	<T> List<T> nativeQuery(Class<T> resultClass, String nativeSql, Object... params);
	
	/**
	 * @Title: nativeQueryList 
	 * @Description: 执行nativeSql查询  用于sql中自带分页
	 * @param @param resultClass
	 * @param @param nativeSql
	 * @param @param params
	 * @param @return    设定文件 
	 * @return List<T>    返回类型 
	 * @author zln 
	 * @throws
	 */
	public <T> List<T> nativeQueryList(Class<T> resultClass, String nativeSql, Object... params);
	
	/**
	 * 执行nativeSql查询一行
	 * @param resultClass 查询结果类型
	 * @param nativeSql
	 * @param params 占位符参数(例如?1)绑定的参数值
	 * @return
	 */
	<T> T nativeQuerySingleResult(Class<T> resultClass, String nativeSql, Object... params);
	
	/**
	 * 执行nativeSql分页查询
	 * @param resultClass 查询结果类型
	 * @param pageable 分页数据
	 * @param nativeSql
	 * @param params 占位符参数(例如?1)绑定的参数值
	 * @return 分页结果
	 */
	<T> List<T> nativeQueryPagingList(Class<T> resultClass, Pageable pageable, String nativeSql, Object... params);
	
	/**
	 * 执行nativeSql分页查询
	 * @param resultClass 查询结果类型
	 * @param pageable 分页数据
	 * @param nativeSql
	 * @param params 占位符参数(例如?1)绑定的参数值
	 * @return 分页对象
	 */
	<T> Page<T> nativeQuery(Class<T> resultClass, Pageable pageable, String nativeSql, Object... params);
	
	/**
	 * 执行nativeSql统计查询
	 * @param nativeSql
	 * @param params 占位符参数(例如?1)绑定的参数值
	 * @return 统计条数
	 */
	Long nativeQueryCount(String nativeSql, Object... params);
	
	/**
	 * 执行nativeSql的update,delete操作
	 * @param nativeSql
	 * @param params
	 * @return
	 */
	int nativeExecuteUpdate(String nativeSql, Object... params);

	
	/**
	 * 执行jpql查询
	 * @param resultClass 查询结果类型
	 * @param 直接输入jpql参数，无需namedquery
	 * @param params 占位符参数(例如?1)绑定的参数值
	 * @return
	 */
	<T> List<T> directQuery(Class<T> resultClass, String jpql,  Map<String, Object> paramsMap);
	
	
	/**
	 * 执行jpql查询
	 * @param resultClass 查询结果类型
	 * @param 直接输入jpql参数，无需namedquery
	 * @param params 占位符参数(例如?1)绑定的参数值
	 * @return
	 */
	<T> T directQuerySingleResult(Class<T> resultClass, String jpql,  Map<String, Object> paramsMap);
	
	
	/**
	 * 执行jpql分页查询
	 * @param resultClass 查询结果类型
	 * @param pageable 分页数据
	 * @param nativeSql
	 * @param params 占位符参数(例如?1)绑定的参数值
	 * @return 分页结果
	 */
	<T> List<T> directQueryPagingList(Class<T> resultClass, Pageable pageable, String nativeSql, Map<String, Object> paramsMap);

	
	Map<String, Object> queryMapSingleResult(String jpql, Object... params);

	List<Map<String, Object>> queryMap(String jpql, Object... params);

	List<Map<String, Object>> queryMapPagingList(Pageable pageable, String jpql, Object... params);

	Page<Map<String, Object>> queryMap(Pageable pageable, String jpql, Object... params);

	Map<String, Object> nativeQueryMapSingleResult(String nativeSql, Object... params);

	List<Map<String, Object>> nativeQueryMap(String nativeSql, Object... params);

	List<Map<String, Object>> nativeQueryMapPagingList(Pageable pageable, String nativeSql, Object... params);

	Page<Map<String, Object>> nativeQueryMap(Pageable pageable, String nativeSql, Object... params);


	Query createQueryByJpqlString(String qlString);


	<T> TypedQuery<T> createTypedQueryByJpqlString(String qlString, Class<T> resultClass);

}
