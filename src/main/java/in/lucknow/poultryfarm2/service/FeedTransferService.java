package in.lucknow.poultryfarm2.service;

public interface FeedTransferService {
	Integer getTotalQtyToFarmer(String toFarmer, String product);

	Integer getTotalQtyFromFarmer(String frFarmer, String product);
}
