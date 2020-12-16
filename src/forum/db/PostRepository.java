package forum.db;

import java.util.List;

import forum.entity.Post;
import forum.web.PaginationSupport;

/**
 * 主题帖资源库接口
 * 
 */
public interface PostRepository {

	/**
	 * 获得有效主题帖数量
	 * @return
	 */
	long count();
	
	/**
	 * 获得指定id的主题帖
	 * @param id
	 * @return
	 */
	Post findOne(long id);
	
	/**
	 * 生成主题帖
	 * @param post
	 * @return
	 */
	Post save(Post post);
	
	/**
	 * 获得指定发帖人id的帖子列表
	 * @param posterId
	 * @return
	 */
	List<Post> findByPosterId(long posterId);
	
	/**
	 * 删除帖子
	 * @param id
	 */
	void delete(long id);
	
	/**
	 * 编辑帖子
	 * @param thread
	 * @param id
	 * @return
	 */
	Post update(Post post, long id);
	
	/**
	 * 分页展示
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	PaginationSupport<Post> findPage(int pageNo, int pageSize);
}
