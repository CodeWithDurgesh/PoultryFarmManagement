package in.lucknow.poultryfarm2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BatchAllocationDTO {
	private Long id;
	private String date1;
	private String batchno;
	private String branch;
	private Long farmer;
	private String inspector;
	private String remark;
	private String cat;
	private String product;
	private Double qty;
	private String workdone;
	private String status;
	private String date2;
}
