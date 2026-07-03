package in.lucknow.poultryfarm2.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.lucknow.poultryfarm2.dto.FarmerAlcReportDTO;
import in.lucknow.poultryfarm2.dto.FarmerMortSummaryDTO;
import in.lucknow.poultryfarm2.dto.FeedConsumeSummaryDTO;
import in.lucknow.poultryfarm2.utils.FarmerAlcReportRowMapper;
import in.lucknow.poultryfarm2.utils.FarmerMortSummaryRowMapper;
import in.lucknow.poultryfarm2.utils.FeedConsumeRowMapper;
import in.lucknow.poultryfarm2.utils.TotalConsumeRowMapper;

@Repository
public class FarmerAlcRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings("deprecation")
	public Integer fetchTotalConsume(String batch, String feed) {
		String sql = "SELECT COALESCE(SUM(consume),0) AS totalConsume "
				+ "FROM farmer_alc_report WHERE batch=? AND feed=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { batch, feed }, new TotalConsumeRowMapper());
	}

	public List<FarmerAlcReportDTO> findByBatch(String batch) {
		String sql = "SELECT id,date1,stime,etime,okm,ckm,tkm,line,farmer,mdate,mort,mortp,cweight,feed,consume,suggestion,reason,batch,date2,lat,lang,time,mortpic,distance,culls,temprature,eaddress,fstatus,eremarks "
				+ "FROM farmer_alc_report WHERE batch = ? ORDER BY date2 ASC";
		return jdbcTemplate.query(sql, new FarmerAlcReportRowMapper(), batch);
	}

	public List<FarmerMortSummaryDTO> getMortSummary(String batch, String date2) {

		String sql = "SELECT SUM(mort) AS total_mort, date1, id, reason, cweight " + "FROM farmer_alc_report "
				+ "WHERE batch = ? AND date2 = ? " + "GROUP BY date1, id, reason, cweight";

		return jdbcTemplate.query(sql, new FarmerMortSummaryRowMapper(), batch, date2);
	}

	public List<FeedConsumeSummaryDTO> getFeedConsumeSummary(String batch, String date2) {

		String sql = "SELECT SUM(consume) AS total_consume, feed " + "FROM farmer_alc_report "
				+ "WHERE batch = ? AND mort = '' AND date2 = ? " + "GROUP BY feed";

		return jdbcTemplate.query(sql, new FeedConsumeRowMapper(), batch, date2);
	}

	public int save(FarmerAlcReportDTO report) {

		String sqlQUERY = "INSERT INTO farmer_alc_report " + "(date1, stime, etime, okm, ckm, tkm, line, farmer, "
				+ "mdate, mort, mortp, cweight, feed, consume, suggestion, "
				+ "reason, batch, date2, lat, lang, time, mortpic, distance, "
				+ "culls, temprature, eaddress, fstatus, eremarks) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		return jdbcTemplate.update(sqlQUERY, report.getDate1(), report.getStime(), report.getEtime(), report.getOkm(),
				report.getCkm(), report.getTkm(), report.getLine(), report.getFarmer(), report.getMdate(),
				report.getMort(), report.getMortp(), report.getCweight(), report.getFeed(), report.getConsume(),
				report.getSuggestion(), report.getReason(), report.getBatch(), report.getDate2(), report.getLat(),
				report.getLang(), report.getTime(), report.getMortpic(), report.getDistance(), report.getCulls(),
				report.getTemprature(), report.getEaddress(), report.getFstatus(), report.getEremarks());
	}

	public Integer getMaxIdFromFarmerAlcReport() {
		String sql = "Select max(id) from farmer_alc_report";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

}
