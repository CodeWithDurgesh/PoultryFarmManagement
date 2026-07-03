package in.lucknow.poultryfarm2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	private Integer id;
	private String name;
	private String user;
	@JsonIgnore
	private String pwd;
	private String status;
	private String branches;
	private String companies;
	private String role;
	private String wdays;
}
