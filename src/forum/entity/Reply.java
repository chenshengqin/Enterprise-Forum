package forum.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * 回复对象类
 * 
 */
public class Reply {
	
	private final Long id;
	private final Poster poster;
	private final long postId;
	
	@NotNull
	private final String message;
	private final Date postedTime;
	
	@NotNull
	private Boolean deleted;
	
	/**
	 * 构造方法
	 * 
	 * @param id           id主键
	 * @param poster       回复者
	 * @param post         回复主题帖
	 * @param message      内容
	 * @param postedTime   回复时间
	 */
	public Reply(Long id, Poster poster, long postId, String message, Date postedTime) {
		this.id = id;
		this.poster = poster;
		this.postId = postId;
		this.message = message;
		this.postedTime = postedTime;
		this.deleted = false;
	}
	
	/**
	 * 获得id主键
	 * @return
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * 获得回复者
	 * @return
	 */
	public Poster getPoster() {
		return this.poster;
	}
	
	/**
	 * 获得回复主题帖id
	 * @return
	 */
	public long getPostId() {
		return this.postId;
	}
	
	/**
	 * 获得回复内容
	 * @return
	 */
	public String getMessage() {
		return this.message;
	}
	
	/**
	 * 获得回复时间
	 * @return
	 */
	public Date getPostedTime() {
		return this.postedTime;
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
