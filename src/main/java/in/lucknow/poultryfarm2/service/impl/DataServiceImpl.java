package in.lucknow.poultryfarm2.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.lucknow.poultryfarm2.repository.DataDao;
import in.lucknow.poultryfarm2.service.DataService;
import in.lucknow.poultryfarm2.utils.ListContainer;

@Service
public class DataServiceImpl implements DataService {
	@Autowired
	DataDao dd;

	@Override
	public ListContainer getFlockTotal(String batch) {
		ListContainer lc = new ListContainer();
		try {
			List<Map<String, Object>> createUser = dd.getFlockTotal(batch);
			lc.setReport(createUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lc;

	}

	@Override
	public ListContainer getTravel(String date, String id) {

		ListContainer lc = new ListContainer();
		try {
			List<Map<String, Object>> createUser = dd.getTravel(date, id);
			lc.setReport(createUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lc;
	
	}
}
