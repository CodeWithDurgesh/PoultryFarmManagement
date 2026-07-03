package in.lucknow.poultryfarm2.service;

import java.util.List;

import in.lucknow.poultryfarm2.dto.SupervisorResponse;

public interface SupervisorService {
	List<SupervisorResponse> fetchSupervisor(String name);
}
