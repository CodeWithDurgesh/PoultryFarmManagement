package in.lucknow.poultryfarm2.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import in.lucknow.poultryfarm2.dto.FarmerAlcReportDTO;
import in.lucknow.poultryfarm2.dto.FarmerDTO;
import in.lucknow.poultryfarm2.dto.FarmerMortSummaryDTO;
import in.lucknow.poultryfarm2.dto.FeedConsumeSummaryDTO;
import in.lucknow.poultryfarm2.dto.SaveTravel;
import in.lucknow.poultryfarm2.repository.FarmerAlcRepository;
import in.lucknow.poultryfarm2.repository.FarmerRepository;
import in.lucknow.poultryfarm2.repository.TravelRepository;
import in.lucknow.poultryfarm2.service.FarmerAlcService;

@Service
public class FarmerAlcServiceImpl implements FarmerAlcService {

	@Autowired
	private FarmerAlcRepository repository;

	@Autowired
	private FarmerRepository farmerRepository;

	@Autowired
	private FarmerAlcRepository farmerAlcRepository;

	@Autowired
	private TravelRepository travelRepository;

	@Value("${file.upload-dir}")
	private String uploadPath;

	@Value("${file.base-url}")
	private String baseUrl;

	private static final Logger log = LoggerFactory.getLogger(FarmerAlcServiceImpl.class);

	@Override
	public Integer getTotalConsume(String batch, String feed) {
		return repository.fetchTotalConsume(batch, feed);
	}

	@Override
	public List<FarmerAlcReportDTO> getByBatch(String batch) {
		return repository.findByBatch(batch);
	}

	@Override
	public List<FarmerMortSummaryDTO> getMortSummary(String batch, String date2) {
		return repository.getMortSummary(batch, date2);
	}

	@Override
	public List<FeedConsumeSummaryDTO> getFeedConsumeSummary(String batch, String date2) {
		return repository.getFeedConsumeSummary(batch, date2);
	}

//	@Override
//	public String saveReport(FarmerAlcReportDTO report, MultipartFile mortpic) {
//		try {
//
//			// Save image physically
//			String fileName = System.currentTimeMillis() + "_" + mortpic.getOriginalFilename();
//
//			Path path = Paths.get(uploadPath + File.separator + fileName);
//
//			Files.write(path, mortpic.getBytes());
//
//			// Save image path into DB
//			report.setMortpic(path.toString());
//
//			System.out.println("IMAGE PATH@@@@@" + path.toString());
//
//			// Example RestTemplate call
//			// String url =
//			// "https://www.codespiraltechnologies.com/attendance_1.0/images/1";
//
//			// restTemplate.getForObject(url, String.class);
//
//			// Save DB Record
//			repository.save(report);
//
//			return "Record has been saved successfully";
//
//		} catch (IOException e) {
//			throw new RuntimeException("Image upload failed");
//		}
//
//	}

