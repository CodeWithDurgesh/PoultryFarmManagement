package in.lucknow.poultryfarm2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FarmerDTO {
	private Integer id;
	private Integer area;
	private Integer branch;
	private String name;
	private String contact;
}
