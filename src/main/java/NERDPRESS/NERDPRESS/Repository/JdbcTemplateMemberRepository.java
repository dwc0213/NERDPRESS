package NERDPRESS.NERDPRESS.Repository;

import NERDPRESS.NERDPRESS.Domain.MDetail;
import NERDPRESS.NERDPRESS.Domain.Member;
import NERDPRESS.NERDPRESS.Repository.MemberRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
//import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository
public class JdbcTemplateMemberRepository implements MemberRepositoryInterface {

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
                output.setEmail(res.getString("Email"));
                output.setGrade(res.getByte("grade"));
                output.setMale(res.getBoolean("male"));
                output.setBirthDate(res.getInt("birthdate"));
                return output;
            }
        };
    }
    private RowMapper<MDetail> MDetailRowMapper() {
        return new RowMapper<MDetail>() {
            @Override
            public MDetail mapRow(ResultSet res, int rowNum) throws SQLException {
                MDetail output = new MDetail();
                output.setId(res.getInt("id"));
                output.setMem_id(res.getInt("mem_id"));
                output.setPoint(res.getInt("point"));
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
        parameters.put("birthDate", m.getBirthDate());
        parameters.put("grade", m.getGrade());
        parameters.put("EMail",m.getEmail());
        jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        jdbcInsert.withTableName("m_detail").usingGeneratedKeyColumns("id");
        parameters.clear();
        parameters.put("MEM_ID",m.getUserId());
        parameters.put("POINT", 0);
        jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

    }
    @Override
    public void changeAsset(String colname, String input, String userID){
        String sql=("UPDATE member SET "+colname+" = "+input+" WHERE userID = ?");
        jdbcTemplate.query(sql, MemberRowMapper(), userID);
    }

    @Override
    public List<Member> pagingList(int page) {
        return null;
    }

    @Override
    public Member getById(int id) {
        List<Member> output = jdbcTemplate.query("SELECT * FROM member WHERE id = ?", MemberRowMapper(), id);
        return output.stream().findFirst().get();
    }

    @Override
    public Member getByUserId(String userId) {
        List<Member> output = jdbcTemplate.query("SELECT * FROM member WHERE Userid = ?", MemberRowMapper(), userId);
        return output.stream().findFirst().get();
    }

    @Override
    public List<Member> getAllDomains() {
        return jdbcTemplate.query("SELECT * FROM member", MemberRowMapper());
    }
    public int getPoint(String userID){
        Member user = this.getByUserId(userID);
        List<MDetail> output = jdbcTemplate.query("SELECT * FROM m_detail WHERE mem_id = ?", MDetailRowMapper(), user.getId());
        return output.stream().findFirst().get().getPoint();
    }
    public void setPoint(String userID, int point){
        Member user = this.getByUserId(userID);
        String sql=("UPDATE m_detail SET point = "+point+" WHERE mem_id = ?");
        jdbcTemplate.query(sql, MemberRowMapper(), user.getId());
    }

}
