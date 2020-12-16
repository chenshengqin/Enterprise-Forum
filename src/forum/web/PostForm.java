package forum.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 主题帖表单类，是作用view层的对象类，便于传递数据
 * 
 * @author CN.CSQ
 * @version v1.0
 */
public class PostForm {

	@NotNull
	@Size(min = 1, max = 20)
	private String postName;
	
	@NotNull
	@Size(min = 1, max = 2000)
	private String message;

	/**
	 *  取得吐槽内容
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 设置吐槽内容
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 获取标题
	 * 
	 * @return the postName
	 */
	public String getPostName() {
		return postName;
	}

	/**
	 * 设置标题
	 * 
	 * @param postname the postname to set
	 */
	public void setPostName(String postName) {
		this.postName = postName;
	}
	
	
}
