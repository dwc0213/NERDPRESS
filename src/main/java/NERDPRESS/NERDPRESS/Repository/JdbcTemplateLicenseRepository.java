package NERDPRESS.NERDPRESS.repository;

import NERDPRESS.NERDPRESS.domain.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class JdbcTemplateLicenseRepository implements LicenseRepositoryInterface{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateLicenseRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<License> licenseRowMapper() {
        return new RowMapper<License>() {
            @Override
            public License mapRow(ResultSet rs, int rowNum) throws SQLException {
                License license = new License();

                license.setLicense_id(rs.getInt("License_id"));
                license.setLicense_type(rs.getString("License_type"));

                return license;
            }
        };
    }


    @Override
    public void saveLicense(License l) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("License_id", l.getLicense_id());
        parameters.put("License_type", l.getLicense_type());

        jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
    }


}
