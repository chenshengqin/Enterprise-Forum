package forum.entity;

import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

/**
 * 管理员对象类
 * 
 * @ClassName: Manger
 * @author: CN.CSQ
 * @version: 1.0
 * @date: 2020-12-16
 */
public class Manager {
	
	private Long id;
	
	@NotNull
	@Size(min = 5, max = 16)
	private String userName;
	
	@NotNull
	@Size(min = 5, max = 25)
	private String password;
	
	private String trueName;
	
	@Email
	private String email;
	
	@NotNull
	private Boolean deleted;

	/**
	 * Void Constructor
	 * 
	 * @Title: Manager
	 */
	public Manager() {
		
	}

	/**
	 * Constructor 1
	 * 
	 * @Title: Manager
	 * @param id		主键
	 * @param userName	管理用户名
	 * @param password	密码
	 */
	public Manager(Long id, String userName, String password) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.deleted = false;
	}

	/**
	 * Constructor 2
	 * 
	 * @Title: Manager
	 * @param id 		主键
	 * @param userName 	管理用户名
	 * @param password 	密码
	 * @param trueName	管理员全名
	 * @param email		邮箱
	 * @param deleted	删除标记
	 */
	public Manager(Long id, String userName, String password, String trueName, String email, Boolean deleted) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.trueName = trueName;
		this.email = email;
		this.deleted = deleted;
	}
	
	/**
	 * Constructor 3
	 * 
	 * @Title: Manager
	 * @param userName 	管理用户名
	 * @param password 	密码
	 * @param trueName	管理员全名
	 * @param email		邮箱
	 * @param deleted	删除标记
	 */
	public Manager(String userName, String password, String trueName, String email, Boolean deleted) {
		this(null, userName, password, trueName, email, deleted);
	}
	
	/**
	 * 取得id主键
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置id主键
	 * 
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 取得用户名（登录）
	 * 
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置用户名（登录）
	 * 
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 取得密码
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置密码
	 * 
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 取得全名
	 * 
	 * @return the fullName
	 */
	public String getTrueName() {
		return trueName;
	}

	/**
	 * 设置全名
	 * 
	 * @param fullName the fullName to set
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	/**
	 * 取得邮箱
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置邮箱
	 * 
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * 取得删除标记
	 * 
	 * @return the deleted
	 */
	public Boolean getDeleted() {
		return deleted;
	}

	/**
	 * 设置删除标记
	 * 
	 * @param deleted the deleted to set
	 */
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manager other = (Manager) obj;
		return Objects.equals(id, other.id) || Objects.equals(userName, other.userName);
	}
	
	


	
}
