package in.lucknow.poultryfarm2.service;

import java.util.List;

import in.lucknow.poultryfarm2.dto.FarmerDTO;

public interface FarmerService {
	List<FarmerDTO> getFarmers(String emptype, String llid, String branch);
}
