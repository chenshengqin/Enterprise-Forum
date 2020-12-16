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
	 * @return
	 */
	long count();
	
	/**
	 * 获得指定id的回复
	 * @param id
	 * @return
	 */
	Reply findOne(long id);
	
	/**
	 * 生成回复
	 * @param reply
	 * @return
	 */
	Reply save(Reply reply);
	
	/**
	 * 获得指定用户的回复
	 * @param posterId
	 * @return
	 */
	List<Reply> findByPostId(long postId);
	
	/**
	 * 删除
	 * @param id
	 */
	void delete(long id);
	
	/**
	 * 分页展示
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	PaginationSupport<Reply> findPage(int pageNo,int pageSize);
}
