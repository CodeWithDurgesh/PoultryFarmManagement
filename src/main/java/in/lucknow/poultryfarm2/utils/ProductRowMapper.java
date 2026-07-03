package in.lucknow.poultryfarm2.utils;

import org.springframework.jdbc.core.RowMapper;

import in.lucknow.poultryfarm2.dto.ProductDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<ProductDTO> {

	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductDTO product = new ProductDTO();
		product.setId(rs.getInt("id"));
		product.setProduct(rs.getString("product"));
		product.setCategory(rs.getString("category"));
		product.setUnit(rs.getString("unit"));
		product.setKg(rs.getString("kg"));
		product.setMargin(rs.getString("margin"));
		product.setMaxPurchase(rs.getString("max_purchase"));
		product.setMinPurchase(rs.getString("min_purchase"));
		product.setSaleRate(rs.getString("sale_rate"));
		product.setCompany(rs.getString("company"));
		product.setStdCost(rs.getString("stdcost"));
		product.setHsn(rs.getString("hsn"));
		return product;
	}
}
