package in.lucknow.poultryfarm2.service;

import java.util.List;

import in.lucknow.poultryfarm2.dto.AllocationFeedMedDTO;

public interface AllocationFeedMedService {
	List<AllocationFeedMedDTO> fetchAllocationData(String batchNumber);

	Integer getTotalQty(String batch, String product);

	List<AllocationFeedMedDTO> getByBatchAndCat(String batch, String cat);
}
