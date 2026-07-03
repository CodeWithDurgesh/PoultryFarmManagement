package in.lucknow.poultryfarm2.repository;

import in.lucknow.poultryfarm2.model.User;

public interface UserRepository {

	User login(String username, String password);

}
