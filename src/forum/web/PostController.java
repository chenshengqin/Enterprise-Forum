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
	@Autowired
	private ReplyRepository replyRepository;

	/**
	 * 主题帖列表
	 * 
	 * @param count
	 * @return
	 */
	/*
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
	//}
	
	/**
	 * 新建主题帖页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/newPost", method = RequestMethod.GET)
	public String newPost(Model model) {
		
		 return "newPost";
	}
	

	/**
	 * 查看单个主题
	 * 
	 * @param postId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{postId}", method = RequestMethod.GET)
	public String post(@PathVariable("postId") long postId, Model model,
						@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
						@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		//TODO
		//解决查看主题回帖的问题
		Post post = postRepository.findOne(postId);
		if(pageNo == 1) {
			post.setClick(post.getClick() + 1);
			postRepository.updateClick(post);
		}
		model.addAttribute(post);
		model.addAttribute("paginationSupport", replyRepository.findPage(postId, pageNo, pageSize));
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
	@RequestMapping(value = "/newPost", method = RequestMethod.POST)
	public String savePost(HttpServletRequest request, PostForm form, Model model, HttpSession session)
			throws Exception {
		postRepository
				.save(new Post(null, (Poster) session.getAttribute("poster"), form.getPostName(), form.getMessage(), new Date()));
		return "redirect:/";
	}
	
	/**
	 * 发表回帖
	 * 
	 * @param postId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{postId}", method = RequestMethod.POST)
	public String reply(@PathVariable("postId") long postId, HttpServletRequest request,
							@RequestParam(value = "replyMessage", defaultValue = "") String replyMessage,
							Model model, HttpSession session) {
		replyRepository
				.save(new Reply(null, (Poster) session.getAttribute("poster"), postId, replyMessage, new Date()));
		Post post = postRepository.findOne(postId);
		post.setFollow(post.getFollow() + 1);
		postRepository.updateFollow(post);
		return "redirect:/posts/" + postId;
	}

	/**
	 * 打开编辑主题帖页面
	 * 
	 * @param postId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit/{postId}", method = RequestMethod.GET)
	public String getEditPost(@PathVariable("postId") long postId, Model model) {
		Post post = postRepository.findOne(postId);
		model.addAttribute(post);
		return "editPost";
	}
	
	/**
	 * 编辑一个主题
	 * 
	 * @param request
	 * @param form
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/edit/{postId}", method = RequestMethod.POST)
	public String editPost(@RequestParam(value = "id") Long id,
							@RequestParam(value = "postName", defaultValue = "") String postName,
							@RequestParam(value = "message", defaultValue = "") String message)
			throws Exception {
		Post post = postRepository.findOne(id);
		post.setPostName(postName);
		post.setMessage(message);
		postRepository.updatePost(post, id);
		return "redirect:/";
	}
	
	/**
	 * 自己的主题帖
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/ownPost", method = RequestMethod.GET)
	public String ownPosts(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize, Model model, HttpSession session) {
		Poster poster = (Poster) session.getAttribute("poster");
		Long id = poster.getId();
		model.addAttribute("paginationSupport", postRepository.findPageByPosterId(id, pageNo, pageSize));
		return "ownPost";
	}
}
