package NERDPRESS.NERDPRESS.service;

import NERDPRESS.NERDPRESS.Domain.Member;
import NERDPRESS.NERDPRESS.Repository.MemberRepositoryInterface;

import java.util.List;

public class MemberService {

    MemberRepositoryInterface memberRepository;

    public MemberService(MemberRepositoryInterface memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> memberListPagingService(int page){

        List<Member> memberlist = memberRepository.pagingList(page);

        return memberlist;
    }

}
