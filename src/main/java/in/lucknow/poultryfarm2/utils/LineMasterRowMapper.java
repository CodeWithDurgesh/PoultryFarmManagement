package in.lucknow.poultryfarm2.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import in.lucknow.poultryfarm2.dto.LineMasterDTO;

public class LineMasterRowMapper implements RowMapper<LineMasterDTO> {

	@Override
	public LineMasterDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		LineMasterDTO dto = new LineMasterDTO();
		dto.setId(rs.getInt("id"));
		dto.setName(rs.getString("name"));
		dto.setCompany(rs.getString("company"));
		dto.setBranch(rs.getInt("branch"));
		return dto;
	}
}
