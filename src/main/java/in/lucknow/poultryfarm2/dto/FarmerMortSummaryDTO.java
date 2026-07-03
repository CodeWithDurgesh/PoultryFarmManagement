package in.lucknow.poultryfarm2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FarmerMortSummaryDTO {
	private int id;
	private String date1;
	private String reason;
	private String cweight;
	private int totalMort;
}
