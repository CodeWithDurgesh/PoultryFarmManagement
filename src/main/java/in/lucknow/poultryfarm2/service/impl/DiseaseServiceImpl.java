package in.lucknow.poultryfarm2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.lucknow.poultryfarm2.dto.DiseaseDTO;
import in.lucknow.poultryfarm2.repository.DiseaseRepository;
import in.lucknow.poultryfarm2.service.DiseaseService;

@Service
public class DiseaseServiceImpl implements DiseaseService {

	@Autowired
	private DiseaseRepository diseaseRepository;

	@Override
	public List<DiseaseDTO> getAllDiseases() {
		return diseaseRepository.getAllDiseases();
	}
}
