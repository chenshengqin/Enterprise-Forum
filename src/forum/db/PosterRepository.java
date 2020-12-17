package forum.db;

import java.util.List;

import forum.entity.Poster;
import forum.web.PaginationSupport;

public interface PosterRepository {
	
	/**
	 * 取得有效用户数量
	 * 
	 * @return
	 */
	long count();

	/**
	 * 新建一个用户
	 * 
	 * @param poster
	 *            新建的用户
	 * @return 用户
	 */
	Poster save(Poster poster);

	/**
	 * 依据id查找用户
	 * 
	 * @param id
	 *            吐槽者ID
	 * @return 用户
	 */
	Poster findOne(long id);

	/**
	 * 依据用户名（登录名）查找用户
	 * 
	 * @param userName
	 *            用户名（登录名）
	 * @return 用户
	 */
	Poster findByUserName(String userName);

	/**
	 * 依据用户名（登录名），密码查找吐槽者
	 * 
	 * @param userName
	 *            用户名（登录名）
	 * @param password
	 *            密码
	 * @return 用户
	 */
	Poster findByUserName(String userName, String password);

	/**
	 * 取得全部用户
	 * 
	 * @return 全部用户
	 */
	List<Poster> findAll();
	
	/**
	 * 分页展示
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	PaginationSupport<Poster> findPage(int pageNo, int pageSize);
	
	/**
	 * 删除指定id用户
	 * @param id
	 */
	void deletePoster(long id);
	
	/**
	 * 锁定指定id用户
	 * @param id
	 */
	void setLockOrNo(long id, boolean locked);
	
}

