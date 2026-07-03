package in.lucknow.poultryfarm2.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import in.lucknow.poultryfarm2.dto.SupervisorResponse;

public class SupervisorRowMapper implements RowMapper<SupervisorResponse> {

	@Override
	public SupervisorResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		SupervisorResponse obj = new SupervisorResponse();
		obj.setIsemp(rs.getString("isemp"));
		obj.setBranch(rs.getInt("branch"));
		obj.setTdate(rs.getInt("tdate"));
		return obj;
	}
}
