package forum.db;

import forum.entity.Manager;
import forum.web.PaginationSupport;

/**
 * 管理员资源库接口
 * 
 * @ClassName: ManagerRepository
 * @author: CN.CSQ
 * @version: 1.0
 * @date: 2020-11-19
 */
public interface ManagerRepository {
	
	/**
	 * 取得管理员数量
	 * 
	 * @return
	 */
	long count();

	/**
	 * 新建一个管理员
	 * 
	 * @return 管理员
	 */
	Manager save(Manager manager);

	/**
	 * 依据id查找管理员
	 * 
	 * @param id
	 *            管理员ID
	 * @return 管理员
	 */
	Manager findOne(long id);

	/**
	 * 依据用户名（登录名）查找管理员
	 * 
	 * @param userName
	 *            用户名（登录名）
	 * @return 管理员
	 */
	Manager findByUserName(String userName);

	/**
	 * 依据用户名（登录名），密码查找管理员
	 * 
	 * @param userName
	 *            用户名（登录名）
	 * @param password
	 *            密码
	 * @return 管理员
	 */
	Manager findByUserName(String userName, String password);
	
	/**
	 * 依据页码和指定页面大小，返回管理员列表
	 * @param pageNo 起始位置
	 * @param pageSize 每页数量
	 * @return 分页对象
	 */
	PaginationSupport<Manager> findPage(int pageNo,int pageSize);
	
	/**
	 * 根据用户名，更新密码，邮箱，电话
	 * 
	 * @Title: update
	 * @param userName	用户名
	 * @param password	更新的密码
	 * @param email		更新的邮箱
	 * @param phoneNo	更新的电话
	 * @return
	 */
	Manager updateProfile(String userName, String password, String trueName, String email, String phoneNo);
	
	/**
	 * 根据用户名，删除某个管理员
	 * 
	 * @Title: delete
	 * @param userName 用户名
	 * @return
	 */
	Manager deleteByName(String userName);
	
	/**
	 * 根据id，删除某个管理员
	 * 
	 * @Title: deleteName
	 * @param id id
	 * @return
	 */
	Manager deleteByID(Long id);
}
