package NERDPRESS.NERDPRESS.controller;

import NERDPRESS.NERDPRESS.Domain.Member;
import NERDPRESS.NERDPRESS.Repository.JdbcMemberRepository;
import NERDPRESS.NERDPRESS.service.AdminService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class adminController {

    JdbcTemplate jdbcTemplate;
    JdbcMemberRepository jdbcMemberRepository;

    AdminService adminService;

    // 관리자 회원관리 adminidmanage - adminIDManage.html
    @GetMapping("adminidmanage")
    public String adminIdManage(Model model){

        return "admin/adminIDManage";
    }

    @PostMapping("idsearch")
    public String idSearch(@RequestParam ("idsearch") String idsearch, Model model) {
        Member member = new Member();
        member = adminService.adminIdSearchService("idsearch");

        model.addAttribute("memberId", member.getId());
        model.addAttribute("memberUserId", member.getUserId());
        model.addAttribute("memberEmail", member.getEmail());
        model.addAttribute("memberGrade", member.getGrade());


        return "admin/IDSearching";
    }

    // 관리자 작가관리 adminwritermanage - adminWriteManage.html
    @GetMapping("adminwritermanage")
    public String adminWriterManage(){
        return "admin/adminWriterManage";
    }

    // 관리자 소설관리 adminnovelmanage - adminNovelManage.html
    @GetMapping("adminnovelmanage")
    public String adminNovelManage(){
        return "admin/adminNovelManage";
    }

    // 관리자 자격증 문제 등록 adminaddquest - adminAddQuest.html
    @GetMapping("adminaddquest")
    public String adminAddQuest(){
        return "admin/adminAddQuest";
    }

    // 관리자 자격증 문제 보기 adminquestmanage
    @GetMapping("adminquestmanage")
    public String adminQuestManage(){
        return "admin/adminQuestManage";
    }

    // 신고내역 관리
    @GetMapping("adminreportmanage")
    public String adminReportManage(){
        return "admin/adminReportManage";
    }

}
