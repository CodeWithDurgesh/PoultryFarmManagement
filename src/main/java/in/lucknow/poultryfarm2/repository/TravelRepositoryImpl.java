package in.lucknow.poultryfarm2.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.lucknow.poultryfarm2.dto.SaveTravel;

@Repository
public class TravelRepositoryImpl implements TravelRepository {

	private static final String INSERT_TRAVEL_SQL = "insert into travelreg(date1,okm,ckm,tkm,user,date2,lat,lan,otime,lat1,lan1,ctime,travelpic_open) "
			+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String UPDATE_TRAVEL_SQL =
			"UPDATE travelreg SET " +
			"date1 = COALESCE(?, date1), " +
			"okm = COALESCE(?, okm), " +
			"ckm = COALESCE(?, ckm), " +
			"tkm = COALESCE(?, tkm), " +
			"date2 = COALESCE(?, date2), " +
			"lat = COALESCE(?, lat), " +
			"lan = COALESCE(?, lan), " +
			"otime = COALESCE(?, otime), " +
			"lat1 = COALESCE(?, lat1), " +
			"lan1 = COALESCE(?, lan1), " +
			"ctime = COALESCE(?, ctime), " +
			"travelpic_open = COALESCE(?, travelpic_open), " +
			"travelpic_close = COALESCE(?, travelpic_close) " +
			"WHERE id = ? AND user = ?";

	private final JdbcTemplate jdbcTemplate;

	public TravelRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int saveTravel(SaveTravel saveTravel) {
		return jdbcTemplate.update(INSERT_TRAVEL_SQL, saveTravel.getDate1(), saveTravel.getOkm(), saveTravel.getCkm(),
				saveTravel.getTkm(), saveTravel.getUser(), saveTravel.getDate2(), saveTravel.getLat(),
				saveTravel.getLan(), saveTravel.getOtime(), saveTravel.getLat1(), saveTravel.getLan1(),
				saveTravel.getCtime(), saveTravel.getTravelpic_open());
	}

	@Override
	public int updateTravel(SaveTravel saveTravel) {
		return jdbcTemplate.update(UPDATE_TRAVEL_SQL,
				saveTravel.getDate1(),
				saveTravel.getOkm(),
				saveTravel.getCkm(),
				saveTravel.getTkm(),
				saveTravel.getDate2(),
				saveTravel.getLat(),
				saveTravel.getLan(),
				saveTravel.getOtime(),
				saveTravel.getLat1(),
				saveTravel.getLan1(),
				saveTravel.getCtime(),
				saveTravel.getTravelpic_open(),
				saveTravel.getTravelpic_close(),
				saveTravel.getId(),
				saveTravel.getUser());
	}
}
