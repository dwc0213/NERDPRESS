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

        List<Member> memberList = memberRepository.pagingList(page);

        return memberList;
    }

    public List<Member> writerListPagingService(int page){

        List<Member> writerList = memberRepository.pagingWriterList(page);

        return writerList;
    }

}
