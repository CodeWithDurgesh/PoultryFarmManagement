package in.lucknow.poultryfarm2.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

	private boolean success;
	private String message;
	private User data;

	public LoginResponse() {
	}

	public LoginResponse(boolean success, String message, User data) {
		this.success = success;
		this.message = message;
		this.data = data;
	}
}
