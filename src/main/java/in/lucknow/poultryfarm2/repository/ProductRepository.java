package in.lucknow.poultryfarm2.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import in.lucknow.poultryfarm2.dto.ProductDTO;
import in.lucknow.poultryfarm2.utils.ProductRowMapper;

import java.util.List;

@Repository
public class ProductRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<ProductDTO> findByCompanyId(String companyId, String category, String min_purchase) {
		final String QUERY = "SELECT id,product,category,unit,kg,margin,max_purchase,min_purchase,"
				+ "sale_rate,company,stdcost,hsn FROM product "
				+ "WHERE company LIKE ? AND category = ? AND min_purchase = ?";
		return jdbcTemplate.query(QUERY, new ProductRowMapper(), companyId, category, min_purchase);
	}
}
