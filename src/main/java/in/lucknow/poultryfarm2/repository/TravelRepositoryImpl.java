package in.lucknow.poultryfarm2.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.lucknow.poultryfarm2.dto.SaveTravel;

@Repository
public class TravelRepositoryImpl implements TravelRepository {

	private static final String INSERT_TRAVEL_SQL = "insert into travelreg(date1,okm,ckm,tkm,user,date2,lat,lan,otime,lat1,lan1,ctime) "
			+ "values(?,?,?,?,?,?,?,?,?,?,?,?)";

	private final JdbcTemplate jdbcTemplate;

	public TravelRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int saveTravel(SaveTravel saveTravel) {
		return jdbcTemplate.update(INSERT_TRAVEL_SQL, saveTravel.getDate1(), saveTravel.getOkm(), saveTravel.getCkm(),
				saveTravel.getTkm(), saveTravel.getUser(), saveTravel.getDate2(), saveTravel.getLat(),
				saveTravel.getLan(), saveTravel.getOtime(), saveTravel.getLat1(), saveTravel.getLan1(),
				saveTravel.getCtime());
	}
}
