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
	public String showManagerLoginForm() {
		return "managerLogin";
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
			session.setAttribute("manager", manager);
			return "managerHome";
		}else {
			return "loginError";
		}
	}
	
	/**
	 * 返回管理员编辑帖子界面
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
	 * 返回Poster管理界面
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
	
	@RequestMapping(value = "/showPoster/banPoster/{posterId}" , method = GET)
	public String banPoster(@PathVariable("posterId")long posterId) {
		this.posterRepository.
		return null;
	}
	
	@RequestMapping(value = "/showManager",method = GET)
	public String showManager(Model model,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		model.addAttribute("paginationSupport",managerRepository.findPage(pageNo, pageSize));
		return "managerShowManager";
	}
}
