package forum.db.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import forum.db.PosterRepository;
import forum.entity.Poster;
import forum.web.PaginationSupport;

@Repository
public class JdbcPosterRepository implements PosterRepository {

	private JdbcTemplate jdbc;

	@Autowired
	public JdbcPosterRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	private static final String INSERT_POSTER = "insert into Poster (username, password, truename, email) values (?, ?, ?, ?)";
	
	private static final String SELECT_POSTER = "select id, username, truename, email, locked, deleted from Poster";
	
	private static final String SELECT_PAGE_POSTERS = SELECT_POSTER + " where deleted=0 limit ? offset  ?";
	
	private static final String DELETE_POSTER = "update Poster set deleted = 1";

	private static class PosterRowMapper implements RowMapper<Poster> {
		public Poster mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Poster(rs.getLong("id"), rs.getString("username"), rs.getString("password"),
					rs.getString("truename"), rs.getString("email"), rs.getBoolean("locked"), rs.getBoolean("deleted"));
		}
	}

	@Override
	public long count() {
		return jdbc.queryForLong("select count(id) from Poster where deleted=0");
	}

	@Override
	public Poster save(Poster poster) {
		jdbc.update(INSERT_POSTER, poster.getUserName(), poster.getPassword(), poster.getTrueName(), poster.getEmail());
		return poster;
	}

	@Override
	public Poster findOne(long id) {
		Poster poster = null;
		try {
			poster = jdbc.queryForObject(SELECT_POSTER + " where deleted=0 and id=?", new PosterRowMapper(), id);
		} catch (DataAccessException e) {
		}
		return poster;
	}

	@Override
	public Poster findByUserName(String userName) {
		Poster poster = null;
		try {
			poster = jdbc.queryForObject(SELECT_POSTER + " where deleted=0 and username=?", new PosterRowMapper(), userName);
		} catch (DataAccessException e) {
		}
		return poster;
	}

	@Override
	public Poster findByUserName(String userName, String password) {
		Poster poster = null;
		try {
			poster = jdbc.queryForObject(SELECT_POSTER + " where deleted=0 and username=? and password=?", new PosterRowMapper(), userName, password);
		} catch (DataAccessException e) {
		}
		return poster;
	}

	@Override
	public List<Poster> findAll() {
		return jdbc.query(SELECT_POSTER + " where deleted=0" + " order by id", new PosterRowMapper());
	}

	@Override
	public PaginationSupport<Poster> findPage(int pageNo, int pageSize) {
		int totalCount = (int) count();
		int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
		if (totalCount < 1)
			return new PaginationSupport<Poster>(new ArrayList<Poster>(0), 0);

		List<Poster> items = jdbc.query(SELECT_PAGE_POSTERS, new PosterRowMapper(), pageSize, startIndex);
		PaginationSupport<Poster> ps = new PaginationSupport<Poster>(items, totalCount, pageSize, startIndex);
		return ps;
	}

	@Override
	public void deletePoster(long id) {
		jdbc.update(DELETE_POSTER + "where id=?", id);
	}

}
