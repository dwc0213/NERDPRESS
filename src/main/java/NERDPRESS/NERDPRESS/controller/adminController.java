package NERDPRESS.NERDPRESS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class adminController {

    // 관리자 회원관리 adminidmanage - adminIDManage.html
    @GetMapping("adminidmanage")
    public String adminIdManage(){
        return "admin/adminIDManage";
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
