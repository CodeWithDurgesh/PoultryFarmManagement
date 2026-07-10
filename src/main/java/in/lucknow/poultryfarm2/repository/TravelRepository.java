package in.lucknow.poultryfarm2.repository;

import in.lucknow.poultryfarm2.dto.SaveTravel;

public interface TravelRepository {
	int saveTravel(SaveTravel saveTravel);

	int updateTravel(SaveTravel saveTravel);
}
