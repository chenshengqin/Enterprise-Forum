package forum.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import forum.db.PostRepository;
import forum.db.ReplyRepository;
import forum.entity.Post;
import forum.entity.Poster;
import forum.entity.Reply;

/**
 * 主题帖控制器类
 * 
 * @author CN.CSQ
 * @version v1.0
 */
@Controller
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostRepository postRepository;
	private ReplyRepository replyRepository;

	/**
	 * 主题帖列表
	 * 
	 * @param count
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public PaginationSupport<Post> posts(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

		return postRepository.findPage(pageNo, pageSize);
		/*
		 * 约定优于配置，以上语句等价于：
		 * 
		 * model.addAttribute("paginationSupport",postRepository.findPage(
		 * pageNo, pageSize)); return "posts";
		 * 
		 */
	}

	/**
	 * 查看单个主题
	 * 
	 * @param postId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{postId}", method = RequestMethod.GET)
	public String post(@PathVariable("postId") long postId, Model model) {
		//TODO
		//解决查看主题回帖的问题
		model.addAttribute(postRepository.findOne(postId));
		return "post";
	}

	/**
	 * 新建一个主题
	 * 
	 * @param request
	 * @param form
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String savePost(HttpServletRequest request, PostForm form, Model model, HttpSession session)
			throws Exception {
		postRepository
				.save(new Post(null, (Poster) session.getAttribute("poster"), form.getPostName(), form.getMessage(), new Date()));
		return "redirect:/posts";
	}
	
	/**
	 * 发表回帖
	 * 
	 * @param postId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{postId}", method = RequestMethod.POST)
	public String reply(@PathVariable("postId") long postId, HttpServletRequest request, String replyMessage, Model model, HttpSession session) {
		replyRepository
				.save(new Reply(null, (Poster) session.getAttribute("poster"), postId, replyMessage, new Date()));
		return "redirect:/posts/" + postId;
	}


}
