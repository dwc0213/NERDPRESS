package NERDPRESS.NERDPRESS.repository;

import NERDPRESS.NERDPRESS.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface MemberRepositoryInterface {
    void saveMember(Member member);
    Member getById(int id);
    Member getByUserId(String userId);
    List<Member> getAllDomains();
}
