package in.lucknow.poultryfarm2.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.lucknow.poultryfarm2.dto.AllocationFeedMedDTO;
import in.lucknow.poultryfarm2.utils.AllocationFeedMedRowMapper;
import in.lucknow.poultryfarm2.utils.TotalQtyRowMapper;

@Repository
public class AllocationFeedMedRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings("deprecation")
	public List<AllocationFeedMedDTO> getAllocationData(String batchNumber) {

		String sql = "SELECT id,date1,invoice,branch,farmer,batch,remark,cat,product,qty,unit,rate,total,dcno,vehicleno,transferby,transfercharge,againreq,vendor "
				+ "FROM allocation_feed_med " + "WHERE batch = ? AND cat = ?";

		return jdbcTemplate.query(sql, new Object[] { batchNumber, "Chicks" }, new AllocationFeedMedRowMapper());
	}

	@SuppressWarnings("deprecation")
	public Integer fetchTotalQty(String batch, String product) {

		String sql = "SELECT SUM(qty) as totalQty FROM allocation_feed_med WHERE batch=? AND product=?";

		return jdbcTemplate.queryForObject(sql, new Object[] { batch, product }, new TotalQtyRowMapper());
	}

	public List<AllocationFeedMedDTO> findByBatchAndCat(String batch, String cat) {
		String sql = "SELECT * FROM allocation_feed_med WHERE batch = ? AND cat = ?";
		return jdbcTemplate.query(sql, new AllocationFeedMedRowMapper(), batch, cat);
	}
}
