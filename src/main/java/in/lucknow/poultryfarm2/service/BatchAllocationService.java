package in.lucknow.poultryfarm2.service;

import java.util.List;

import in.lucknow.poultryfarm2.dto.BatchAllocationDTO;
import in.lucknow.poultryfarm2.dto.BatchSummaryDTO;

public interface BatchAllocationService {
	BatchSummaryDTO getBatchSummary(Long farmerId);

	List<BatchAllocationDTO> getOpenBatches(Integer farmerId);
}
