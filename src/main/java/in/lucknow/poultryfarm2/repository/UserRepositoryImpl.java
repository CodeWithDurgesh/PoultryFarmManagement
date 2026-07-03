package in.lucknow.poultryfarm2.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.lucknow.poultryfarm2.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings("deprecation")
	@Override
	public User login(String username, String password) {

		String sql = "SELECT id,name,user,pwd,status,branches,companies,role,wdays FROM user WHERE user = ? AND pwd = ?";

		List<User> users = jdbcTemplate.query(sql, new Object[] { username, password }, (rs, rowNum) -> {
			User u = new User();
			u.setId(rs.getInt("id"));
			u.setName(rs.getString("name"));
			u.setUser(rs.getString("user"));
			u.setPwd(rs.getString("pwd"));
			u.setStatus(rs.getString("status"));
			u.setBranches(rs.getString("branches"));
			u.setCompanies(rs.getString("companies"));
			u.setRole(rs.getString("role"));
			u.setWdays(rs.getString("wdays"));

			return u;
		});

		return users.isEmpty() ? null : users.get(0);
	}
}
