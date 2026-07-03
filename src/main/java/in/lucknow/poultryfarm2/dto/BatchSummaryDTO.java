package in.lucknow.poultryfarm2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchSummaryDTO {
	private String batchno;
	private Integer qty;
	private Integer totalMort;
	private Integer totalSold;
	private Integer totalCulls;
	private Integer totalBalance;
}
