package in.lucknow.poultryfarm2.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.lucknow.poultryfarm2.dto.FarmerDTO;
import in.lucknow.poultryfarm2.utils.FarmerRowMapper;

@Repository
public class FarmerRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<FarmerDTO> findByArea(String area) {
		String sql = "SELECT * FROM farmer_master WHERE area = ?";
		return jdbcTemplate.query(sql, new FarmerRowMapper(), area);
	}

	public List<FarmerDTO> findByBranch(String branch) {
		String sql = "SELECT * FROM farmer_master WHERE branch = ?";
		return jdbcTemplate.query(sql, new FarmerRowMapper(), branch);
	}

	public List<FarmerDTO> findById(String id) {
		String sql = "SELECT * FROM farmer_master WHERE id = ?";
		return jdbcTemplate.query(sql, new FarmerRowMapper(), id);
	}

}
