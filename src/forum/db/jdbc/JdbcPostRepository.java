package forum.db.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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

	private static final String INSERT_POST = "insert into Post (poster, postname, message, postedTime, follow, click, topped, deleted) values (?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String SELECT_POST = "select p.id, pt.id as posterId, pt.username, pt.password, pt.truename, pt.email, pt.locked, pt.deleted, p.postname, p.message, p.postedTime, p.follow, p.click, p.topped, p.deleted from Post p, Poster pt where p.poster=pt.id and p.deleted=false";
	
	private static final String SELECT_POST_BY_ID = SELECT_POST + " and p.id=?";
	
	private static final String SELECT_POST_BY_POSTER_ID_TOPPED = SELECT_POST + " and pt.id=? and p.topped=true order by p.postedTime desc";
	private static final String SELECT_POST_BY_POSTER_ID_UNTOPPED = SELECT_POST + " and pt.id=? and p.topped=false order by p.postedTime desc";
	
	private static final String UPDATE_POST = "update Post set postname=?, message=?, postedTime=? where deleted=false and id=?";
	private static final String UPDATE_POST_FOLLOW = "update Post set follow=? where deleted=false and id=?";
	private static final String UPDATE_POST_CLICK = "update Post set click=? where deleted=false and id=?";
	
	private static final String SELECT_PAGE_POST_BY_POSTER_ID_TOPPED = SELECT_POST_BY_POSTER_ID_TOPPED + " limit ? offset  ?";
	private static final String SELECT_PAGE_POST_BY_POSTER_ID_UNTOPPED = SELECT_POST_BY_POSTER_ID_UNTOPPED + " limit ? offset  ?";
	private static final String SELECT_PAGE_POSTS_TOPPED = SELECT_POST + " and p.topped=true order by p.postedTime desc limit ? offset  ?";
	private static final String SELECT_PAGE_POSTS_UNTOPPED = SELECT_POST + " and p.topped=false order by p.postedTime desc limit ? offset  ?";
	private static final String SELECT_PAGE_POSTS_TOPPED_UNLIMITED = SELECT_POST + " and p.topped=true order by p.postedTime desc";

	
	private static final String DELETE_POST = "update Post set deleted = true";
	private static final String SET_POST_TOP = "update Post set topped = ?";

	private static class PostRowMapper implements RowMapper<Post> {
		public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
			long posterId = rs.getLong("posterId");
			String userName = rs.getString("username");
			String password = rs.getString("password");
			String trueName = rs.getString("truename");
			String email = rs.getString("email");
			Poster poster = new Poster(posterId, userName, password, trueName, email);
			
			long id = rs.getLong("id");
			String postname = rs.getString("postname");
			String message = rs.getString("message");
			Timestamp postedTime = rs.getTimestamp("postedTime");
			int follow = rs.getInt("follow");
			int click = rs.getInt("click");
			Boolean topped = rs.getBoolean("topped");
			Boolean deleted = rs.getBoolean("deleted");
			return new Post(id, poster, postname, message, postedTime, follow, click, topped, deleted);
		}
	}

	@Override
	public long count() {
		return jdbc.queryForLong("select count(id) from Post where deleted=false");
	}
	
	@Override
	public long countByPosterId(long posterId) {
		return jdbc.queryForLong("select count(id) from Post where deleted=false and poster=?", posterId);
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
	public List<Post> findAll() {
		List<Post> posts = jdbc.query(SELECT_POST, new PostRowMapper());
		return posts;
	}

	@Override
	public Post save(Post post) {
		jdbc.update(INSERT_POST, post.getPoster().getId(), post.getPostName(), post.getMessage(), post.getPostedTime(), post.getFollow(), post.getClick(), post.getTopped(), post.getDeleted());
		return post;
	}

	@Override
	public List<Post> findByPosterId(long posterId) {
		List<Post> posts = jdbc.query(SELECT_POST_BY_POSTER_ID_TOPPED, new PostRowMapper(), posterId);
		posts.addAll(jdbc.query(SELECT_POST_BY_POSTER_ID_UNTOPPED, new PostRowMapper(), posterId));
		
		return posts;
	}

	@Override
	public void deletePost(long id) {
		jdbc.update(DELETE_POST + " where id=?", id);
	}
	
	@Override
	public void setTopOrNo(long id, boolean topped) {
		jdbc.update(SET_POST_TOP + " where id=?", topped, id);
	}

	@Override
	public Post updatePost(Post post, long id) {
		jdbc.update(UPDATE_POST, post.getPostName(), post.getMessage(), post.getPostedTime(), id);
		return post;
	}

	@Override
	public PaginationSupport<Post> findPage(int pageNo, int pageSize) {
		int totalCount = (int) count();
		int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
		if (totalCount < 1)
			return new PaginationSupport<Post>(new ArrayList<Post>(0), 0);
		
		List<Post> toppedItems = jdbc.query(SELECT_PAGE_POSTS_TOPPED_UNLIMITED, new PostRowMapper());
		List<Post> items = jdbc.query(SELECT_PAGE_POSTS_TOPPED, new PostRowMapper(), pageSize, startIndex);
		
		int toppedPages, notToppedPageNo, notToppedStartIndex = 0;
		if(toppedItems.size() == 0) {
			toppedPages = 0;
			notToppedPageNo = pageNo;
			notToppedStartIndex = 0;
		}else if(toppedItems.size() % pageSize != 0) {
			toppedPages = (toppedItems.size() / pageSize) + 1;
			notToppedPageNo = pageNo - toppedPages + 1;
			if(toppedPages == pageNo) {
				notToppedStartIndex = 0;
			}else {
				notToppedStartIndex = PaginationSupport.convertFromPageToStartIndex(notToppedPageNo, pageSize) - (toppedItems.size() % pageSize);
			}
		}else {
			toppedPages = toppedItems.size() / pageSize;
			notToppedPageNo = pageNo - toppedPages;
			notToppedStartIndex = PaginationSupport.convertFromPageToStartIndex(notToppedPageNo, pageSize);
		}
		
		if(items.size() < pageSize) {
			if(pageNo >= toppedPages) {
				items.addAll(jdbc.query(SELECT_PAGE_POSTS_UNTOPPED, new PostRowMapper(), pageSize-items.size(), notToppedStartIndex));
			}
		}
		PaginationSupport<Post> p = new PaginationSupport<Post>(items, totalCount, pageSize, startIndex);
		return p;
	}

	@Override
	public void updateFollow(Post post) {
		jdbc.update(UPDATE_POST_FOLLOW, post.getFollow(), post.getId());
	}

	@Override
	public void updateClick(Post post) {
		jdbc.update(UPDATE_POST_CLICK, post.getClick(), post.getId());
	}

	@Override
	public PaginationSupport<Post> findPageByPosterId(long posterId, int pageNo, int pageSize) {
		int totalCount = (int) countByPosterId(posterId);
		int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
		if (totalCount < 1)
			return new PaginationSupport<Post>(new ArrayList<Post>(0), 0);
		
		List<Post> items = jdbc.query(SELECT_PAGE_POST_BY_POSTER_ID_TOPPED, new PostRowMapper(), posterId, pageSize, startIndex);
		items.addAll(jdbc.query(SELECT_PAGE_POST_BY_POSTER_ID_UNTOPPED, new PostRowMapper(), posterId, pageSize, startIndex));
		PaginationSupport<Post> p = new PaginationSupport<Post>(items, totalCount, pageSize, startIndex);
		return p;
	}

}
