package in.lucknow.poultryfarm2.repository;

import in.lucknow.poultryfarm2.dto.BatchAllocationDTO;
import in.lucknow.poultryfarm2.dto.BatchSummaryDTO;
import in.lucknow.poultryfarm2.utils.BatchAllocationRowMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BatchAllocationRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings("deprecation")
	public BatchSummaryDTO getBatchSummary(Long farmerId) {

//		String sql = """
//				    SELECT
//				        ba.batchno,
//				        afm.qty,
//				        fr.total_mort,
//				        fr.total_culls,
//				        se.total_sold,
//				                     (afm.qty
//				 - COALESCE(fr.total_mort, 0)
//				 - COALESCE(se.total_sold, 0)
//				 - COALESCE(fr.total_culls, 0)
//				) AS total_balance
//				    FROM (
//				        SELECT *
//				        FROM batch_allocation
//				        WHERE farmer = ?
//				        ORDER BY id DESC
//				        LIMIT 1
//				    ) ba
//
//				    LEFT JOIN allocation_feed_med afm
//				        ON afm.batch = ba.batchno AND afm.cat = 'Chicks'
//
//				    LEFT JOIN (
//				        SELECT batch, SUM(mort) AS total_mort,SUM(culls) AS total_culls
//				        FROM farmer_alc_report
//				        GROUP BY batch
//				    ) fr
//				        ON fr.batch = ba.batchno
//
//				    LEFT JOIN (
//				        SELECT batch, SUM(totalbirds) AS total_sold
//				        FROM saleentry
//				        GROUP BY batch
//				    ) se
//				        ON se.batch = ba.batchno
//				""";
		
		String sql = "SELECT ba.batchno, afm.qty, fr.total_mort, fr.total_culls, se.total_sold, (afm.qty - COALESCE(fr.total_mort, 0) - COALESCE(se.total_sold, 0) - COALESCE(fr.total_culls, 0)) AS total_balance FROM (SELECT * FROM batch_allocation WHERE farmer = ? ORDER BY id DESC LIMIT 1) ba LEFT JOIN allocation_feed_med afm ON afm.batch = ba.batchno AND afm.cat = 'Chicks' LEFT JOIN (SELECT batch, SUM(mort) AS total_mort, SUM(culls) AS total_culls FROM farmer_alc_report GROUP BY batch) fr ON fr.batch = ba.batchno LEFT JOIN (SELECT batch, SUM(totalbirds) AS total_sold FROM saleentry GROUP BY batch) se ON se.batch = ba.batchno";

		return jdbcTemplate.query(sql, new Object[] { farmerId }, rs -> {
			if (!rs.next()) {
				return null;
			}

			return new BatchSummaryDTO(rs.getString("batchno"), rs.getObject("qty") != null ? rs.getInt("qty") : 0,
					rs.getObject("total_mort") != null ? rs.getInt("total_mort") : 0,
					rs.getObject("total_culls") != null ? rs.getInt("total_culls") : 0,
					rs.getObject("total_sold") != null ? rs.getInt("total_sold") : 0,
					rs.getObject("total_balance") != null ? rs.getInt("total_balance") : 0

			);
		});
	}

	public List<BatchAllocationDTO> findOpenBatchesByFarmer(Integer farmerId) {

		String sql = "SELECT id, date1, batchno, branch, farmer, inspector, remark, cat, product, qty, workdone, status, date2 "
				+ "FROM batch_allocation WHERE farmer = ? AND status != 'CLOSE'";

		return jdbcTemplate.query(sql, new Object[] { farmerId }, new BatchAllocationRowMapper());

//		return jdbcTemplate.query(sql, new Object[] { farmerId }, (rs, rowNum) -> {
//			BatchAllocationDTO dto = new BatchAllocationDTO();
//			dto.setId(rs.getLong("id"));
//			dto.setDate1(rs.getString("date1"));
//			dto.setBatchno(rs.getString("batchno"));
//			dto.setBranch(rs.getString("branch"));
//			dto.setFarmer(rs.getLong("farmer"));
//			dto.setInspector(rs.getString("inspector"));
//			dto.setRemark(rs.getString("remark"));
//			dto.setCat(rs.getString("cat"));
//			dto.setProduct(rs.getString("product"));
//			dto.setQty(rs.getDouble("qty"));
//			dto.setWorkdone(rs.getString("workdone"));
//			dto.setStatus(rs.getString("status"));
//			dto.setDate2(rs.getString("date2"));
//			return dto;
//		});
	}

}