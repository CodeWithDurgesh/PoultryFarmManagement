package in.lucknow.poultryfarm2.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.lucknow.poultryfarm2.dto.CompanyDTO;

@Repository
public class CompanyRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<CompanyDTO> getAllCompanies() {

        String sql = "SELECT name,address,country,state,city,pin,contact,email,pan,gst FROM company ORDER BY id ASC";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            CompanyDTO dto = new CompanyDTO();
            dto.setName(rs.getString("name"));
            dto.setAddress(rs.getString("address"));
            dto.setCountry(rs.getString("country"));
            dto.setState(rs.getString("state"));
            dto.setCity(rs.getString("city"));
            dto.setPin(rs.getString("pin"));
            dto.setContact(rs.getString("contact"));
            dto.setEmail(rs.getString("email"));
            dto.setPan(rs.getString("pan"));
            dto.setGst(rs.getString("gst"));
            return dto;
        });
    }
}
