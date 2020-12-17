package forum.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;

import forum.db.PostRepository;
import forum.db.ReplyRepository;
import forum.entity.Post;

/**
 * 搜索控制类
 */
@Controller // 控制定义
@RequestMapping("/search") // 相应web路径
public class SearchController {
	
	@Autowired // 自动注入资源
	private ReplyRepository replyRepository;
	@Autowired
	private PostRepository postRepository;
	
	@RequestMapping(value = "/searchPosts", method = POST)
	public String processSearch(@Valid String searchText, Errors errors, HttpSession session) {
		if (errors.hasErrors()) {
			return "serchForm";
		}
		
		List<Post> postList = postRepository.findAll();
		
		return "";
	}
}
