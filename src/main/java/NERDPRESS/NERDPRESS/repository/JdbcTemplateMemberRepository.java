package NERDPRESS.NERDPRESS.repository;

import NERDPRESS.NERDPRESS.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTemplateMemberRepository implements MemberRepositoryInterface{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateMemberRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    private RowMapper<Member> MemberRowMapper() {
        return new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet res, int rowNum) throws SQLException {
                Member output = new Member();
                output.setId(res.getInt("id"));
                output.setUserId(res.getString("userId"));
                output.setName(res.getString("name"));
                output.setPW(res.getString("PW"));
                output.setGrade(res.getByte("grade"));
                output.setMale(res.getBoolean("male"));
                output.setBirthDate(res.getInt("birthdate"));
                return output;
            }
        };
    }
    @Override
    public void saveMember(Member m) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", m.getName());
        parameters.put("userId", m.getUserId());
        parameters.put("Male", m.getMale());
        parameters.put("PW", m.getPW());
        parameters.put("birthDate", m.getUserId());
        parameters.put("grade", m.getUserId());
        jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
    }

    @Override
    public Member getById(int id) {
        List<Member> output = jdbcTemplate.query("SELECT * FROM member WHERE id = ?", MemberRowMapper(), id);
        return output.stream().findFirst().get();
    }

    @Override
    public Member getByUserId(String userId) {
        List<Member> output = jdbcTemplate.query("SELECT * FROM member WHERE id = ?", MemberRowMapper(), userId);
        return output.stream().findFirst().get();
    }

    @Override
    public List<Member> getAllDomains() {
        return jdbcTemplate.query("SELECT * FROM profile", MemberRowMapper());
    }


}
