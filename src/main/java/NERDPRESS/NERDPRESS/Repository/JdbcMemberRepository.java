package NERDPRESS.NERDPRESS.Repository;

import NERDPRESS.NERDPRESS.Domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcMemberRepository implements MemberRepositoryInterface{
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public JdbcMemberRepository(DataSource dataSource) {jdbcTemplate = new JdbcTemplate(dataSource);}
    private RowMapper<Member> MemberRowMapper() {
        return new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet res, int rowNum) throws SQLException {
                Member output = new Member();
                output.setId(res.getInt("id"));
                output.setGrade(res.getByte("grade"));
                output.setBirthDate(res.getInt("birthdate"));
                output.setPW(res.getString("PW"));
                if(res.getInt("ismale")==0) {output.setIsMale(false);}
                else {output.setIsMale(true);}
                output.setName(res.getString("name"));
                return output;
            }
        };
    }
    @Override
    public void saveMember(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap();
        parameters.put("name",member.getName());
        parameters.put("userId",member.getUserId());
        parameters.put("grade",member.getGrade());
        parameters.put("PW",member.getPW());
        parameters.put("isMale",member.getIsMale());
        parameters.put("birthdate",member.getBirthDate());
        parameters.put("ID",member.getId());
        jdbcInsert.execute(parameters);
    }

    @Override
    public Member getById(int id) {
        List<Member> output = jdbcTemplate.query("SELECT * FROM member WHERE id = ?", MemberRowMapper(), id);
        return output.stream().findFirst().get();
    }

    @Override
    public Member getByUserId(String userId) {
        List<Member> output = jdbcTemplate.query("SELECT * FROM member WHERE userId = ?", MemberRowMapper(), userId);
        return output.stream().findFirst().get();
    }

    @Override
    public Member getByName(String name) {
        List<Member> output = jdbcTemplate.query("SELECT * FROM member WHERE name = ?", MemberRowMapper(), name);
        return output.stream().findFirst().get();
    }

    @Override
    public List<Member> getAllDomains() {
        return jdbcTemplate.query("SELECT * FROM profile", MemberRowMapper());
    }
}
