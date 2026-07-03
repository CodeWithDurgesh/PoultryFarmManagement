package in.lucknow.poultryfarm2.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FarmerAlcReportDTO {
	private int id;
	private String date1;
	private String stime;
	private String etime;
	private String okm;
	private String ckm;
	private String tkm;
	private String line;
	private String farmer;
	private String mdate;
	private String mort;
	private String mortp;
	private String cweight;
	private String feed;
	private String consume;
	private String suggestion;
	private String reason;
	private String batch;
	private String date2;
	private String lat;
	private String lang;
	private String time;
	// Store image path in DB
	private String mortpic;
	// Receive file from Postman
	private MultipartFile mortpicfile;
	private String distance;
	private String culls;
	private String temprature;
	private String eaddress;
	private String fstatus;
	private String eremarks;
}
