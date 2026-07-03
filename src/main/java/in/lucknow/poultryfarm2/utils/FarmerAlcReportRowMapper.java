package in.lucknow.poultryfarm2.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import in.lucknow.poultryfarm2.dto.FarmerAlcReportDTO;

public class FarmerAlcReportRowMapper implements RowMapper<FarmerAlcReportDTO> {

	@Override
	public FarmerAlcReportDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

		FarmerAlcReportDTO obj = new FarmerAlcReportDTO();
		obj.setId(rs.getInt("id"));
		obj.setDate1(rs.getString("date1"));
		obj.setStime(rs.getString("stime"));
		obj.setEtime(rs.getString("etime"));
		obj.setOkm(rs.getString("okm"));
		obj.setCkm(rs.getString("ckm"));
		obj.setTkm(rs.getString("tkm"));
		obj.setLine(rs.getString("line"));
		obj.setFarmer(rs.getString("farmer"));
		obj.setMdate(rs.getString("mdate"));
		obj.setMort(rs.getString("mort"));
		obj.setMortp(rs.getString("mortp"));
		obj.setCweight(rs.getString("cweight"));
		obj.setFeed(rs.getString("feed"));
		obj.setConsume(rs.getString("consume"));
		obj.setSuggestion(rs.getString("suggestion"));
		obj.setReason(rs.getString("reason"));
		obj.setBatch(rs.getString("batch"));
		obj.setDate2(rs.getString("date2"));
		obj.setLat(rs.getString("lat"));
		obj.setLang(rs.getString("lang"));
		obj.setTime(rs.getString("time"));
		obj.setMortpic(rs.getString("mortpic"));
		obj.setDistance(rs.getString("distance"));
		obj.setCulls(rs.getString("culls"));
		obj.setTemprature(rs.getString("temprature"));
		obj.setEaddress(rs.getString("eaddress"));
		obj.setFstatus(rs.getString("fstatus"));
		obj.setEremarks(rs.getString("eremarks"));
		return obj;
	}
}
