package in.lucknow.poultryfarm2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.lucknow.poultryfarm2.dto.AllocationFeedMedDTO;
import in.lucknow.poultryfarm2.repository.AllocationFeedMedRepository;
import in.lucknow.poultryfarm2.service.AllocationFeedMedService;

@Service
public class AllocationFeedMedServiceImpl implements AllocationFeedMedService {

	@Autowired
	private AllocationFeedMedRepository repository;

	@Override
	public List<AllocationFeedMedDTO> fetchAllocationData(String batchNumber) {
		return repository.getAllocationData(batchNumber);
	}

	@Override
	public Integer getTotalQty(String batch, String product) {
		return repository.fetchTotalQty(batch, product);
	}

	@Override
	public List<AllocationFeedMedDTO> getByBatchAndCat(String batch, String cat) {
		return repository.findByBatchAndCat(batch, cat);
	}
}
