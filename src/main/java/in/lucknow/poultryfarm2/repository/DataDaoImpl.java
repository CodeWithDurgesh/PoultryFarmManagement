package in.lucknow.poultryfarm2.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

@Repository
public class DataDaoImpl implements DataDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Map<String, Object>> getFlockTotal(String batch) {
		List<Map<String, Object>> result = new ArrayList<>();
		DataSource ds = jdbcTemplate.getDataSource();
		Connection cn = DataSourceUtils.getConnection(ds);

		try {

			String adate2 = "";

			PreparedStatement ps111 = cn
					.prepareStatement("select * from allocation_feed_med where batch=? and cat='Chicks'");
			ps111.setString(1, batch);
			ResultSet rs111 = ps111.executeQuery();
			if (rs111.next()) {

				adate2 = rs111.getString(18);

				String wdate = "";
				double cweight = 0.0;

				int mort = 0;

				PreparedStatement ps1134 = cn.prepareStatement("select sum(mort) from farmer_alc_report where batch=?");
				ps1134.setString(1, batch);
				ResultSet rs1134 = ps1134.executeQuery();
				while (rs1134.next()) {
					mort += rs1134.getInt(1);

				}

				String branch = "";

				String frms[] = batch.split("/");
				PreparedStatement ps11556 = cn.prepareStatement("select branch from farmer_master where id=?");
				ps11556.setString(1, frms[0]);
				ResultSet rs11556 = ps11556.executeQuery();
				while (rs11556.next()) {
					branch = rs11556.getString(1);

				}

				PreparedStatement ps118 = cn.prepareStatement(
						"select cweight,date2,date1 from farmer_alc_report where batch=? and cweight>0 and id=(select max(id) from farmer_alc_report where batch=? and cweight>0)");
				ps118.setString(1, batch);
				ps118.setString(2, batch);
				ResultSet rs118 = ps118.executeQuery();
				while (rs118.next()) {
					wdate = rs118.getString(2);
					cweight = rs118.getDouble(1);
				}

				double feedcon = 0.0;
				PreparedStatement ps1155 = cn
						.prepareStatement("select sum(consume) from farmer_alc_report where batch=? ");
				ps1155.setString(1, batch);
				ResultSet rs1155 = ps1155.executeQuery();
				while (rs1155.next()) {
					feedcon += rs1155.getDouble(1);
				}

				double medcost = 0.0;
				PreparedStatement ps1167 = cn.prepareStatement(
						"select sum(total) from allocation_feed_med where batch=? and cat='Farm Medicine'");
				ps1167.setString(1, batch);
				ResultSet rs1167 = ps1167.executeQuery();
				while (rs1167.next()) {
					medcost += rs1167.getDouble(1);
				}

				double ccost = 0.0, fcost = 0.0, mgcost = 0.0;

				PreparedStatement ps117 = cn.prepareStatement("select * from gcsetup where branch=? and date1<'"
						+ adate2 + "' and id=(select max(id) from gcsetup where branch=? and date1<'" + adate2 + "')");
				ps117.setString(1, branch);
				ps117.setString(2, branch);
				ResultSet rs117 = ps117.executeQuery();
				while (rs117.next()) {
					ccost = rs117.getDouble(2);
					fcost = rs117.getDouble(3);
					mgcost = rs117.getDouble(4);
				}

				int mage2 = 0;
				try {
					SimpleDateFormat simpleDateFormat11 = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date date11 = simpleDateFormat11.parse(adate2);
					java.util.Date date22 = simpleDateFormat11.parse(wdate);

					long days = date22.getTime() - date11.getTime();
					String daysBetween = (days / (1000 * 60 * 60 * 24)) + "";

					mage2 = Integer.parseInt(daysBetween);
				} catch (Exception e45) {
					mage2 = 0;

				}

				double tcost = 0.0;
				tcost = ccost * rs111.getDouble(10) + fcost * feedcon + mgcost * rs111.getDouble(10) + medcost;

				double pcost1 = 0.0, pcost = 0.0;
				if (cweight > 0) {
					int aaaa = rs111.getInt(10) - mort;
					pcost1 = aaaa * cweight;
					pcost1 = pcost1 / 1000;
					pcost = tcost / pcost1;
				} else {
					pcost = 0.0;
				}
				pcost = Math.round(pcost * 100.0) / 100.0;

				double fcr = feedcon;
				int aaaa = rs111.getInt(10) - mort;

				fcr = fcr / (Double.parseDouble(aaaa + "") * cweight);
				fcr = fcr * 1000;
				fcr = Math.round(fcr * 100.0) / 100.0;

				double daygain1 = cweight;
				daygain1 = daygain1 / mage2;
				daygain1 = Math.round(daygain1 * 100.0) / 100.0;

				Map<String, Object> obj = new HashMap<>();
				obj.put("PCOST", pcost);
				obj.put("FCR", fcr + "");
				obj.put("DAY GAIN", daygain1 + "");
				obj.put("MEAN AGE", mage2 + "");
				result.add(obj);

			}

			cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<Map<String, Object>> getTravel(String date, String id) {
		List<Map<String, Object>> result = new ArrayList<>();
		DataSource ds = jdbcTemplate.getDataSource();
		Connection cn = DataSourceUtils.getConnection(ds);

		try {
			PreparedStatement ps11 = cn.prepareStatement("select * from travelreg where date1=? and user=?");
			ps11.setString(1, date);
			ps11.setString(2, id);
			ResultSet rs11 = ps11.executeQuery();
			if (rs11.next()) {
				PreparedStatement ps1 = cn.prepareStatement(
						"select okm,ckm,tkm,date1,date2,lat,lan,otime,lat1,lan1,ctime,travelpic_open,travelpic_close,id from travelreg where id=?");
				ps1.setString(1, rs11.getString(1));

				ResultSet rs1 = ps1.executeQuery();
				if (rs1.next()) {
					Map<String, Object> obj = new HashMap<>();
					obj.put("Opening Km", rs1.getString(1));
					obj.put("Closing Km", rs1.getString(2));
					obj.put("Total Km", rs1.getString(3));
					obj.put("Opening Date", rs1.getString(4));
					obj.put("Closing Date", rs1.getString(5));
					obj.put("Opening Lattitude", rs1.getString(6));
					obj.put("Opening Longitude", rs1.getString(7));
					obj.put("Opening Time", rs1.getString(8));
					obj.put("Closing Lattitude", rs1.getString(9));
					obj.put("Closing Longitude", rs1.getString(10));
					obj.put("Closing Time", rs1.getString(11));
					obj.put("Opening Photo", rs1.getString(12));
					obj.put("Closing Photo", rs1.getString(13));
					obj.put("Travel ID", rs1.getString(14));
					result.add(obj);

				}

			}
			cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
