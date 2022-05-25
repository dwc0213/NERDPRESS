package NERDPRESS.NERDPRESS.controller;

import NERDPRESS.NERDPRESS.Domain.Member;
import NERDPRESS.NERDPRESS.service.MyPageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class MyPageController {
    MyPageService service;
    @GetMapping("myPage")
    public String goToMyPage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);

        if(session==null) {
            try {
                response.setContentType("text/html; charset=euc-kr");
                PrintWriter out = response.getWriter();
                out = response.getWriter();
                out.println("<script>alert('로그인을 아직 하지 않으셨습니다. 먼저 로그인하세요');" +
                        "function load() {window.location.href = \"/login\";};</script>");
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "myPage/myPage";
    }
    @GetMapping("renewMyInfo")
    public String renewMyInfo(){
        return"myPage/renewMyInfo";
    }
    @PostMapping("afterRenewMyInfo")
    public String afterInfoRenewal(
            @RequestParam String name,
            @RequestParam String Email,
            HttpServletRequest request
            ) {
        HttpSession session = request.getSession(false);
        Member memberInfo = new Member();
        LoginAccount member = (LoginAccount)session.getAttribute("loginAccount");
        memberInfo = service.searchByUserID(member.getUserID());
        service.renewAssets("Email",Email, memberInfo.getUserId());
        service.renewAssets("name",name, memberInfo.getUserId());
        return "redirect:renewMyInfo";
    }
    @GetMapping("renewMyPW")
    public String renewMyPW(){
        return"myPage/renewMyPW";
    }
    @PostMapping("afterRenewMyPW")
    public String afterPWRenewal(@RequestParam String PW, @RequestParam String newPW,HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        Member memberInfo = new Member();
        LoginAccount member = (LoginAccount)session.getAttribute("loginAccount");
        memberInfo = service.searchByUserID(member.getUserID());
        if(!PW.equals(memberInfo.getPW())){
            response.setContentType("text/html; charset=euc-kr");
            try {
                PrintWriter out = response.getWriter();
                out = response.getWriter();
                out.println("<script>alert('비밀번호가 틀렸습니다.'); history.go(-1);</script>");
                out.flush();
                return "redirect:renewMyPW";
            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:renewMyPW";
            }
        }
        service.renewAssets(PW, newPW, memberInfo.getUserId());
        return"redirect:logout";
    }
    @GetMapping("purchase")
    public String purchase(){
        return"PointNQuest/purchase";
    }
    @PostMapping("afterPurchase")
    public String afterPurchase(@RequestParam int point, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        Member memberInfo = new Member();
        LoginAccount member = (LoginAccount)session.getAttribute("loginAccount");
        memberInfo = service.searchByUserID(member.getUserID());
        return "redirect:purchase";
    }
    @GetMapping("solveQuest")
    public String solveQuest(Model model){
        model.addAttribute("Quest",service.getQuest(1));
        return"PointNQuest/solveQuest";
    }
    @PostMapping("afterSolveQuest")
    public String afterSolveQuest(@RequestParam String isRight, Model model,HttpServletRequest request){
        HttpSession session = request.getSession(false);
        LoginAccount member =(LoginAccount) session.getAttribute("loginAccount");
        if(isRight.equals("y")){
            service.plusPoint(member.getUserID(), 10);
        } else {
            service.plusPoint(member.getUserID(), -5);
        }
        return"redirect:solveQuest";
    }
    @GetMapping("showMyPoints")
    public String showMyPoints( Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        LoginAccount member =(LoginAccount) session.getAttribute("loginAccount");
        int point = service.inquirePoint(member.getUserID());
        model.addAttribute("point", point);
        return"PointNQuest/showMyPoint";
    }
}

