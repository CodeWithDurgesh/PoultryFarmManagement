package in.lucknow.poultryfarm2.service.impl;

import in.lucknow.poultryfarm2.dto.BatchAllocationDTO;
import in.lucknow.poultryfarm2.dto.BatchSummaryDTO;
import in.lucknow.poultryfarm2.repository.BatchAllocationRepository;
import in.lucknow.poultryfarm2.service.BatchAllocationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatchAllocationServiceImpl implements BatchAllocationService {

	@Autowired
	private BatchAllocationRepository repository;

	@Override
	public BatchSummaryDTO getBatchSummary(Long farmerId) {
		return repository.getBatchSummary(farmerId);
	}

	@Override
	public List<BatchAllocationDTO> getOpenBatches(Integer farmerId) {
		return repository.findOpenBatchesByFarmer(farmerId);
	}
}
