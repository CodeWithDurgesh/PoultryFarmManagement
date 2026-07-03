package in.lucknow.poultryfarm2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.lucknow.poultryfarm2.dto.SupervisorResponse;
import in.lucknow.poultryfarm2.repository.SupervisorRepository;
import in.lucknow.poultryfarm2.service.SupervisorService;

@Service
public class SupervisorServiceImpl implements SupervisorService {

	@Autowired
	private SupervisorRepository supervisorRepository;

	@Override
	public List<SupervisorResponse> fetchSupervisor(String name) {
		return supervisorRepository.getSupervisorDetails(name);
	}
}
