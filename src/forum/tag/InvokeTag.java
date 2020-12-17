package forum.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 插入内容
 * 
 * @ClassName: InvokeTag
 * @author: CN.CSQ
 * @version: 1.0
 * @date: 2020-12-17
 */
public class InvokeTag extends SimpleTagSupport {
	/**
	 * 
	 * 
	 * @Title: doTag
	 * @throws JspException
	 * @throws IOException
	 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
	 */
	@Override
	public void doTag() throws JspException, IOException {
		getJspContext().setAttribute("id", "id:");
		getJspBody().invoke(null);
	}
}