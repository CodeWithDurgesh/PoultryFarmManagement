package in.lucknow.poultryfarm2.utils;

import org.springframework.jdbc.core.RowMapper;

import in.lucknow.poultryfarm2.dto.FeedConsumeSummaryDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedConsumeRowMapper implements RowMapper<FeedConsumeSummaryDTO> {

	@Override
	public FeedConsumeSummaryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

		FeedConsumeSummaryDTO obj = new FeedConsumeSummaryDTO();

		obj.setFeed(rs.getString("feed"));
		obj.setTotalConsume(rs.getInt("total_consume"));

		return obj;
	}
}
