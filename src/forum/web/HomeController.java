package forum.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import forum.db.PostRepository;
import forum.db.PosterRepository;
import forum.entity.Poster;

/**
 * 系统主页控制类
 * 
 * @author CN.CSQ
 * @version v1.0
 */
@Controller // 控制定义
@RequestMapping("/") // 相应web路径
public class HomeController {

	@Autowired // 自动注入资源
	private PosterRepository posterRepository;
	@Autowired
	private PostRepository postRepository;
	/**
	 * 首页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = GET) // 相应的请求方法
	public String home(Model model,
			HttpServletRequest req, HttpServletResponse res, HttpSession session,
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
		
		// 实现自动登录
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			String userName = "";
			String password = "";
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("userName")) {
					userName = cookies[i].getValue();
				}
				if (cookies[i].getName().equals("password")) {
					password = cookies[i].getValue();
				}
			}
			if (!userName.equals("") && !password.equals("")) {
				Poster poster = posterRepository.findByUserName(userName, password);
				if (poster != null) {
					session.setAttribute("poster", poster);
				}
			}
		}
		
		model.addAttribute("paginationSupport", postRepository.findPage(pageNo, pageSize));
		return "home";
	}

	/**
	 * 注册
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = GET)
	public String showLoginForm() {
		return "loginForm";
	}

	/**
	 * 登录请求
	 * 
	 * @param userName
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/login", method = POST)
	public String processLogin(@RequestParam(value = "userName", defaultValue = "") String userName,
			@RequestParam(value = "password", defaultValue = "") String password, HttpSession session,
			HttpServletRequest req, HttpServletResponse res) {
		/*
		 * @RequestParam注解：
		 * 
		 * value：参数名字，即入参的请求参数名字，如userName表示请求的参数区中的名字为userName的参数的值将传入；
		 * 
		 * required：是否必须，默认是true，表示请求中一定要有相应的参数，否则将报404错误码；
		 * 
		 * defaultValue：默认值，表示如果请求中没有同名参数时的默认值
		 * 
		 */

		Poster poster = posterRepository.findByUserName(userName, password);
		if (poster != null) {
			session.setAttribute("poster", poster);
			
			// 保存cookie,直接覆写
			Cookie nameCookie = new Cookie("userName", userName);
			nameCookie.setMaxAge(20 * 60 * 60);
			//System.out.println(nameCookie.getPath());
			//System.out.println(nameCookie.getDomain());
			res.addCookie(nameCookie);
			Cookie passwordCookie = new Cookie("password", password);
			passwordCookie.setMaxAge(20 * 60 * 60);
			res.addCookie(passwordCookie);
			
			return "redirect:/";
		} else {
			return "loginError";
		}

	}

	/**
	 * 注销
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout", method = GET)
	public String logout(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		// 覆盖，使cookie无效
		Cookie nameCookie = new Cookie("userName", "");
		nameCookie.setMaxAge(0);
		res.addCookie(nameCookie);
		Cookie passwordCookie = new Cookie("password", "");
		passwordCookie.setMaxAge(0);
		res.addCookie(passwordCookie);
		
		session.removeAttribute("poster");
		session.invalidate();
		return "redirect:/";
	}
	
	/**
	 * 进入搜索
	 * 
	 * @return
	 */
	@RequestMapping(value = "/search", method = GET)
	public String showSearch() {
		return "search";
	}
	
	/**
	 * 搜索
	 * 
	 * @return
	 */
	@RequestMapping(value = "/search", method = POST)
	public String getSearch(Model model, HttpSession session,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "searchText") String searchText) {
		session.setAttribute("pageNo", pageNo);
		session.setAttribute("searchText", searchText);
		model.addAttribute("List", postRepository.findAll());
		
		return "search";
	}
}
