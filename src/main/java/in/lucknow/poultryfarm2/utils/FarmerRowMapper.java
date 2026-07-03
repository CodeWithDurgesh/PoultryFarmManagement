package in.lucknow.poultryfarm2.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import in.lucknow.poultryfarm2.dto.FarmerDTO;

public class FarmerRowMapper implements RowMapper<FarmerDTO> {

	@Override
	public FarmerDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		FarmerDTO f = new FarmerDTO();
		f.setId(rs.getInt("id"));
		f.setArea(rs.getInt("area"));
		f.setBranch(rs.getInt("branch"));
		f.setName(rs.getString("name"));
		f.setContact(rs.getString("contact"));
		return f;
	}

}
