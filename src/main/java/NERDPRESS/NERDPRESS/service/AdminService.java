package NERDPRESS.NERDPRESS.service;


import NERDPRESS.NERDPRESS.Domain.Member;
import NERDPRESS.NERDPRESS.Repository.MemberRepositoryInterface;

public class AdminService {
    MemberRepositoryInterface memberRepository;

    public AdminService(MemberRepositoryInterface memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member adminIdSearchService(String id){
        Member member = new Member();
        member = memberRepository.getByUserId("id");

        return member;

    }



}
