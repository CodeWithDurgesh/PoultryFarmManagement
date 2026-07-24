package in.lucknow.poultryfarm2.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import in.lucknow.poultryfarm2.dto.AllocationFeedMedDTO;

public class AllocationFeedMedRowMapper implements RowMapper<AllocationFeedMedDTO> {

	@Override
	public AllocationFeedMedDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

		AllocationFeedMedDTO dto = new AllocationFeedMedDTO();

		dto.setId(rs.getLong("id"));
		dto.setDate1(rs.getString("date1"));
		dto.setInvoice(rs.getString("invoice"));
		dto.setBranch(rs.getString("branch"));
		dto.setFarmer(rs.getLong("farmer"));
		dto.setBatch(rs.getString("batch"));
		dto.setRemark(rs.getString("remark"));
		dto.setCat(rs.getString("cat"));
		dto.setProduct(rs.getString("product"));
		dto.setQty(rs.getDouble("qty"));
		dto.setUnit(rs.getString("unit"));
		dto.setRate(rs.getDouble("rate"));
		dto.setTotal(rs.getDouble("total"));
		dto.setDcno(rs.getString("dcno"));
		dto.setVehicleno(rs.getString("vehicleno"));
		dto.setTransferby(rs.getString("transferby"));
		dto.setTransfercharge(rs.getString("transfercharge") != null ? rs.getString("transfercharge") : "");
		dto.setAgainreq(rs.getString("againreq"));
		dto.setVendor(rs.getString("vendor"));
		// dto.setGodown(rs.getString("godown"));

		return dto;
	}
}
