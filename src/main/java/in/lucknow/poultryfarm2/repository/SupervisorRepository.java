package in.lucknow.poultryfarm2.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.lucknow.poultryfarm2.dto.SupervisorResponse;
import in.lucknow.poultryfarm2.utils.SupervisorRowMapper;

@Repository
public class SupervisorRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<SupervisorResponse> getSupervisorDetails(String name) {

//		String sql = """
//				    SELECT isemp, branch, tdate
//				    FROM supervisor
//				    WHERE name = ?
//				""";

		String sql = "SELECT isemp, branch, tdate FROM supervisor WHERE id = ?";

		return jdbcTemplate.query(sql, new SupervisorRowMapper(), name);
	}
}
