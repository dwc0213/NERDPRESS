package NERDPRESS.NERDPRESS.service;

import NERDPRESS.NERDPRESS.Domain.Member;
import NERDPRESS.NERDPRESS.Domain.Novel;
import NERDPRESS.NERDPRESS.Repository.MemberRepositoryInterface;
import NERDPRESS.NERDPRESS.Repository.NovelRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NovelService {
    private MemberRepositoryInterface memberRepository;
    private NovelRepositoryInterface novelRepository;

    @Autowired
    public NovelService(
            MemberRepositoryInterface memberRepository,
            NovelRepositoryInterface novelRepository
    ) {
        this.memberRepository=memberRepository;
        this.novelRepository=novelRepository;
    }
    public void newMemJoin(Member member){
        memberRepository.saveMember(member);
    }

}
