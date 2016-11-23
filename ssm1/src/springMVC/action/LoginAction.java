package springMVC.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import springMVC.bean.DepNode;
import springMVC.bean.IPage;
import springMVC.bean.MVCUser;
import springMVC.bean.User;
import springMVC.dao.BaseDao;
import springMVC.service.LoginService;

@Controller
@RequestMapping("/zjj")
public class LoginAction extends AbstractController {
	@Resource(name = "baseDao")
	protected BaseDao baseDao;

	@Resource(name = "loginService")
	protected LoginService loginService;

	@RequestMapping("/tolist.mvc")
	public ModelAndView tolist() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("list");
		return modelAndView;
	}
	
	@RequestMapping("/welcome.mvc")
	public ModelAndView welcome() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("welcome");
		return modelAndView;
	}
	
	@RequestMapping("/toArticleList.mvc")
	public ModelAndView toArticleList() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("article-list");
		return modelAndView;
	}
	
	@RequestMapping("/toArticleAdd.mvc")
	public ModelAndView toArticleAdd() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("article-add");
		return modelAndView;
	}
	
	@RequestMapping(value = "list")
	@ResponseBody
	public IPage list(MVCUser user, int page, int rows, String conditions, String order, String sort) {
		return loginService.list(user, page, rows, conditions, order, sort);
	}
	
	/**   
	*    
	* ��Ŀ��ƣ�ssm1     
	* ����������   ������
	* �����ˣ��ž���   
	* ����ʱ�䣺2016-10-19 ����8:06:07   
	* �޸��ˣ��ž���   
	* �޸�ʱ�䣺2016-10-19 ����8:06:07   
	* �޸ı�ע��   
	* @version    
	*    
	*/ 
	@RequestMapping(value = "findDeps")
	@ResponseBody
	public List<DepNode> findDeps(){
		List<DepNode> depNodes = null;
		try {
			depNodes = loginService.findDeps();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return depNodes;
	}

	@ResponseBody
	@RequestMapping(value = "add")
	public Map<String, Object> add(MVCUser user) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			baseDao.add(user);
		} catch (Exception e) {
			resultMap.put("msg", "fail");
			e.printStackTrace();
		}

		resultMap.put("msg", "success");
		return resultMap;
	}

	@ResponseBody
	@RequestMapping(value = "modify")
	public Map<String, Object> modify(MVCUser user) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			loginService.update(user);
		} catch (Exception e) {
			resultMap.put("msg", "fail");
			e.printStackTrace();
		}

		resultMap.put("msg", "success");
		return resultMap;
	}

	@ResponseBody
	@RequestMapping(value = "delete")
	public Map<String, Object> delete(MVCUser user) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			baseDao.delete(user);
		} catch (Exception e) {
			resultMap.put("msg", "fail");
			e.printStackTrace();
		}

		resultMap.put("msg", "success");
		return resultMap;
	}

	@ResponseBody
	@RequestMapping(value="login",method=RequestMethod.GET)  
    public ModelAndView loginForm(Model model){  
        //model.addAttribute("user", new User());  
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
    }  
      
    @RequestMapping(value="login",method=RequestMethod.POST)  
    public String login(User user,BindingResult bindingResult,RedirectAttributes redirectAttributes){  
        try {  
            if(bindingResult.hasErrors()){  
                return "login.mvc";  
            }  
            //使用权限工具进行用户登录，登录成功后跳到shiro配置的successUrl中，与下面的return没什么关系！  
            SecurityUtils.getSubject().login(new UsernamePasswordToken(user.getUsername(), user.getPassword()));  
            return "redirect:tolist.mvc";  
        } catch (AuthenticationException e) {  
            redirectAttributes.addFlashAttribute("message","用户名或密码错误");  
            return "redirect:login.mvc";  
        }  
    }  
    
    @ResponseBody
	@RequestMapping(value="opennew")  
    public Map<String, Object> opennew(Model model){  
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	resultMap.put("msg", "pass");
    	
		return resultMap;
    }  
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
