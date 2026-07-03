package in.lucknow.poultryfarm2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.lucknow.poultryfarm2.dto.CompanyDTO;
import in.lucknow.poultryfarm2.repository.CompanyRepository;
import in.lucknow.poultryfarm2.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public List<CompanyDTO> getAllCompanies() {
		return companyRepository.getAllCompanies();
	}
}
