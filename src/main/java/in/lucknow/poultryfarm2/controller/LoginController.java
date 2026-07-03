package in.lucknow.poultryfarm2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.lucknow.poultryfarm2.model.LoginRequest;
import in.lucknow.poultryfarm2.model.LoginResponse;
import in.lucknow.poultryfarm2.model.User;
import in.lucknow.poultryfarm2.service.impl.LoginService;
import in.lucknow.poultryfarm2.utils.ApiResponse;
import in.lucknow.poultryfarm2.utils.ResponseUtil;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<User>> login(@RequestBody LoginRequest request) {

		LoginResponse response = loginService.login(request);

		if (!response.isSuccess()) {
			return ResponseEntity.ok(new ApiResponse<>("FAILED", 401, "Invalid Username or Password", null));
		}

		return ResponseUtil.success(response.getData());
	}
}
