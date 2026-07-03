package in.lucknow.poultryfarm2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.lucknow.poultryfarm2.dto.FarmerDTO;
import in.lucknow.poultryfarm2.repository.FarmerRepository;
import in.lucknow.poultryfarm2.service.FarmerService;

@Service
public class FarmerServiceImpl implements FarmerService {

	@Autowired
	private FarmerRepository farmerRepository;

	@Override
	public List<FarmerDTO> getFarmers(String emptype, String llid, String branch) {

		if ("Line Supervisor".equalsIgnoreCase(emptype)) {
			return farmerRepository.findByArea(llid);
		} else {
			return farmerRepository.findByBranch(branch);
		}
	}
}
