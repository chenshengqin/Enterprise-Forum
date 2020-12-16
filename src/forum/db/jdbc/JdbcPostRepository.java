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

import forum.db.PostRepository;
import forum.entity.Post;
import forum.entity.Poster;
import forum.web.PaginationSupport;

@Repository
public class JdbcPostRepository implements PostRepository {

	private JdbcTemplate jdbc;

	@Autowired
	public JdbcPostRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	private static final String INSERT_POST = "insert into Post (poster, postname, message, postedTime) values (?, ?, ?, ?)";
	
	private static final String SELECT_POST = "select p.id, pt.id as posterId, pt.username, pt.password, pt.truename, pt.email, pt.locked, pt.deleted, p.postname, p.message, p.postedTime, p.follow, p.click, p.topped, p.deleted from Post p, Poster pt where p.poster=pt.id and p.deleted=0";
	
	private static final String SELECT_POST_BY_ID = SELECT_POST + " and p.id=?";
	
	private static final String SELECT_POST_BY_POSTER_ID_TOPPED = SELECT_POST + " and pt.id=? and pt.topped=1 order by p.postedTime desc";
	private static final String SELECT_POST_BY_POSTER_ID_UNTOPPED = SELECT_POST + " and pt.id=? and pt.topped=0 order by p.postedTime desc";
	
	private static final String UPDATE_POST = "update Post set postname=?, message=?, postedTime=? where deleted=0 and id=?";
	private static final String UPDATE_POST_FOLLOW = "update Post set follow=? where deleted=0 and id=?";
	private static final String UPDATE_POST_CLICK = "update Post set click=? where deleted=0 and id=?";
	
	private static final String SELECT_PAGE_POSTS_TOPPED = SELECT_POST + " and pt.topped=1 order by p.postedTime desc limit ? offset  ?";
	private static final String SELECT_PAGE_POSTS_UNTOPPED = SELECT_POST + " and pt.topped=0 order by p.postedTime desc limit ? offset  ?";
	
	private static final String DELETE_POST = "update Post set deleted = 1";

	private static class PostRowMapper implements RowMapper<Post> {
		public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
			long posterId = rs.getLong("posterId");
			String userName = rs.getString("username");
			String password = rs.getString("password");
			String trueName = rs.getString("truename");
			String email = rs.getString("email");
			Poster poster = new Poster(posterId, userName, password, trueName, email);
			return new Post(rs.getLong("id"), poster, rs.getString("postname"), rs.getString("message"),
					rs.getTimestamp("postedTime"), rs.getInt("follow"), rs.getInt("click"), rs.getBoolean("topped"),
					rs.getBoolean("deleted"));
		}
	}

	@Override
	public long count() {
		return jdbc.queryForLong("select count(id) from Post where deleted=0");
	}

	@Override
	public Post findOne(long id) {
		Post post = null;
		try {
			post = jdbc.queryForObject(SELECT_POST_BY_ID, new PostRowMapper(), id);
		} catch (DataAccessException e) {
		}
		return post;
	}

	@Override
	public Post save(Post post) {
		jdbc.update(INSERT_POST, post.getPoster(), post.getPostName(), post.getMessage(), post.getPostedTime());
		return post;
	}

	@Override
	public List<Post> findByPosterId(long posterId) {
		List<Post> posts = jdbc.query(SELECT_POST_BY_POSTER_ID_TOPPED, new PostRowMapper());
		posts.addAll(jdbc.query(SELECT_POST_BY_POSTER_ID_UNTOPPED, new PostRowMapper()));
		
		return posts;
	}

	@Override
	public void delete(long id) {
		jdbc.update(DELETE_POST + "where id=?", id);
	}

	@Override
	public Post update(Post post, long id) {
		jdbc.update(UPDATE_POST, post.getPostName(), post.getMessage(), post.getPostedTime(), id);
		return post;
	}

	@Override
	public PaginationSupport<Post> findPage(int pageNo, int pageSize) {
		int totalCount = (int) count();
		int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
		if (totalCount < 1)
			return new PaginationSupport<Post>(new ArrayList<Post>(0), 0);
		
		List<Post> items = jdbc.query(SELECT_PAGE_POSTS_TOPPED, new PostRowMapper());
		items.addAll(jdbc.query(SELECT_PAGE_POSTS_UNTOPPED, new PostRowMapper()));
		PaginationSupport<Post> p = new PaginationSupport<Post>(items, totalCount, pageSize, startIndex);
		return p;
	}

	@Override
	public void updateFollow(long id) {
		Post post = findOne(id);
		jdbc.update(UPDATE_POST_FOLLOW, post.getFollow() + 1, id);
	}

	@Override
	public void updateClick(long id) {
		Post post = findOne(id);
		jdbc.update(UPDATE_POST_CLICK, post.getClick() + 1, id);
	}

}
