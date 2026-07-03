package in.lucknow.poultryfarm2.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.lucknow.poultryfarm2.dto.DiseaseDTO;

@Repository
public class DiseaseRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<DiseaseDTO> getAllDiseases() {

		String sql = "SELECT id, name FROM dieseas ORDER BY name ASC";

		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			DiseaseDTO dto = new DiseaseDTO();
			dto.setId(rs.getLong("id"));
			dto.setName(rs.getString("name"));
			return dto;
		});
	}
}
