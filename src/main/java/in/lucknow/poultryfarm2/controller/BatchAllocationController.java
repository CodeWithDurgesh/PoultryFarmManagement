package in.lucknow.poultryfarm2.controller;

import in.lucknow.poultryfarm2.dto.AllocationFeedMedDTO;
import in.lucknow.poultryfarm2.dto.BatchAllocationDTO;
import in.lucknow.poultryfarm2.dto.BatchSummaryDTO;
import in.lucknow.poultryfarm2.dto.CompanyDTO;
import in.lucknow.poultryfarm2.dto.DiseaseDTO;
import in.lucknow.poultryfarm2.dto.FarmerAlcReportDTO;
import in.lucknow.poultryfarm2.dto.FarmerDTO;
import in.lucknow.poultryfarm2.dto.FarmerMortSummaryDTO;
import in.lucknow.poultryfarm2.dto.FeedConsumeSummaryDTO;
import in.lucknow.poultryfarm2.dto.LineMasterDTO;
import in.lucknow.poultryfarm2.dto.ProductDTO;
import in.lucknow.poultryfarm2.dto.SaveTravel;
import in.lucknow.poultryfarm2.dto.SupervisorResponse;
import in.lucknow.poultryfarm2.service.AllocationFeedMedService;
import in.lucknow.poultryfarm2.service.BatchAllocationService;
import in.lucknow.poultryfarm2.service.CompanyService;
import in.lucknow.poultryfarm2.service.DataService;
import in.lucknow.poultryfarm2.service.DiseaseService;
import in.lucknow.poultryfarm2.service.FarmerAlcService;
import in.lucknow.poultryfarm2.service.FarmerService;
import in.lucknow.poultryfarm2.service.FeedTransferService;
import in.lucknow.poultryfarm2.service.LineMasterService;
import in.lucknow.poultryfarm2.service.ProductService;
import in.lucknow.poultryfarm2.service.SupervisorService;
import in.lucknow.poultryfarm2.utils.ApiResponse;
import in.lucknow.poultryfarm2.utils.ListContainer;
import in.lucknow.poultryfarm2.utils.ResponseUtil;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class BatchAllocationController {

	@Autowired
	private BatchAllocationService batchAllocationService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private DiseaseService diseaseService;

	@Autowired
	private SupervisorService supervisorService;

	@Autowired
	private FarmerService farmerService;

	@Autowired
	private LineMasterService lineMasterService;

	@Autowired
	private AllocationFeedMedService allocationFeedMedService;

	@Autowired
	private ProductService productService;

	@Autowired
	private FeedTransferService feedTransferService;

	@Autowired
	private FarmerAlcService farmerAlcService;

	@Autowired
	private DataService ds;

	@GetMapping("/batch/summary/{farmerId}")
	public ResponseEntity<ApiResponse<BatchSummaryDTO>> getBatchSummary(@PathVariable Long farmerId) {

		BatchSummaryDTO data = batchAllocationService.getBatchSummary(farmerId);
		if (data == null || data.getBatchno() == null) {
			return ResponseUtil.failure();
		}

		return ResponseUtil.success(data);
	}

	@GetMapping("/get-company")
	public ResponseEntity<ApiResponse<List<CompanyDTO>>> getAllCompanies() {
		List<CompanyDTO> data = companyService.getAllCompanies();
		if (data == null) {
			return ResponseUtil.failure();
		}
		return ResponseUtil.success(data);
	}

	@GetMapping("/get-dieseas")
	public ResponseEntity<ApiResponse<List<DiseaseDTO>>> getAllDiseases() {
		List<DiseaseDTO> data = diseaseService.getAllDiseases();
		if (data == null) {
			return ResponseUtil.failure();
		}
		return ResponseUtil.success(data);
	}

	@GetMapping("/get-farm")
	public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getSupervisor(@RequestParam String id) {
		List<Map<String, Object>> resultForGetForm = new ArrayList<>();

		// Step 1 - get Supervisor
		List<SupervisorResponse> data = supervisorService.fetchSupervisor(id);
		if (data == null || data.isEmpty()) {
			return ResponseUtil.failure();
		} else {
			String emptype = data.get(0).getIsemp();
			String branch = String.valueOf(data.get(0).getBranch());
			String tDate = String.valueOf(data.get(0).getTdate());
			System.out.println("@@@Employee Type@@@@@@" + emptype);
			System.out.println("@@@Employee Branch@@@@@@" + branch);
			System.out.println("@@@Employee tDate@@@@@@" + tDate);

			// Step 2 - get Farmers rs11
			List<FarmerDTO> farmers = farmerService.getFarmers(emptype, tDate, branch);
			List<AllocationFeedMedDTO> getAllocationFeed = null;
			if (farmers != null && farmers.size() > 0) {
				for (FarmerDTO farmer : farmers) {
					String aqty = "", adate2 = "";
					String flag = "Y";
					String batch = "";
					// System.out.println("@@Area@@@" + farmer.getArea());
					// Step 3 - get Line Master rs112
					List<LineMasterDTO> lineData = lineMasterService.getLineName(tDate);
					// Step 4 - get Open Batch Allocation rs113
					List<BatchAllocationDTO> getOpenBatches = batchAllocationService.getOpenBatches(farmer.getId());

					if (getOpenBatches != null && getOpenBatches.size() > 0) {
						for (BatchAllocationDTO batchAllocation : getOpenBatches) {

							// Step 4 - get Open Batch Allocation FEED MED rs111
							getAllocationFeed = allocationFeedMedService
									.fetchAllocationData(batchAllocation.getBatchno());
							if (getAllocationFeed != null && getAllocationFeed.size() > 0) {
								for (AllocationFeedMedDTO feedMed : getAllocationFeed) {
									adate2 = feedMed.getAgainreq();
									aqty = feedMed.getQty().toString();
								}

							} else {
								flag = "N";
							}

							batch = batchAllocation.getBatchno();
							if (!flag.equals("N")) {
								Map<String, Object> obj = new HashMap<>();
								obj.put("ID", farmer.getId());
								obj.put("farm_name", farmer.getName());
								obj.put("line_name", lineData.get(0).getName());
								obj.put("contact", farmer.getContact());
								obj.put("batch", batch);
								// obj.put("mean_age", mage2 + "");
								obj.put("bird", aqty + "");
								resultForGetForm.add(obj);
							}

						}
					}

				}

			}

			if (farmers == null || farmers.isEmpty()) {
				return ResponseUtil.failure();
			}
			return ResponseUtil.success(resultForGetForm);
		}
	}

	@GetMapping("/get-feed")
	public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getFeed(@RequestParam String batch,
			@RequestParam String companyId) {
		List<Map<String, Object>> resultForGetFeed = new ArrayList<>();
		// Step 1 - get Products According to Batch and cId rs13
		List<ProductDTO> getProducts = productService.getProducts(companyId);
		System.out.println("Product Data@@@@@@@" + getProducts.size());
		if (getProducts != null && getProducts.size() > 0) {
			for (ProductDTO product : getProducts) {
				double fin = 0.0, fout = 0.0;
				String consume = "0";
				double size = 0.0;
				// Step 1 - get Total Quantity from allocation_feed_med According to Batch and
				// Prodcut rs11
				Integer totalQty = allocationFeedMedService.getTotalQty(batch, product.getProduct());
				System.out.println("@@@@@@Total QTY@@@@@@@" + totalQty);
				if (totalQty != null) {
					size = Double.parseDouble(String.valueOf(totalQty));
				} else {
					size = 0.0;
				}

				Integer totalConsume = farmerAlcService.getTotalConsume(batch, product.getProduct());
				System.out.println("@@@@@@Total Consume@@@@@@@" + totalConsume);
				if (totalConsume != null) {
					consume = totalConsume + "";
				} else {
					consume = "0";
				}

				Integer totalQtyToFarmer = feedTransferService.getTotalQtyToFarmer(batch, product.getProduct());
				System.out.println("@@@@@@Total QTY 'To-Farmer' FEED TRANSFER@@@@@@@" + totalQty);
				if (totalQtyToFarmer != null) {
					fin = Double.parseDouble(String.valueOf(totalQtyToFarmer));
				} else {
					fin = 0.0;
				}

				Integer totalQtyFromFarmer = feedTransferService.getTotalQtyFromFarmer(batch, product.getProduct());
				System.out.println("@@@@@@Total QTY 'From-Farmer' FEED TRANSFER@@@@@@@" + totalQty);
				if (totalQtyFromFarmer != null) {
					fout = Double.parseDouble(String.valueOf(totalQtyFromFarmer));
				} else {
					fout = 0.0;
				}

				double remain = 0.0;
				try {
					remain = size - Double.parseDouble(consume) + fin - fout;
				} catch (Exception e) {
					e.printStackTrace();
				}

				Map<String, Object> obj = new HashMap<>();
				obj.put("product", product.getProduct());
				obj.put("allocation", size);
				obj.put("fin", fin + "");
				obj.put("fout", fout + "");
				obj.put("consume", consume + "");
				obj.put("balance", remain + "");

				resultForGetFeed.add(obj);

			}
		}
		if (getProducts == null || getProducts.isEmpty()) {
			return ResponseUtil.failure();
		}
		return ResponseUtil.success(resultForGetFeed);
	}

	@GetMapping("/get-flock-detail")
	public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getFlockDetail(@RequestParam String batch) {
		List<Map<String, Object>> resultForFlockDetails = new ArrayList<>();
		String aqty = "", adate = "";
		List<AllocationFeedMedDTO> getByBatchAndCat = allocationFeedMedService.getByBatchAndCat(batch, "Chicks");

		if (getByBatchAndCat != null && getByBatchAndCat.size() > 0) {
			for (AllocationFeedMedDTO allocationFeedMed : getByBatchAndCat) {
				adate = allocationFeedMed.getDate1().toString();
				aqty = allocationFeedMed.getQty().toString();
			}
			List<FarmerAlcReportDTO> getByBatch = farmerAlcService.getByBatch(batch);

			if (getByBatch != null && getByBatch.size() > 0) {
				for (FarmerAlcReportDTO farmerAlcReport : getByBatch) {
					int data1 = 0;
					String mdate = "", cweight = "";
					List<FarmerMortSummaryDTO> getMortSummary = farmerAlcService.getMortSummary(batch,
							farmerAlcReport.getDate2());
					if (getMortSummary != null && getMortSummary.size() > 0) {
						for (FarmerMortSummaryDTO alcReport : getMortSummary) {
							data1 = alcReport.getTotalMort();
							mdate = alcReport.getDate1();
							cweight = alcReport.getCweight();
						}
					} else {
						data1 = 0;
					}

					String data4 = "", data5 = "";
					int data77 = 0;

					List<FeedConsumeSummaryDTO> getFeedConsumeSummary = farmerAlcService.getFeedConsumeSummary(batch,
							farmerAlcReport.getDate2());

					if (getFeedConsumeSummary != null && getFeedConsumeSummary.size() > 0) {
						for (FeedConsumeSummaryDTO getFeedConsume : getFeedConsumeSummary) {
							data5 = getFeedConsume.getFeed();
							data77 = getFeedConsume.getTotalConsume();
						}

					} else {
						data5 = "-";
						data77 = 0;
					}

					data4 = data77 + "";
					if (data4.equals("") || data4.equals(null)) {
						data4 = "0";
					}
					try {
						if (data5.equals("") || data5.equals(null)) {
							data5 = "-";
						}
					} catch (Exception e) {
						data5 = "-";
					}

					String mortdate = mdate;

//					SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
//					java.util.Date dateBefore = myFormat.parse(adate);
//					java.util.Date dateAfter = myFormat.parse(mortdate);
//					long difference = dateAfter.getTime() - dateBefore.getTime();
//					float daysBetween = (difference / (1000 * 60 * 60 * 24));
//
//					double mortp = 100 * data1;
//					mortp = mortp / Integer.parseInt(aqty);
//					mortp = Math.round(mortp * 100.0) / 100.0;

					Map<String, Object> obj = new HashMap<>();
					obj.put("DATE", mdate);
					// obj.put("MAGE", (int) daysBetween + "");
					obj.put("FEED", data5 + "");
					obj.put("FEED QTY", Math.round(Double.parseDouble(data4)) + "");
					obj.put("MORTILITY", data1);
					// obj.put("PERCENTAGE", mortp + "");
					obj.put("BWT", cweight + "");

					resultForFlockDetails.add(obj);

				}
			}

		}
		if (getByBatchAndCat == null || getByBatchAndCat.isEmpty()) {
			return ResponseUtil.failure();
		}
		return ResponseUtil.success(resultForFlockDetails);

	}

	@GetMapping(value = "/get-flock-total")
	public ResponseEntity<?> getFlockTotal(@RequestParam String batch) throws IOException {
		return new ResponseEntity<ListContainer>(ds.getFlockTotal(batch), HttpStatus.OK);
	}

	@GetMapping(value = "/get-travel")
	public ResponseEntity<?> getTravel(@RequestParam String date, @RequestParam String id) throws IOException {
		return new ResponseEntity<ListContainer>(ds.getTravel(date, id), HttpStatus.OK);
	}

	@PostMapping("/save-entry")
	public ResponseEntity<ApiResponse<List<String>>> saveReport(@ModelAttribute FarmerAlcReportDTO report) {
		List<String> messageSuccess = new ArrayList<>();
		String message = farmerAlcService.saveReport(report);
		if (message == null || message.equalsIgnoreCase("FAIL")) {
			return ResponseUtil.failure();
		} else if (message.equalsIgnoreCase("Saved successfully")) {
			messageSuccess.add(message);
			return ResponseUtil.success(messageSuccess);
		}
		return null;
	}

	@PostMapping("/save-travel")
	public ResponseEntity<ApiResponse<List<String>>> saveTravel(@ModelAttribute SaveTravel saveTravel) {
		List<String> messageSuccess = new ArrayList<>();
		String message = farmerAlcService.saveTravel(saveTravel);
		if (message == null || message.equalsIgnoreCase("FAIL")) {
			return ResponseUtil.failure();
		} else if (message.equalsIgnoreCase("Saved successfully")) {
			messageSuccess.add(message);
			return ResponseUtil.success(messageSuccess);
		}
		return null;
	}

	@PutMapping("/update-travel")
	public ResponseEntity<ApiResponse<List<String>>> updateTravel(@ModelAttribute SaveTravel saveTravel) {
		List<String> messageSuccess = new ArrayList<>();
		String message = farmerAlcService.updateTravel(saveTravel);
		if (message == null || message.equalsIgnoreCase("FAIL")) {
			return ResponseUtil.failure();
		} else if (message.equalsIgnoreCase("Update successfully")) {
			messageSuccess.add(message);
			return ResponseUtil.success(messageSuccess);
		}
		return null;
	}

}