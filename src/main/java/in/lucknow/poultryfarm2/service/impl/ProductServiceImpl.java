package in.lucknow.poultryfarm2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.lucknow.poultryfarm2.dto.ProductDTO;
import in.lucknow.poultryfarm2.repository.ProductRepository;
import in.lucknow.poultryfarm2.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;

	@Override
	public List<ProductDTO> getProducts(String companyId) {
		return repository.findByCompanyId(companyId, "Feed", "Broiler");
	}
}
