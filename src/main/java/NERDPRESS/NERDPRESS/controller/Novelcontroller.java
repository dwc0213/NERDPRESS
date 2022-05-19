package NERDPRESS.NERDPRESS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Novelcontroller {

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
    @GetMapping("signup")
    public String signup(){
        return "login/signUp";
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
