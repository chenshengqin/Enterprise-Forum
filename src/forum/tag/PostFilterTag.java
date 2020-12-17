package forum.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import forum.entity.Post;
import forum.web.PaginationSupport;

/**
 * 过滤非搜索文本的主题帖
 * 
 * @author: CN.CSQ
 * @version: 1.0
 */
public class PostFilterTag extends SimpleTagSupport {
	private List<Post> postList;
	private String searchText;
	private int pageNo;
	private PaginationSupport<Post> paginationSupport;

	/**
	 * @return the postList
	 */
	public List<Post> getPostList() {
		return postList;
	}

	/**
	 * @param postList the postList to set
	 */
	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}
	
	/**
	 * @return the searchText
	 */
	public String getSearchText() {
		return searchText;
	}

	/**
	 * @param searchText the searchText to set
	 */
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	
	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

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
		if(searchText != null) {
			List<Post> validPostList = new ArrayList<Post>();
			for(Post post : postList) {
				String postName = post.getPostName();
				if(postName.indexOf(searchText) != -1) {
					validPostList.add(post);
				}
			}
			int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo);
			paginationSupport = new PaginationSupport<>(validPostList, validPostList.size(), startIndex);
			getJspContext().setAttribute("paginationSupport", paginationSupport);
		}else {
			int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo);
			paginationSupport = new PaginationSupport<>(postList, postList.size(), startIndex);
			getJspContext().setAttribute("paginationSupport", paginationSupport);
		}
	}
}