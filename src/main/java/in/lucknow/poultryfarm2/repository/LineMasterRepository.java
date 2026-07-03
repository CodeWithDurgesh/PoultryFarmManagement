package in.lucknow.poultryfarm2.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.lucknow.poultryfarm2.dto.LineMasterDTO;
import in.lucknow.poultryfarm2.utils.LineMasterRowMapper;

@Repository
public class LineMasterRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<LineMasterDTO> findById(String id) {
		String sql = "SELECT id,name,company,branch FROM linemaster WHERE id = ?";
		return jdbcTemplate.query(sql, new LineMasterRowMapper(), id);
	}
}
