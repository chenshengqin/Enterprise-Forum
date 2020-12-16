package forum.db;

import java.util.List;

import forum.entity.Reply;
import forum.web.PaginationSupport;

/**
 * 回复资源库接口
 */
public interface ReplyRepository {
	
	/**
	 * 获得有效回复数量
	 * @return 回复数量
	 */
	long count();
	
	/**
	 * 获得指定id的回复
	 * @param id
	 * @return 回复
	 */
	Reply findOne(long id);
	
	/**
	 * 生成回复
	 * @param reply
	 * @return 回复
	 */
	Reply save(Reply reply);
	
	/**
	 * 获得指定用户的回复
	 * @param posterId
	 * @return 回复列表
	 */
	List<Reply> findByPostId(long postId);
	
	/**
	 * 删除
	 * @param id
	 */
	void delete(long id);
	
	/**
	 * 分页展示
	 * @param pageNo 页数
	 * @param pageSize 页长
	 * @return 回复分页类对象
	 */
	PaginationSupport<Reply> findPage(long postId, int pageNo,int pageSize);
}
