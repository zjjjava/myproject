package springMVC.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.jdbc.core.RowMapper;

/**
 * @author FengJianBo 2014�?�?8�?上午11:44:14
 */
public interface BaseDao {
	/**
	 * 添加
	 * 
	 * @author FengJianBo
	 * @param t
	 * @throws Exception
	 *             2014�?�?8�?上午11:48:02
	 */
	public <T> void add(T t) throws Exception;

	/**
	 * 修改
	 * 
	 * @author FengJianBo
	 * @param t
	 * @throws Exception
	 *             2014�?�?8�?上午11:48:07
	 */
	public <T> void update(T t) throws Exception;

	/**
	 * 按照对象删除
	 * 
	 * @author FengJianBo
	 * @param t
	 * @throws Exception
	 *             2014�?�?8�?上午11:48:16
	 */
	public <T> void delete(T t) throws Exception;

	/**
	 * 按照id删除
	 * 
	 * @author FengJianBo
	 * @param id
	 * @throws Exception
	 *             2014�?�?8�?上午11:48:26
	 */
	public <T> void deleteById(Class<T> entityName, Serializable id)
			throws Exception;

	public <T> void deleteBathByIds(Class<T> entityName, Serializable ids[])
			throws Exception;

	public <T> T getEntity(Class<T> entityName, Serializable id);

	/**
	 * 查询�?��
	 * 
	 * @author FengJianBo
	 * @throws Exception
	 *             2014�?�?8�?上午11:48:35
	 */
	public <T> List<T> findAll(String entityName) throws Exception;

	public <T> List<T> findByCondition(String sql, List<Object> params,
			Class<T> entityClass) throws Exception;

	public int excuteSql(String sql, List<Object> params);
	
	Session getSession();

	// List<Map<String, Object>> findSql(String sql);
	
}
