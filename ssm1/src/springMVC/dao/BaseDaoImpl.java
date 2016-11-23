package springMVC.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("baseDao")
public class BaseDaoImpl implements BaseDao {

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public <T> void add(T t) throws Exception {
		getSession().save(t);
		getSession().flush();
	}

	@Override
	public void update(Object t) throws Exception {
		Session session = getSession();
		session.update(t);
		session.flush();
	}

	@Override
	public void delete(Object t) throws Exception {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.delete(t);
		session.flush();
		tx.commit();
		session.close();
	}

	@Override
	public <T> void deleteById(Class<T> entityName, Serializable id)
			throws Exception {
		delete(getEntity(entityName, id));
		getSession().flush();
	}

	@Override
	public <T> void deleteBathByIds(Class<T> entityName, Serializable ids[])
			throws Exception {
		for (int i = 0; i < ids.length; i++) {
			deleteById(entityName, ids[i]);
		}
		getSession().flush();
	}

	@Override
	public <T> T getEntity(Class<T> entityName, Serializable id) {
		@SuppressWarnings("unchecked")
		T t = (T) getSession().get(entityName, id);
		if (t != null) {
			getSession().flush();
		}
		return t;
	}

	@Override
	public <T> List<T> findAll(String entityName) throws Exception {
		String hql = "from " + entityName;
		Query queryObject = getSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<T> list = queryObject.list();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findByCondition(String sql, List<Object> params,
			Class<T> entityClass) throws Exception {
		SQLQuery query = getSession().createSQLQuery(sql);
		addParamsToSQLQuery(params, query);
		query.addEntity(entityClass);
		
		return query.list();
	}

	private void addParamsToSQLQuery(List<Object> params, SQLQuery query) {
		if (params != null && !params.isEmpty()) {
			for (int i = 0,size=params.size(); i < size; i++) {
				query.setParameter(i, params.get(i));
			}
		}
	}
	
	@Override
	public int excuteSql(String sql, List<Object> params){
		SQLQuery query = getSession().createSQLQuery(sql);
		addParamsToSQLQuery(params, query);
        int result = query.executeUpdate();
		
		return result;
	}

	@Override
	public Session getSession() {
		return sessionFactory.openSession();
	}

}
