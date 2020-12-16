package forum.db.jdbc;

import forum.db.ManagerRepository;
import forum.entity.Manager;
import forum.web.PaginationSupport;

public class JdbcManagerRepository implements ManagerRepository {

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Manager save(Manager manager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Manager findOne(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Manager findByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Manager findByUserName(String userName, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginationSupport<Manager> findPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Manager updateProfile(String userName, String password, String tureName, String email, String phoneNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Manager deleteByName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Manager deleteByID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
