package in.lucknow.poultryfarm2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.lucknow.poultryfarm2.dto.LineMasterDTO;
import in.lucknow.poultryfarm2.repository.LineMasterRepository;
import in.lucknow.poultryfarm2.service.LineMasterService;

@Service
public class LineMasterServiceImpl implements LineMasterService {

	@Autowired
	private LineMasterRepository repository;

	@Override
	public List<LineMasterDTO> getLineName(String id) {
		return repository.findById(id);
	}
}
