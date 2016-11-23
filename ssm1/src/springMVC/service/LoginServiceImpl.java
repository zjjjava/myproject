package springMVC.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import springMVC.bean.DepNode;
import springMVC.bean.IPage;
import springMVC.bean.MVCUser;
import springMVC.dao.BaseDao;

@Repository("loginService")
public class LoginServiceImpl implements LoginService {
	@Resource(name = "baseDao")
	protected BaseDao baseDao;

	@Cacheable(value = "listCache", key = "#page+'-'+#rows+'-'+#user.name")
	@Override
	public IPage list(MVCUser user, int page, int rows, String conditions,
			String order, String sort) {
		IPage ipage = new IPage();

		List<Object> params = new ArrayList<Object>();
		String sql = "select * from users where 1=1 ";

		Field[] fields = user.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				if (field.get(user) != null
						&& !"".equals(field.get(user).toString())) {
					sql += "and " + field.getName() + " = ? ";
					params.add(field.get(user));
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (null != user.getName() && !"".equals(user.getName())) {
			sql += "and name = ? ";
			params.add(user.getName());
		}

		int total = 0;
		try {
			total = baseDao.findByCondition(sql, params, MVCUser.class).size();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// 排序参数
		if (null != order && !"".equals(order) && null != sort
				&& !"".equals(sort)) {
			sql += "order by " + sort + " " + order + " ";
			/*
			 * params.add(sort); params.add(order);
			 */
		}

		// 分页参数
		sql += "limit " + ((page - 1) * rows) + "," + rows + " ";

		// org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy
		List<MVCUser> list = null;
		
		try {
			list = baseDao.findByCondition(sql, params, MVCUser.class);

			File users = new File("E:/users.txt");
			if(!users.exists()){
				users.createNewFile();
			}
			
			ObjectOutputStream oo = new ObjectOutputStream(
					new FileOutputStream(users));
			oo.writeObject(list);
			System.out.println("Person对象序列化成功！");
			oo.close();

			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					new File("E:/users.txt")));
			@SuppressWarnings("unchecked")
			List<MVCUser> list1 = list1 = (List<MVCUser>) ois.readObject();
			list1.remove(0);
			System.out.println("Person对象反序列化成功！");
			System.out.println(list1.size());
			
			ipage.setRows(list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		ipage.setTotal(total);

		return ipage;
	}

	@Cacheable(value = "listCache")
	@Override
	public List<DepNode> findDeps() throws Exception {
		return baseDao.findAll("DepNode");
	}

	@Override
	// @Transactional(readOnly = false, propagation = Propagation.REQUIRED,
	// isolation = Isolation.READ_COMMITTED)
	public void update(MVCUser user) throws Exception {
		baseDao.update(user);
	}

}
