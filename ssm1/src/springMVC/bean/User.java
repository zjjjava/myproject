package springMVC.bean;

/** 

* @ClassName: User 

* @Description: 登陆表单对象

* @author A18ccms a18ccms_gmail_com 

* @date 2016-11-6 上午10:41:42 

* 
 

*/ 
public class User {
	private String username;
	private String password;
	private String verifyCode;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	
}
