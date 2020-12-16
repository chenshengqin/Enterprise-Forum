package forum.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * 主题帖对象类
 *
 */
public class Post {
	
	private final Long id;
	private final Poster poster;
	
	@NotNull
	private String postName;
	
	@NotNull
	private String message;
	private Date postedTime;
	private int follow;
	private int click;
	
	@NotNull
	private Boolean topped;
	
	@NotNull
	private Boolean deleted;
	
	/**
	 * 构造方法
	 * 
	 * @param id           id主键
	 * @param poster       发帖人
	 * @param postName     主题帖标题
	 * @param message      主题帖内容
	 * @param postedTime   发帖时间
	 */
	public Post(Long id, Poster poster, String postName, String message, Date postedTime) {
		this.id = id;
		this.poster = poster;
		this.postName = postName;
		this.message = message;
		this.postedTime = postedTime;
		this.follow = 0;
		this.click = 0;
		this.topped = false;
		this.deleted = false;
	}
	
	public Post(Long id, Poster poster, String postName, String message, Date postedTime, int follow, int click, Boolean topped, Boolean deleted) {
		this.id = id;
		this.poster = poster;
		this.postName = postName;
		this.message = message;
		this.postedTime = postedTime;
		this.follow = follow;
		this.click = click;
		this.topped = topped;
		this.deleted = deleted;
	}
	
	/**
	 * 获得id主键
	 * @return
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * 获得发帖人
	 * @return
	 */
	public Poster getPoster() {
		return this.poster;
	}
	
	/**
	 * 获得主题帖标题
	 * @return
	 */
	public String getPostName() {
		return this.postName;
	}
	
	/**
	 * 设置主题帖标题
	 * @param postName
	 */
	public void setPostName(String postName) {
		this.postName = postName;
	}
	
	/**
	 * 获得内容
	 * @return
	 */
	public String getMessage() {
		return this.message;
	}
	
	/**
	 * 编辑内容
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * 获得发帖时间
	 * @return
	 */
	public Date getPostedTime() {
		return this.postedTime;
	}
	
	/**
	 * 设置发帖时间
	 * @param postedTime
	 */
	public void setPostedTime(Date postedTime) {
		this.postedTime = postedTime;
	}
	
	/**
	 * 获得回复数
	 * @return
	 */
	public int getFollow() {
		return this.follow;
	}
	
	/**
	 * 设置回复数
	 * @param follow
	 */
	public void setFollow(int follow) {
		this.follow = follow;
	}
	
	/**
	 * 获得点击量
	 * @return
	 */
	public int getClick() {
		return this.click;
	}
	
	/**
	 * 设置点击量
	 * @param click
	 */
	public void setClick(int click) {
		this.click = click;
	}
	
	/**
	 * 获得置顶状态
	 * @return
	 */
	public boolean getTopped() {
		return this.topped;
	}
	
	/**
	 * 设置置顶状态
	 * @param topped
	 */
	public void setTopped(Boolean topped) {
		this.topped = topped;
	}
	
	/**
	 * 获得删除状态
	 * @return
	 */
	public boolean getDeleted() {
		return this.deleted;
	}
	
	/**
	 * 设置删除状态
	 * @param deleted
	 */
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}
