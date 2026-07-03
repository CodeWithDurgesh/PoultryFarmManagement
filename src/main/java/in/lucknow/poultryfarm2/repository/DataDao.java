package in.lucknow.poultryfarm2.repository;

import java.util.List;
import java.util.Map;

public interface DataDao {
	List<Map<String, Object>> getFlockTotal(String batch);

	List<Map<String, Object>> getTravel(String date, String id);
}
