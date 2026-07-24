package in.lucknow.poultryfarm2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AllocationFeedMedDTO {
	private Long id;
	private String date1;
	private String invoice;
	private String branch;
	private Long farmer;
	private String batch;
	private String remark;
	private String cat;
	private String product;
	private Double qty;
	private String unit;
	private Double rate;
	private Double total;
	private String dcno;
	private String vehicleno;
	private String transferby;
	private String transfercharge;
	private String againreq;
	private String vendor;
	// private String godown;
}
