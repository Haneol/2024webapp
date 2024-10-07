package org.example.simplebbs.controller;

import lombok.RequiredArgsConstructor;
import org.example.simplebbs.service.ISimpleBbsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor // Constructor Dependency Injection
public class MyController {
    private final ISimpleBbsService service;

    @RequestMapping("/")
    public String root() {
        return "redirect:list";
    }

    @RequestMapping("/list")
    public String listPage(Model model) {
        model.addAttribute("lists", service.list());
        model.addAttribute("count", service.count());
        return "list";
    }

    @RequestMapping("/view") // view?id=1
    public String view(HttpServletRequest request,Model model) {
        String sId = request.getParameter("id");
        model.addAttribute("dto", service.view(sId));
        return "view";
    }

    @RequestMapping("/writeForm")
    public String writer() {
        return "writeForm";
    }

    @RequestMapping("/write")
    public String write(HttpServletRequest request) {
        service.write(
                request.getParameter("writer"),
                request.getParameter("title"),
                request.getParameter("content")
        );
        return "redirect:list";
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request) {
        service.delete(request.getParameter("id"));

        return "redirect:list";
    }


}