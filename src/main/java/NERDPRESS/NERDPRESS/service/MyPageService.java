package NERDPRESS.NERDPRESS.service;

import NERDPRESS.NERDPRESS.Domain.Member;
import NERDPRESS.NERDPRESS.Domain.Quest;
import NERDPRESS.NERDPRESS.Repository.MemberRepositoryInterface;
import NERDPRESS.NERDPRESS.Repository.QuestRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyPageService {
    private MemberRepositoryInterface memberRepository;
    private QuestRepositoryInterface questRepository;
    @Autowired
    public MyPageService(
            MemberRepositoryInterface memberRepository,
            QuestRepositoryInterface questRepository
            ){
        this.memberRepository=memberRepository;
        this.questRepository=questRepository;

    }
    public Member searchByUserID(String userID) {
        return memberRepository.getByUserId(userID);
    }
    public void renewAssets(String colname, String input, String userID){
        memberRepository.changeAsset(colname, input, userID);
    }
    public void plusPoint(String userID, int point){ //point에 음수 입력하면 마이너스됨.
        int presentPoint=memberRepository.getPoint(userID);
        if(presentPoint+point>0){
            memberRepository.setPoint(userID, presentPoint+point);
        }
    }
    public int inquirePoint(String userID){
        return memberRepository.getPoint(userID);
    }
    public List<Quest> getQuest(int licenseId) {
        return questRepository.findQuest(licenseId);
    }
}
