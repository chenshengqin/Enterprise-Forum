package forum.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpSession;

import forum.db.ManagerRepository;
import forum.db.PostRepository;
import forum.db.PosterRepository;
import forum.entity.Manager;

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
			return "loginError";
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
	
}
