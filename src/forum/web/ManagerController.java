package forum.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import forum.db.ManagerRepository;
import forum.db.PostRepository;
import forum.db.PosterRepository;
import forum.db.ReplyRepository;
import forum.entity.Manager;
import forum.entity.Post;

/**
 * 管理员控制类
 * @author niliu717
 *
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {
	@Autowired
	private ManagerRepository managerRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private PosterRepository posterRepository;
	@Autowired
	private ReplyRepository replyRepository;
	
	
	/**
	 * 返回管理员登录界面
	 * @return
	 */
	@RequestMapping(value = "",method = GET)
	public String showManagerLoginForm(HttpSession session) {
		if(session.getAttribute("manager") != null) {
			if(session.getAttribute("poster")!=null) {
				session.removeAttribute("poster");
			}
			return "managerHome";
		}
		else {
		return "managerLogin";
		}
	}
	
	/**
	 * 处理管理员登录请求
	 * @param userName
	 * @param passWord
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "",method = POST)
	public String processManagerLogin(@RequestParam(value = "userName" , defaultValue = "")String userName,
			@RequestParam(value = "password" , defaultValue = "")String passWord, HttpSession session) {
		Manager manager = managerRepository.findByUserName(userName, passWord);
		if(manager != null && manager.getDeleted() == false) {
			session.removeAttribute("poster");
			session.setAttribute("manager", manager);
			return "managerHome";
		}else {
			return "managerLoginError";
		}
	}
	
	/**
	 * 返回管理员编辑帖子界面，并实现对帖子进行分页
	 * @return
	 */
	
	@RequestMapping(value = "/showPost",method = GET)
	public String showPost(Model model,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		/*
		 * 依据WebConfig.viewResolver中的
		 * org.springframework.web.servlet.view.InternalResourceViewResolver定义
		 * 
		 * InternalResourceViewResolver resolver = new
		 * InternalResourceViewResolver();
		 * resolver.setPrefix("/WEB-INF/views/"); 
		 * resolver.setSuffix(".jsp");
		 * 
		 * 返回相应jsp视图，即返回/WEB-INF/views/home.jsp
		 * 
		 */
		model.addAttribute("paginationSupport", postRepository.findPage(pageNo, pageSize));
		return "managerShowPost";
	}
	
	/**
	 * 实现删除帖子功能，并重定向到showPost界面
	 * @param postId
	 * @return
	 */
	@RequestMapping(value = "/showPost/deletePost/{postId}",method = GET)
	public String deleltePost(@PathVariable("postId")long postId) {
		this.postRepository.deletePost(postId);
		return "redirect:/manager/showPost";
	}
	/**
	 * 返回Poster管理界面，并实现对Poster的分页服务
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/showPoster" , method = GET)
	public String showPoster(Model model,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
				
		model.addAttribute("paginationSupport", posterRepository.findPage(pageNo, pageSize));
		return "managerShowPoster";
	}
	/**
	 * 实现锁定用户
	 * @param posterId
	 * @return
	 */
	@RequestMapping(value = "/showPoster/banPoster/{posterId}" , method = GET)
	public String banPoster(@PathVariable("posterId")long posterId) {
		this.posterRepository.setLockOrNo(posterId, true);
		return "redirect:/manager/showPoster";
	}
	
	/**
	 * 实现解除用户锁定
	 * @param posterId
	 * @return
	 */
	@RequestMapping(value = "/showPoster/unbanPoster/{posterId}" , method = GET)
	public String unbanPoster(@PathVariable("posterId")long posterId) {
		this.posterRepository.setLockOrNo(posterId, false);
		return "redirect:/manager/showPoster";
	}
	
	/**
	 * 实现帖子置顶功能
	 * @param postId
	 * @return
	 */
	@RequestMapping(value = "/showPost/putToTop/{postId}" , method = GET)
	public String putToTop(@PathVariable("postId")long postId) {
		this.postRepository.setTopOrNo(postId, true);
		return "redirect:/manager/showPost";
	}
	
	/**
	 * 实现取消帖子置顶功能
	 * @param postId
	 * @return
	 */
	@RequestMapping(value = "/showPost/cancelputToTop/{postId}" , method = GET)
	public String cancelPutToTop(@PathVariable("postId")long postId) {
		this.postRepository.setTopOrNo(postId, false);
		return "redirect:/manager/showPost";
	}
	
	/**
	 * 返回Manager列表，并实现分页服务
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/showManager",method = GET)
	public String showManager(Model model,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		model.addAttribute("paginationSupport",managerRepository.findPage(pageNo, pageSize));
		return "managerShowManager";
	}
	
	/**
	 * 实现删除管理员功能
	 * @param managerId
	 * @return
	 */
	@RequestMapping(value = "/showManager/deleteManager/{managerId}" , method = GET)
	public String deleteManager(@PathVariable("managerId")long managerId) {
		this.managerRepository.deleteByID(managerId);
		return "redirect:/manager/showManager";
	}
	/**
	 * 实现了管理员注销功能
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout" , method = GET)
	public String logout(HttpSession session) {
		if(session.getAttribute("manager") != null) {
			session.removeAttribute("manager");
		}
		return "redirect:/manager";
	}
	/**
	 * 返回了添加管理员界面
	 * @return
	 */
	@RequestMapping(value = "/register" , method = GET)
	public String register(Model model) {
		model.addAttribute(new Manager());
		return "managerRegisterForm";
	}
	/**
	 * 实现管理员添加管理员功能
	 * @param manager
	 * @param errors
	 * @param session
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/register", method = POST)
	public String processRegistration(@Valid Manager manager, Errors errors, HttpSession session, HttpServletRequest req, HttpServletResponse res, Model model) {
		if (errors.hasErrors()) {
			return "managerRegisterForm";
		}
		if(manager.getTrueName() == null) {
			manager.setTrueName("");
		}
		manager.setDeleted(false);
		List<Manager> managerList = managerRepository.findAll();
		for(Manager sqlManager : managerList) {
			if(manager.equals(sqlManager)) {
				model.addAttribute("errSameUserName", "errSameUserName");
				return "managerRegisterForm";
			}
		}
		managerRepository.save(manager);
		return "redirect:/manager";
	}
	/**
	 * 返回修改管理员个人信息界面
	 * @return
	 */
	@RequestMapping(value = "/modify", method = GET)
	public String modify(Model model, HttpSession session) {
		model.addAttribute("manager", session.getAttribute("manager"));
		return "managerModify";
	}
	/**
	 * 实现管理员个人信息的修改
	 * @param manager
	 * @param errors
	 * @param session
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/modify" , method = POST)
	public String processModify(@Valid Manager manager, Errors errors, HttpSession session, HttpServletRequest req, HttpServletResponse res, Model model) {
		if (errors.hasErrors()) {
			return "managerModify";
		}
		if(manager.getTrueName() == null) {
			manager.setTrueName("");
		}
		Manager nowManager = (Manager) session.getAttribute("manager");
		manager.setDeleted(nowManager.getDeleted());
		manager = managerRepository.modify(nowManager.getId(), manager);
		session.setAttribute("manager", manager);
		return "redirect:/manager";
	}
	
	/**
	 * 查看单个主题
	 * 
	 * @param postId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/showPost/{postId}", method = RequestMethod.GET)
	public String post(@PathVariable("postId") long postId, Model model,
						@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
						@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		Post post = postRepository.findOne(postId);
		model.addAttribute(post);
		model.addAttribute("paginationSupport", replyRepository.findPage(postId, pageNo, pageSize));
		return "managerShowSinglePost";
	}
	
	/**
	 * 打开编辑主题帖页面
	 * 
	 * @param postId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/showPost/edit/{postId}", method = RequestMethod.GET)
	public String getPost(@PathVariable("postId") long postId, Model model) {
		Post post = postRepository.findOne(postId);
		model.addAttribute(post);
		return "managerEditPost";
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
	@RequestMapping(value = "/showPost/edit/{postId}", method = RequestMethod.POST)
	public String editPost(@RequestParam(value = "postId") Long postId, Model model,
							@RequestParam(value = "postName", defaultValue = "") String postName,
							@RequestParam(value = "message", defaultValue = "") String message)
			throws Exception {
		boolean empty = false;
		if(postName.equals("")) {
			model.addAttribute("emptyPostName", "emptyPostName");
			empty = true;
		}
		if(message.equals("")) {
			model.addAttribute("emptyMessage", "emptyMessage");
			empty = true;
		}
		if(empty) {
			Post post = postRepository.findOne(postId);
			model.addAttribute(post);
			return "managerEditPost";
		}
		
		Post post = postRepository.findOne(postId);
		post.setPostName(postName);
		post.setMessage(message);
		postRepository.updatePost(post, postId);
		return "redirect:/manager/showPost/{postId}" + postId;
		}
}
