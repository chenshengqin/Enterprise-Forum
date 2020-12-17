package forum.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.servlet.http.Cookie;
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
import org.springframework.web.bind.annotation.SessionAttributes;

import forum.db.PosterRepository;
import forum.entity.Poster;

/**
 * 用户控制类
 * 
 * @author CN.CSQ
 * @version v1.0
 */
@Controller
/*
 * 将Model中的poster参数保存到了session中（如果Model中没有poster参数，而session中存在一个poster参数，
 * 那么SessionAttribute会讲这个参数塞进Model中） SessionAttribute只能作用在类上。
 * 
 * 将Model中的被注解的attrName属性保存在一个SessionAttributesHandler中，在每个RequestMapping的方法执行后，
 * 这个SessionAttributesHandler都会将它自己管理的“属性”从Model中写入到真正的HttpSession；同样，
 * 在每个RequestMapping的方法执行前，
 * SessionAttributesHandler会将HttpSession中的被@SessionAttributes注解的属性写入到新的Model中。
 */
@SessionAttributes({ "poster" })
@RequestMapping("/poster")
public class PosterController {

	@Autowired
	private PosterRepository posterRepository;

	/**
	 * 进入注册
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/register", method = GET)
	public String showRegistrationForm(Model model) {

		// 等价于model.addAttribute("poster", new Poster());
		model.addAttribute(new Poster());
		return "registerForm";
	}

	/**
	 * 提交注册信息，提交成功后跳转到用户信息
	 * 
	 * @param poster
	 * @param errors
	 * @return
	 */
	@RequestMapping(value = "/register", method = POST)
	public String processRegistration(@Valid Poster poster, Errors errors, HttpSession session, HttpServletRequest req, HttpServletResponse res, Model model) {
		if (errors.hasErrors()) {
			return "registerForm";
		}
		poster.setLocked(false);
		poster.setDeleted(false);
		List<Poster> posterList = posterRepository.findAll();
		for(Poster sqlPoster : posterList) {
			if(poster.equals(sqlPoster)) {
				model.addAttribute("errSameUserName", "errSameUserName");
				return "registerForm";
			}
		}
		
		
		poster = posterRepository.save(poster);
		
		// 保存cookie
		boolean nameFlag = true;
		boolean passwordFlag = true;
		Cookie cookies[] = req.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("userName")) {
					cookies[i].setValue(poster.getUserName());
					nameFlag = false;
				}
				if (cookies[i].getName().equals("password")) {
					cookies[i].setValue(poster.getPassword());
					passwordFlag = false;
				}
			}
		}
		if(nameFlag) {
			Cookie nameCookie = new Cookie("userName", poster.getUserName());
			nameCookie.setMaxAge(20 * 60 * 60);
			res.addCookie(nameCookie);
		}
		if(passwordFlag) {
			Cookie passwordCookie = new Cookie("password", poster.getPassword());
			passwordCookie.setMaxAge(20 * 60 * 60);
			res.addCookie(passwordCookie);
		}
		
		if(poster == null) {
			System.out.println("Poster Null.");
		}
		//session.setAttribute("poster", poster);
		// System.out.println(poster.getId());
		// return "redirect:/login" ;
		return "redirect:/poster/" + poster.getUserName();
	}

	/**
	 * 用户信息页面
	 * 
	 * @param userName
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{userName}", method = GET)
	public String showSpitterProfile(@PathVariable String userName, Model model) {
		/*
		 * @PathVariable("xxx") 通过 @PathVariable
		 * 可以将URL中占位符参数{xxx}绑定到处理器类的方法形参中@PathVariable(“xxx“)
		 * 用于将请求URL中的模板变量映射到功能处理方法的参数上，即取出uri模板中的变量作为参数
		 */

		Poster poster = posterRepository.findByUserName(userName);
		if (poster != null) {
			model.addAttribute(poster);
			return "profile";
		} else {
			return "redirect:/";
		}
	}

}
