package NERDPRESS.NERDPRESS.controller;

import NERDPRESS.NERDPRESS.Domain.Member;
import NERDPRESS.NERDPRESS.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Novelcontroller {
    NovelService service;
    @Autowired
    public Novelcontroller(NovelService service) {
        this.service = service;
    }

    // 메인페이지로 이동하는 컨트롤러 mainpage - index.html
    @GetMapping("mainpage")
    public String mainPage(){
        return "redirect:/";
    }

    // 작가리스트 writerlist - WriterList.html
    @GetMapping("writerlist")
    public String writerList(){
        return "content/WriterList";
    }

    // 로그인 페이지 login - login.html
    @GetMapping("login")
    public String login(){
        return "login/login";
    }

    // 회원가입 signup - signUp.html
    @GetMapping("signUp")
    public String beforeSignup(){
        System.out.println("컨트롤러 정상시행");
        return "login/signUp";
    }
    @PostMapping("signup/aftersignup")
    public String afterSignup(@RequestParam String name,@RequestParam String userID, @RequestParam String PW, @RequestParam String birthDate, @RequestParam String email,@RequestParam String male){
        System.out.println(name+userID+PW+male+email);
        Member member = new Member();
        member.setName(name);
        member.setPW(PW);
        member.setUserId(userID);
        member.setBirthDate(Integer.parseInt(birthDate));
        member.setEmail(email);
        member.setGrade((byte) 0);
        member.setName(name);
        if(male.equals("10")){
            member.setMale(true);
        } else {
            member.setMale(false);
        }
        service.newMemJoin(member);
        return "redirect:/";

    }

    // 웹소설 검색 searchnovel - SearchNovel.html
    @GetMapping("searchnovel")
    public String searchNovel(){
        return "content/SearchNovel";
    }

    // 월간베스트 monthbest - monthBest.html
    @GetMapping("monthbest")
    public String monthBest(){
        return "content/monthBest";
    }

    // 최신 웹소설 목록 newest - newest.html
    @GetMapping("newest")
    public String newest(){
        return "content/newest";
    }

    // 작가 정보 writerinfro - WriterInformation.html
    @GetMapping("writerinfo")
    public String writerInfo(){
        return "content/WriterInformation";
    }

    // 작품 회차 리스트 contentlist - contentList.html
    @GetMapping("contentlist")
    public String contentList(){
        return "content/contestList";
    }

}
