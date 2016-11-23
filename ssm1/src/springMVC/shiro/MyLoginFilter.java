package springMVC.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

public class MyLoginFilter extends FormAuthenticationFilter{
	
	public MyLoginFilter() {
		
	}

	/**
     * 登录令牌
     */
    @Override
    protected org.apache.shiro.authc.AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String verifyCode = request.getParameter("verifyCode");
        return new MyLoginToken(username, password, verifyCode);
    }
	
}
