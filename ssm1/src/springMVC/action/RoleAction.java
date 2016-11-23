package springMVC.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import springMVC.bean.IPage;
import springMVC.bean.MVCUser;
import springMVC.bean.Role;
import springMVC.dao.BaseDao;
import springMVC.service.LoginService;
import springMVC.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleAction extends AbstractController {

	@Resource(name = "baseDao")
	protected BaseDao baseDao;
	
	@Resource(name = "roleService")
	protected RoleService roleService;
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "toRoleList")
	public ModelAndView opennew(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("role/roleList");
		
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "toPrint")
	public ModelAndView toPrint(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("role/carManage");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "list")
	@ResponseBody
	public IPage list(Role role, int page, int rows, String conditions, String order, String sort) {
		return roleService.list(role, page, rows, conditions, order, sort);
	}
	
	@RequestMapping(value = "findFuncs")
	@ResponseBody
	public List<?> findFuncs(String id) {
		return roleService.findFuncs(id); 
	}
	
	@RequestMapping(value = "modRoleFuncs")
	@ResponseBody
	public Map<String, Object> modRoleFuncs(String roleId, String roleFuncIds) {
		return roleService.modRoleFuncs(roleId, roleFuncIds); 
	}
	
}
