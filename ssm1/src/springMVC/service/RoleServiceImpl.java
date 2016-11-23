package springMVC.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import springMVC.bean.Func;
import springMVC.bean.IPage;
import springMVC.bean.MVCUser;
import springMVC.bean.Role;
import springMVC.bean.RoleFunc;
import springMVC.dao.BaseDao;

@Repository("roleService")
public class RoleServiceImpl implements RoleService {
	@Resource(name = "baseDao")
	protected BaseDao baseDao;
	
	@Override
	public IPage list(Role role, int page, int rows, String conditions,
			String order, String sort) {
		IPage ipage = new IPage();
		
		List<Object> params = new ArrayList<Object>();
		String sql = "select * from role where 1=1 ";
		
		Field[] fields = role.getClass().getDeclaredFields();
		for (Field field:fields) {
			 try {
				if(field.get(role)!=null&&!"".equals(field.get(role).toString())){
					sql += "and " +field.getName()+ " = ? ";
					params.add(field.get(role));
				 }
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (null != role.getName() && !"".equals(role.getName())) {
			sql += "and name = ? ";
			params.add(role.getName());
		}
		
		int total = 0;
		try {
			total = baseDao.findByCondition(sql, params, Role.class).size();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// 排序参数
		if (null != order && !"".equals(order) && null != sort && !"".equals(sort)) {
			sql += "order by " + sort + " " + order + " ";
			/*params.add(sort);
			params.add(order);*/
		}
		
		// 分页参数
		sql += "limit " + ((page - 1)*rows) + "," + rows + " ";
		
		//org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy
		List<Role> list = null;
		try {
			list = baseDao.findByCondition(sql, params, Role.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ipage.setRows(list);
		ipage.setTotal(total);
		
		return ipage;
	}

	@Override
	public List<?> findFuncs(String id) {
		String sql = "select * from role_func where role_id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		
		try {
			List<RoleFunc> roleFuncs = baseDao.findByCondition(sql, params, RoleFunc.class);
			/*String funcIds = "";
			for (int i = 0; i < roleFuncs.size(); i++) {
				funcIds += roleFuncs.get(i).getFuncId();
				if (i < roleFuncs.size() - 1) {
					funcIds += ",";
				}
				
			}*/
			
			String funcSql = "select * from func";
			params.clear();
			//params.add(funcIds);
			List<Func> funcs = baseDao.findByCondition(funcSql, params, Func.class);
			
			for (int i = 0; i < funcs.size(); i++) {
				Func currentFunc = funcs.get(i);
				
				for (int j = 0; j < roleFuncs.size(); j++) {
					RoleFunc currentRoleFunc = roleFuncs.get(j);
					if (currentFunc.getId().equals(currentRoleFunc.getFuncId())) {
						currentFunc.setChecked("true");
						break;
					}else{
						currentFunc.setChecked("false");
					}
					
				}
				
			}
			
			return funcs;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return null;
	}

	@Override
	public Map<String, Object> modRoleFuncs(String roleId, String roleFuncIds) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String cleanCurRoleAllFuncsSql = "delete from role_func where role_id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(Integer.parseInt(roleId));
		
		try {
			baseDao.excuteSql(cleanCurRoleAllFuncsSql, params);
			
			String[] curRoleFuncIds = roleFuncIds.split(",");
			for (int i = 0; i < curRoleFuncIds.length; i++) {
				RoleFunc roleFunc = new RoleFunc();
				roleFunc.setRoleId(roleId);
				roleFunc.setFuncId(curRoleFuncIds[i]);
				
				baseDao.add(roleFunc);
			}
		} catch (Exception e) {
			resultMap.put("msg", "fail");
			e.printStackTrace();
			return resultMap;
		}
		
		resultMap.put("msg", "success");
		return resultMap;
	}

}
