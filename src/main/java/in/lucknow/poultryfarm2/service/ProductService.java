package in.lucknow.poultryfarm2.service;

import java.util.List;
import in.lucknow.poultryfarm2.dto.ProductDTO;

public interface ProductService {
	List<ProductDTO> getProducts(String companyId);
}
