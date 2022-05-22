package NERDPRESS.NERDPRESS.Repository;

import NERDPRESS.NERDPRESS.Domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepositoryInterface {
    void saveMember(Member member);
    Member getById(int id);
    Member getByUserId(String userId);
    Member getByName(String name);
    List<Member> getAllDomains();
}
