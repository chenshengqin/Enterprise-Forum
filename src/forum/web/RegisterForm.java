package forum.web;

/**
 * 注册表单类，作用于view层，便于传递数据
 * 
 * @author SK
 */
public class RegisterForm {
	
	private String trueName;
	private String email;
	private String userName;
	private String password;
	private String passwordConfirm;
	
	/**
	 * 获得姓名
	 * 
	 * @return
	 */
	public String getTrueName() {
		return trueName;
	}
	
	/**
	 * 设置姓名
	 * 
	 * @param trueName 
	 * @return
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	/**
	 * 获得邮箱
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * 设置邮箱
	 * 
	 * @param email 
	 * @return
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * 获得用户名
	 * 
	 * @return
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * 设置用户名
	 * 
	 * @param userName 
	 * @return
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * 获得密码
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * 设置密码
	 * 
	 * @param password 
	 * @return
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 获得确认密码
	 * 
	 * @return
	 */
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	
	/**
	 * 设置确认密码
	 * 
	 * @param passwordConfirm 
	 * @return
	 */
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
}
