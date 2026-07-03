package in.lucknow.poultryfarm2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	private Integer id;
	private String product;
	private String category;
	private String unit;
	private String kg;
	private String margin;
	private String maxPurchase;
	private String minPurchase;
	private String saleRate;
	private String company;
	private String stdCost;
	private String hsn;
}
