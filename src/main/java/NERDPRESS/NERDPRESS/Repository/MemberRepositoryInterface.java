package NERDPRESS.NERDPRESS.Repository;

import NERDPRESS.NERDPRESS.Domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepositoryInterface {
    void saveMember(Member member);
    Member getById(int id);
    Member getByUserId(String userId);
    List<Member> getAllDomains();

    void setPoint(String userID, int i);

    int getPoint(String userID);

    void changeAsset(String colname, String input, String userID);

    List<Member> pagingList(int page);

}


