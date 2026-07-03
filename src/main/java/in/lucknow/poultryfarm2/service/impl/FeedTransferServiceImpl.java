package in.lucknow.poultryfarm2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.lucknow.poultryfarm2.repository.FeedTransferRepository;
import in.lucknow.poultryfarm2.service.FeedTransferService;

@Service
public class FeedTransferServiceImpl implements FeedTransferService {

	@Autowired
	private FeedTransferRepository repository;

	@Override
	public Integer getTotalQtyToFarmer(String toFarmer, String product) {
		return repository.fetchTotalQtyToFarmer(toFarmer, product);
	}

	@Override
	public Integer getTotalQtyFromFarmer(String frFarmer, String product) {
		return repository.fetchTotalQtyFromFarmer(frFarmer, product);
	}
}
