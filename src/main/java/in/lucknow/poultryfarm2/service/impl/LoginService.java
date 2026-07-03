package in.lucknow.poultryfarm2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.lucknow.poultryfarm2.model.LoginRequest;
import in.lucknow.poultryfarm2.model.LoginResponse;
import in.lucknow.poultryfarm2.model.User;
import in.lucknow.poultryfarm2.repository.UserRepository;

@Service
public class LoginService {

	@Autowired
	private UserRepository repository;

	public LoginResponse login(LoginRequest request) {

		User user = repository.login(request.getUser(), request.getPassword());

		if (user == null) {
			return new LoginResponse(false, "Invalid Username or Password", null);
		}

		return new LoginResponse(true, "Login Successful", user);
	}
}
