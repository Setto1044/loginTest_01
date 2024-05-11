package com.sns.my.member.controller;

import com.sns.my.member.model.Member;
import com.sns.my.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

    private MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    @PostMapping("/login")
    public String checkLogin(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             HttpSession session){
        Member member = memberService.findByUserName(username).get();
        if( member == null || !member.getPassword().equals(password)){
            return "redirect:/login";
        }
        else{
            session.setAttribute("member", member);
            return "redirect:/main";

        }
    }

    @GetMapping("/register")
    public String register(){
        return "/register";
    }
    @PostMapping("/register")
    public String goRegister(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("realname") String realname){
        System.out.println(">>>>>>" + username + " " + password + " " + realname);
        if( memberService.findByUserName(username).isPresent()){
            System.out.println(">>>>>>>>>Error User 존재");
            return "/register";
        }
        else{
            Member member = new Member();
            member.setUsername(username);
            member.setPassword(password);
            member.setRealname(realname);
            memberService.join(member);
            return "redirect:/login";
        }
    }

    @GetMapping("/main")
    public String goMain(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("member");
        if (member != null) {
            model.addAttribute("member", member);
            return "/main";
        }
        else {
            System.out.println(">> No Member Session");
            return "redirect:/login";
        }
    }

    @GetMapping("/json")
    @ResponseBody
    public String getMemberJson(HttpSession session){
        Member member = (Member) session.getAttribute("member");
        return member.toString();
    }
}