	@Override
	public String saveReport(FarmerAlcReportDTO report) {

		List<String> successId = new ArrayList<>();

		String farmer = "", brn = "";

		String frmm[] = report.getBatch().toString().split("/");
		farmer = frmm[0];

		List<FarmerDTO> getFarmerDetails = farmerRepository.findById(farmer);

		brn = getFarmerDetails.get(0).getBranch().toString();

		System.out.println("@@Branch@@" + brn);
		try {

			MultipartFile file = report.getMortpicfile();

			if (file != null && !file.isEmpty()) {

				Path uploadDir = Paths.get(uploadPath);

				if (!Files.exists(uploadDir)) {
					Files.createDirectories(uploadDir);
				}

				System.out.println("Upload Directory ----------: " + uploadDir.toAbsolutePath());
				String originalFileName = file.getOriginalFilename();

				String extension = "";

				if (originalFileName != null && originalFileName.contains(".")) {
					extension = originalFileName.substring(originalFileName.lastIndexOf("."));
				}
				/////////// @@@@@@@@@@@@@START SERVER@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				String fileName = System.currentTimeMillis() + extension;
				Path destinationPath = uploadDir.resolve(fileName);
				System.out.println("Saving File To---------->check----- : " + destinationPath.toAbsolutePath());
				Files.copy(file.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
				System.out.println("File Saved Successfully");
				String imageUrl = baseUrl + "/" + fileName;
				System.out.println("Image URL :>---------------check " + imageUrl);

				/////////// @@@@@@@@@@@@@END SERVER@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

///////////@@@@@@@@@@@@@START LOCAL@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

//				String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//
//				uploadPath = "D:/MFARM_PICs/uploads/images";
//
//				Path path = Paths.get(uploadPath, fileName);
//
//				Files.write(path, file.getBytes());
//				String imageUrl = baseUrl + "/" + fileName;
				/////////// @@@@@@@@@@@@@END LOCAL@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				// store only filename in DB
				report.setMortpic(imageUrl);

			}
			report.setEtime(brn);
			report.setFarmer(farmer);

			int maxId = 0;
			int saveReport = repository.save(report);
			if (saveReport > 0) {
				maxId = farmerAlcRepository.getMaxIdFromFarmerAlcReport();
				System.out.println("@@Max ID@@" + maxId);
				successId.add(String.valueOf(saveReport));
				System.out.println("@@Success ID SIZE@@" + successId.size());
			}

			if (successId.size() > 0) {
				return "Saved successfully";
			} else {
				return "FAIL";
			}

		} catch (Exception e) {
			throw new RuntimeException("File upload failed", e);
		}
	}

	@Override
	public String saveTravel(SaveTravel saveTravel) {
		try {
			if (saveTravel.getTkm() == null && saveTravel.getOkm() != null && saveTravel.getCkm() != null) {
				saveTravel.setTkm(saveTravel.getCkm() - saveTravel.getOkm());
			}

			MultipartFile file = saveTravel.getTravelpicfile_open();

			if (file != null && !file.isEmpty()) {

				Path uploadDir = Paths.get(uploadPath);

				if (!Files.exists(uploadDir)) {
					Files.createDirectories(uploadDir);
				}

				System.out.println("Upload Directory ----------: " + uploadDir.toAbsolutePath());
				String originalFileName = file.getOriginalFilename();

				String extension = "";

				if (originalFileName != null && originalFileName.contains(".")) {
					extension = originalFileName.substring(originalFileName.lastIndexOf("."));
				}
				/////////// @@@@@@@@@@@@@START SERVER@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				String fileName = System.currentTimeMillis() + extension;
				Path destinationPath = uploadDir.resolve(fileName);
				System.out.println("Saving File To---------->check----- : " + destinationPath.toAbsolutePath());
				Files.copy(file.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
				System.out.println("File Saved Successfully");
				String imageUrl = baseUrl + "/" + fileName;
				System.out.println("Image URL :>---------------check " + imageUrl);

				/////////// @@@@@@@@@@@@@END SERVER@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

///////////@@@@@@@@@@@@@START LOCAL@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

//				String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//
//				uploadPath = "D:/MFARM_PICs/uploads/images/travel";
//
//				Path path = Paths.get(uploadPath, fileName);
//
//				Files.write(path, file.getBytes());
//				String imageUrl = baseUrl + "/" + fileName;
/////////// @@@@@@@@@@@@@END LOCAL@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				// store only filename in DB
				saveTravel.setTravelpic_open(imageUrl);
			}
			int rowsInserted = travelRepository.saveTravel(saveTravel);

			if (rowsInserted > 0) {
				return "Saved successfully";
			}
			return "FAIL";
		} catch (Exception ex) {
			log.error("Error while saving travel data: {}", ex.getMessage(), ex);
			return "FAIL";
		}
	}

	@Override
	public String updateTravel(SaveTravel saveTravel) {
		try {
			if (saveTravel.getId() == null || saveTravel.getUser() == null
					|| saveTravel.getUser().equalsIgnoreCase("")) {
				log.warn("updateTravel called without id/user");
				return "FAIL";
			}

			if (saveTravel.getTkm() == null && saveTravel.getOkm() != null && saveTravel.getCkm() != null) {
				saveTravel.setTkm(saveTravel.getCkm() - saveTravel.getOkm());
			}

			// Handle "open" trip image
			String openImageUrl = saveImageIfPresent(saveTravel.getTravelpicfile_open());
			if (openImageUrl != null) {
				saveTravel.setTravelpic_open(openImageUrl);
			} else {
				saveTravel.setTravelpic_open(null); // don't overwrite existing value
			}

			// Handle "close" trip image
			String closeImageUrl = saveImageIfPresent(saveTravel.getTravelpicfile_close());
			if (closeImageUrl != null) {
				saveTravel.setTravelpic_close(closeImageUrl);
			} else {
				saveTravel.setTravelpic_close(null); // don't overwrite existing value
			}

			int rowsUpdated = travelRepository.updateTravel(saveTravel);

			if (rowsUpdated > 0) {
				return "Update successfully";
			}
			// 0 rows => either id doesn't exist, or id+user combination doesn't match
			return "FAIL";

		} catch (Exception ex) {
			log.error("Error while updating travel data: {}", ex.getMessage(), ex);
			return "FAIL";
		}
	}

	private String saveImageIfPresent(MultipartFile file) throws IOException {
		if (file == null || file.isEmpty()) {
			return null;
		}

		Path uploadDir = Paths.get(uploadPath);
		if (!Files.exists(uploadDir)) {
			Files.createDirectories(uploadDir);
		}
		System.out.println("Upload Directory ----------: " + uploadDir.toAbsolutePath());
		String originalFileName = file.getOriginalFilename();

		String extension = "";

		if (originalFileName != null && originalFileName.contains(".")) {
			extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		}
		//// @@@@@@@@@@@START LOCAL@@@@@@@@@@@@@@@@@@@
		// String fileName = System.currentTimeMillis() + "_" +
		// file.getOriginalFilename();
		// uploadPath = "D:/MFARM_PICs/uploads/images/travel";
		// Path path = Paths.get(uploadPath, fileName);
		// Files.write(path, file.getBytes());
		/////////////////// @@@@@@@@@END LOCAL@@@@@@@@@@@@@@@@@@@@@@

		/////////// @@@@@@@@@@@@@START SERVER@@@@@@@@@@@@@@@@@
		String fileName = System.currentTimeMillis() + extension;
		Path destinationPath = uploadDir.resolve(fileName);
		System.out.println("Saving File To---------->check----- : " + destinationPath.toAbsolutePath());
		Files.copy(file.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
		System.out.println("File Updated Successfully");
		System.out.println("Image URL :>---------------check " + baseUrl + "/" + fileName);

		/////////// @@@@@@@@@@@@@END SERVER@@@@@@@@@@@@@@@@@@@@@
		return baseUrl + "/" + fileName;
	}

}
