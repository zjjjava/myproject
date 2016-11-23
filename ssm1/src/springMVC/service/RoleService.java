package springMVC.service;

import java.util.List;
import java.util.Map;

import springMVC.bean.IPage;
import springMVC.bean.Role;

public interface RoleService {
	IPage list(Role role, int page, int rows, String conditions, String order, String sort);

	List<?> findFuncs(String id);

	Map<String, Object> modRoleFuncs(String roleId, String roleFuncIds);
	
}
