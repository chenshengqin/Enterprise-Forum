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
	 * @return 有效主题帖数量
	 */
	long count();
	
	/**
	 * 获得指定id的主题帖
	 * @param id
	 * @return 主题帖
	 */
	Post findOne(long id);
	
	/**
	 * 生成主题帖
	 * @param post
	 * @return 主题帖
	 */
	Post save(Post post);
	
	/**
	 * 获得指定发帖人id的帖子列表
	 * @param posterId
	 * @return 帖子列表
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
	 * @return 新帖
	 */
	Post update(Post post, long id);
	
	/**
	 * 更新跟帖
	 * 
	 * @param id postId
	 */
	void updateFollow(long id);
	
	/**
	 * 更新点击
	 * 
	 * @param id postId
	 */
	void updateClick(long id);
	
	/**
	 * 分页展示
	 * @param pageNo 页数
	 * @param pageSize 页长
	 * @return 主题帖分页类对象
	 */
	PaginationSupport<Post> findPage(int pageNo, int pageSize);
}
