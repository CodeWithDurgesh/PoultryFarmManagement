package in.lucknow.poultryfarm2.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import in.lucknow.poultryfarm2.dto.BatchAllocationDTO;

public class BatchAllocationRowMapper implements RowMapper<BatchAllocationDTO> {
	@Override
	public BatchAllocationDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BatchAllocationDTO dto = new BatchAllocationDTO();
		dto.setId(rs.getLong("id"));
		dto.setDate1(rs.getString("date1"));
		dto.setBatchno(rs.getString("batchno"));
		dto.setBranch(rs.getString("branch"));
		dto.setFarmer(rs.getLong("farmer"));
		dto.setInspector(rs.getString("inspector"));
		dto.setRemark(rs.getString("remark"));
		dto.setCat(rs.getString("cat"));
		dto.setProduct(rs.getString("product"));
		dto.setQty(rs.getDouble("qty"));
		dto.setWorkdone(rs.getString("workdone"));
		dto.setStatus(rs.getString("status"));
		dto.setDate2(rs.getString("date2"));
		return dto;
	}
}
