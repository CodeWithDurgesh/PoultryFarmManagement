package in.lucknow.poultryfarm2.service;

import in.lucknow.poultryfarm2.utils.ListContainer;

public interface DataService {
	ListContainer getFlockTotal(String batch);

	ListContainer getTravel(String date, String id);
}
