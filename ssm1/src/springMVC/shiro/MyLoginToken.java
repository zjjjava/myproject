package springMVC.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class MyLoginToken extends UsernamePasswordToken{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String verifyCode;

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public MyLoginToken(String username, String password, String verifyCode) {
		super(username, password);
		this.verifyCode = verifyCode;
	}
	
	
}
