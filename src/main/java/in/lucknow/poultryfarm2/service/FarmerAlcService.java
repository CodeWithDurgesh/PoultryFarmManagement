package in.lucknow.poultryfarm2.service;

import java.util.List;

import in.lucknow.poultryfarm2.dto.FarmerAlcReportDTO;
import in.lucknow.poultryfarm2.dto.FarmerMortSummaryDTO;
import in.lucknow.poultryfarm2.dto.FeedConsumeSummaryDTO;
import in.lucknow.poultryfarm2.dto.SaveTravel;

public interface FarmerAlcService {
	Integer getTotalConsume(String batch, String feed);

	List<FarmerAlcReportDTO> getByBatch(String batch);

	List<FarmerMortSummaryDTO> getMortSummary(String batch, String date2);

	List<FeedConsumeSummaryDTO> getFeedConsumeSummary(String batch, String date2);

	// String saveReport(FarmerAlcReportDTO report, MultipartFile mortpic);
	String saveReport(FarmerAlcReportDTO report);

	String saveTravel(SaveTravel saveTravel);

}
