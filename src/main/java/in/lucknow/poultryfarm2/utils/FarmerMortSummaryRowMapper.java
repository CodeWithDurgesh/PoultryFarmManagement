package in.lucknow.poultryfarm2.utils;

import org.springframework.jdbc.core.RowMapper;

import in.lucknow.poultryfarm2.dto.FarmerMortSummaryDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FarmerMortSummaryRowMapper implements RowMapper<FarmerMortSummaryDTO> {

	@Override
	public FarmerMortSummaryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		FarmerMortSummaryDTO obj = new FarmerMortSummaryDTO();
		obj.setId(rs.getInt("id"));
		obj.setDate1(rs.getString("date1"));
		obj.setReason(rs.getString("reason"));
		obj.setCweight(rs.getString("cweight"));
		obj.setTotalMort(rs.getInt("total_mort"));
		return obj;
	}
}
