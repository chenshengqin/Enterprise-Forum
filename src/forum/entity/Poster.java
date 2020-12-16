package forum.entity;

import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;


/**
 * 用户对象类
 * 
 * @ClassName: Poster
 * @author: CN.CSQ
 * @version: 1.0
 * @date: 2020-12-16
 */
public class Poster {

	private Long id;

	@NotNull
	@Size(min = 5, max = 16)
	private String userName;

	@NotNull
	@Size(min = 5, max = 25)
	private String password;

	@NotNull
	@Size(min = 2, max = 30)
	private String trueName;

	@NotNull
	@Email
	private String email;
	
	private Boolean locked;
	private Boolean deleted;

	/**
	 * 空构造方法
	 * 
	 * @Title: Poster
	 */
	public Poster() {
		
	}
	
	/**
	 * 构造方法 1
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @param trueName
	 *            姓名
	 * @param email
	 *            邮箱
	 */
	public Poster(String userName, String password, String trueName, String email) {
		this(null, userName, password, trueName, email);
	}

	/**
	 * 构造方法 2
	 * 
	 * @param id
	 * 			  主键ID
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @param trueName
	 *            姓名
	 * @param email
	 *            邮箱
	 */
	public Poster(Long id, String userName, String password, String trueName, String email) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.trueName = trueName;
		this.email = email;
		this.locked = false;
		this.deleted = false;
	}
	
	/**
	 * 构造方法 3
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @param trueName
	 *            姓名
	 * @param email
	 *            邮箱
	 * @param locked
	 *            锁定标记
	 * @param deleted
	 *            删除标记
	 */
	public Poster(String userName, String password, String trueName, String email, Boolean locked, Boolean deleted) {
		this(null, userName, password, trueName, email, locked, deleted);
	}
	
	/**
	 * 构造方法 4
	 * 
	 * @param id
	 * 			  主键ID
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @param trueName
	 *            姓名
	 * @param email
	 *            邮箱
	 * @param locked
	 *            锁定标记
	 * @param deleted
	 *            删除标记
	 */
	public Poster(Long id, String userName, String password, String trueName, String email, Boolean locked, Boolean deleted) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.trueName = trueName;
		this.email = email;
		this.locked = locked;
		this.deleted = deleted;
	}

	/**
	 * 取得用户名
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
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 取得密码
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
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 取得ID主键
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置ID主键
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 取得姓名
	 * 
	 * @return
	 */
	public String getTrueName() {
		return trueName;
	}

	/**
	 * 设置姓名
	 * 
	 * @param firstName
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	/**
	 * 取得邮箱
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
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 取得锁定标记
	 * 
	 * @return the locked
	 */
	public Boolean getLocked() {
		return locked;
	}

	/**
	 * 设置锁定标记
	 * 
	 * @param locked the locked to set
	 */
	public void setLocked(Boolean locked) {
		this.locked = locked;
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

	/**
	 * 
	 * 
	 * @Title: hashCode
	 * @return
	 * @see java.lang.Object#hashCode()
	 */  
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/**
	 * 
	 * 
	 * @Title: equals
	 * @param obj
	 * @return
	 * @see java.lang.Object#equals(java.lang.Object)
	 */  
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Poster other = (Poster) obj;
		return Objects.equals(id, other.id);
	}

}
