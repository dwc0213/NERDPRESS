package NERDPRESS.NERDPRESS.controller;

import NERDPRESS.NERDPRESS.domain.Member;
import NERDPRESS.NERDPRESS.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
    //<<로그인 및 회원가입 관련>>
    // 로그인 페이지 login - login.html
    @GetMapping("login")
    public String login(){
        return "login/login";
    }
    // 로그인 후 이동하는 페이지
    @PostMapping("login/afterlogin")
    public String afterLogin(LoginAccount la, HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*
            로그인 객체 생성
         */
        LoginAccount loginAccount = new LoginAccount();
        loginAccount.setUserID(la.getUserID());
        loginAccount.setPW(la.getPW());

        // login.html에서 입력받은 ID를 Spring이 Member m에 담아주고
        // 우리는 그걸 꺼내쓰기만 하면 되죠?
        // 여기가 질문포인트: 로그인 못하면 (추후에 alert 창 추가하고) 다시 이전 페이지로 돌아가기.
        String destURI = (String) request.getAttribute("destURI");
        System.out.println(destURI);
        if(!service.loginVerify(loginAccount.getUserID(), loginAccount.getPW())){
            System.out.println("로그인에 실패하셨습니다. 아이디와 비번이 틀렸습니다.");
            response.setContentType("text/html; charset=euc-kr");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인에 실패하셨습니다. 아이디 또는 비밀번호를 다시 확인하세요.'); history.go(-1);</script>");
            out.flush();
            //출처: https://redcoder.tistory.com/195 [로재의 개발 일기]. 로그인 실패하면 alert 창 띄우고 다시 로그인 페이지로 이동
        }
        System.out.println(loginAccount.getUserID() + "님이 로그인 하셨습니다.");

        /*
            로그인 객체를 세션에 담음
         */
        HttpSession session = request.getSession();

        session.setAttribute("loginAccount", loginAccount);
        System.out.println(((LoginAccount)session.getAttribute("loginAccount")).getUserID() + "님의 로그인 세션이 생성되었습니다.");

        /*
            Interceptor preHandle에서 저장해둔 가던 길이 있으면,
            꺼내서, 다시 가던 길로 보내줌
         */
        destURI = (String) session.getAttribute("destURI");

        if(destURI != null) {  // 마이 페이지 눌렀는데, 로그인 안된 상태라 interceptor가 이리로 보낸거면
            System.out.println("마이페이지를 가려다 로그인으로 빠지셨네요. 다시 마이페이지로 돌아갑니다.");
            session.setAttribute("destURI", null);
            return "redirect:" + destURI;   // 가던길로 돌려줌
        }
        else {  // 로그인 링크를 눌러서 왔으면,
            System.out.println("다시 홈으로 돌아갑니다.");
            return "redirect:/"; // 다시 홈으로
        }
    }
    //로그아웃
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if(session != null) {   // 안에 값이 있으면,
            System.out.println(((LoginAccount)session.getAttribute("loginAccount")).getUserID()+ "님의 로그인 세션이 종료되었습니다.");
            session.invalidate();// session 만료시킴.
        } else {
            System.out.println("로그인 세션이 없습니다.");
        }

        return "redirect:/"; // 홈(index.html) 화면으로
    }
    // 회원가입 signup - signUp.html
    @GetMapping("signUp")
    public String beforeSignup(){
        System.out.println("컨트롤러 정상시행");
        return "login/signUp";
    }
    // 회원가입 후 이동하는 페이지.
    @PostMapping("signup/aftersignup")
    public String afterSignup(@RequestParam String name, @RequestParam String userID, @RequestParam String PW, @RequestParam String birthDate, @RequestParam List<String> email, @RequestParam List<String> male){
        System.out.println(name+userID+PW+male+email);
        Member member = new Member();
        member.setName(name);
        member.setPW(PW);
        member.setUserId(userID);
        member.setBirthDate(Integer.parseInt(birthDate));
        member.setEmail(email.get(0));
        member.setGrade((byte) 0);
        member.setName(name);
        member.setMale(male.get(0).equals("on"));
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
        return "content/contentList";
    }


}
