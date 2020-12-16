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

import forum.db.ReplyRepository;
import forum.entity.Poster;
import forum.entity.Reply;
import forum.web.PaginationSupport;

@Repository
public class JdbcReplyRepository implements ReplyRepository {

	private JdbcTemplate jdbc;

	@Autowired
	public JdbcReplyRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	private static final String INSERT_REPLY = "insert into Reply (poster, postId, message, postedTime) values (?, ?, ?, ?)";
	
	private static final String SELECT_REPLY = "select r.id, pt.id as posterId, pt.username, pt.password, pt.truename, pt.email, r.postId, r.message, r.postedTime, r.deleted from Reply r, Poster pt where r.poster=pt.id and r.deleted=0";
	private static final String SELECT_REPLY_BY_ID = SELECT_REPLY + " and r.id=?";
	private static final String SELECT_REPLY_BY_POST_ID = SELECT_REPLY + " and r.postId=?";
	private static final String SELECT_PAGE_REPLYS = SELECT_REPLY + " order by r.postedTime desc limit ? offset  ?";
	
	private static final String DELETE_REPLY = "update Reply set deleted = 1";
	
	private static class ReplyRowMapper implements RowMapper<Reply> {
		public Reply mapRow(ResultSet rs, int rowNum) throws SQLException {
			long posterId = rs.getLong("posterId");
			String userName = rs.getString("username");
			String password = rs.getString("password");
			String trueName = rs.getString("truename");
			String email = rs.getString("email");
			Poster poster = new Poster(posterId, userName, password, trueName, email);
			
			return new Reply(rs.getLong("id"), poster, rs.getLong("postId"), rs.getString("message"), rs.getTimestamp("postedTime"));
		}
	}
	
	@Override
	public long count() {
		return jdbc.queryForLong("select count(id) from Reply where deleted=0");
	}

	@Override
	public Reply findOne(long id) {
		Reply reply = null;
		try {
			reply = jdbc.queryForObject(SELECT_REPLY_BY_ID, new ReplyRowMapper(), id);
		} catch (DataAccessException e) {
		}
		return reply;
	}

	@Override
	public Reply save(Reply reply) {
		jdbc.update(INSERT_REPLY, reply.getPoster(), reply.getPostId(), reply.getMessage(), reply.getPostedTime());
		return reply;
	}

	@Override
	public List<Reply> findByPostId(long postId) {
		return jdbc.query(SELECT_REPLY_BY_POST_ID, new ReplyRowMapper(), postId);
	}

	@Override
	public void delete(long id) {
		jdbc.update(DELETE_REPLY + "where id=?", id);
	}

	@Override
	public PaginationSupport<Reply> findPage(int pageNo, int pageSize) {
		int totalCount = (int) count();
		int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
		if (totalCount < 1)
			return new PaginationSupport<Reply>(new ArrayList<Reply>(0), 0);
		
		List<Reply> items = jdbc.query(SELECT_PAGE_REPLYS, new ReplyRowMapper());
		PaginationSupport<Reply> r = new PaginationSupport<Reply>(items, totalCount, pageSize, startIndex);
		return r;
	}

}
