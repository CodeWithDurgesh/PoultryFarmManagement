package in.lucknow.poultryfarm2.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.lucknow.poultryfarm2.utils.TotalQtyRowMapper;

@Repository
public class FeedTransferRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings("deprecation")
	public Integer fetchTotalQtyToFarmer(String toFarmer, String product) {

		String sql = "SELECT COALESCE(SUM(qty),0) AS totalQty " + "FROM feedtransfer WHERE to_farmer=? AND product=?";

		return jdbcTemplate.queryForObject(sql, new Object[] { toFarmer, product }, new TotalQtyRowMapper());
	}

	@SuppressWarnings("deprecation")
	public Integer fetchTotalQtyFromFarmer(String frFarmer, String product) {

		String sql = "SELECT COALESCE(SUM(qty),0) AS totalQty " + "FROM feedtransfer WHERE fr_farmer=? AND product=?";

		return jdbcTemplate.queryForObject(sql, new Object[] { frFarmer, product }, new TotalQtyRowMapper());
	}
}
