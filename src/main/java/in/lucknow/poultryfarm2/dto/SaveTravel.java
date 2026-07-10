package in.lucknow.poultryfarm2.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SaveTravel {

	// @NotBlank(message = "date1 is required")
	private String date1;

	// @NotNull(message = "okm is required")
	private Double okm;

	// @NotNull(message = "ckm is required")
	private Double ckm;

	private Double tkm;

	// @NotBlank(message = "user is required")
	private String user;

	private String date2;

	// @NotNull(message = "lat is required")
	private Double lat;

	// @NotNull(message = "lan is required")
	private Double lan;

	private String otime;

	private Double lat1;

	private Double lan1;

	private String ctime;

	// Store image path in DB
	private String travelpic_open;
	// Receive file from Postman
	private MultipartFile travelpicfile_open;

	// Store image path in DB
	private String travelpic_close;
	// Receive file from Postman
	private MultipartFile travelpicfile_close;

	private Long id;
}
