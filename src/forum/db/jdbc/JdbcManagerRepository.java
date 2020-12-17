package forum.db.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import forum.db.ManagerRepository;
import forum.entity.Manager;
import forum.entity.Poster;
import forum.web.PaginationSupport;
@Repository
public class JdbcManagerRepository implements ManagerRepository {
	private JdbcTemplate jdbc;
	private static final String SELECT_MANAGER = "select id, username,password,truename,email,deleted from Manager ";
	private static final String INSERT_MANAGER = "insert into Manager (username, password, truename, email, deleted) values (?, ?, ?, ?, ?)";
	private static final String COUNT_MANAGER = "select count(id) from Manager";
	private static final String DELETE_MANAGER = "update Manager set deleted = true ";
	private static final String UPDATE_MANAGER = "update Manager set username = ?, password = ?, truename = ?,email = ?,deleted = ? where id = ?";
	private static final String SELECT_PAGE_MANAGERS = SELECT_MANAGER + " where deleted = false limit ? offset ?";
	@Autowired
	public JdbcManagerRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return this.jdbc.queryForLong(COUNT_MANAGER);
	}
	public static class ManagerRowMapper implements RowMapper<Manager>{

		@Override
		public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new Manager(rs.getLong("id"),rs.getString("username"),rs.getString("password"),rs.getString("truename"),rs.getString("email"),rs.getBoolean("deleted"));
		}
	}
	@Override
	public Manager save(Manager manager) {
		// TODO Auto-generated method stub
		this.jdbc.update(INSERT_MANAGER,manager.getUserName(),manager.getPassword(),manager.getTrueName(),manager.getEmail(),manager.getDeleted());
		return manager;
	}

	@Override
	public Manager findOne(long id) {
		// TODO Auto-generated method stub
		Manager manager = null;
		try {
			manager = jdbc.queryForObject(SELECT_MANAGER + "where id = ?", new ManagerRowMapper());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return manager;
	}

	@Override
	public Manager findByUserName(String userName) {
		// TODO Auto-generated method stub
		Manager manager = null;
		try {
			manager = jdbc.queryForObject(SELECT_MANAGER + "where username = ?", new ManagerRowMapper(), userName);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return manager;
	}

	@Override
	public Manager findByUserName(String userName, String password) {
		// TODO Auto-generated method stub
		Manager manager = null;
		try {
			manager = jdbc.queryForObject(SELECT_MANAGER + "where username = ? and password = ?", new ManagerRowMapper(), userName, password);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return manager;
	}

	@Override
	public PaginationSupport<Manager> findPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		int totalCount = (int)count();
		int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo,pageSize);
		if(totalCount < 1)
			return new PaginationSupport<Manager>(new ArrayList<Manager>(0),0);
		List<Manager> items = jdbc.query(SELECT_PAGE_MANAGERS, new ManagerRowMapper(),pageSize, startIndex);
		PaginationSupport<Manager> ps = new PaginationSupport<Manager>(items, totalCount, pageSize, startIndex);
		return ps;
	}

	@Override
	public Manager updateProfile(String userName, String password, String trueName, String email, String phoneNo) {
		// TODO Auto-generated method stub
		long id = this.jdbc.queryForInt("select id from Manager where username = " + userName + " password = " + password);
		this.jdbc.update("UPDATE_MANAGER",userName,password,trueName,email,phoneNo);
		return jdbc.queryForObject(SELECT_MANAGER + "where id = ?", new ManagerRowMapper(), id);
	}

	@Override
	public Manager deleteByName(String userName) {
		// TODO Auto-generated method stub
		this.jdbc.update(DELETE_MANAGER + "where username = ?",userName);
		return null;
	}

	@Override
	public Manager deleteByID(Long id) {
		// TODO Auto-generated method stub
		this.jdbc.update(DELETE_MANAGER + "where id = ?",id);
		return null;
	}
	@Override
	public List<Manager> findAll() {
		// TODO Auto-generated method stub
		return jdbc.query(SELECT_MANAGER + " where deleted=false" + " order by id", new ManagerRowMapper());
	}
	@Override
	public Manager modify(long managerId,Manager modifiedManager) {
		this.jdbc.update(UPDATE_MANAGER,modifiedManager.getUserName(),modifiedManager.getPassword(),modifiedManager.getTrueName(),modifiedManager.getEmail(),modifiedManager.getDeleted(),managerId);
		return this.jdbc.queryForObject(SELECT_MANAGER + "where id = ?", new ManagerRowMapper(), managerId);
	}

}
