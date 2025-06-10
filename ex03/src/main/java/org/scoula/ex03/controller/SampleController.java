package org.scoula.ex03.controller;

import lombok.extern.log4j.Log4j2;
import org.scoula.ex03.dto.SampleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/sample")
@Log4j2
public class SampleController {
    @RequestMapping("") // url: /sample
    public void basic() {
        log.info("basic...........");
    }

    @RequestMapping(value="/basic", method = {RequestMethod.GET, RequestMethod.POST}) // url: /sample/basic
    public void basicGet() {
        log.info("basic get...........");
    }

    @GetMapping("/basicOnlyGet") // url: /sample/basicOnlyGet
    public void basicGet2() {
        log.info("basic get only get............");
    }


    @GetMapping("/ex04")
    public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
        log.info("dto: " + dto);
        log.info("page: " + page);
        return "sample/ex04";
    }

    @GetMapping("/ex05")
    public void ex05() {
        log.info("/ex05........");
    }

    @GetMapping("/ex06")
    public String ex06(RedirectAttributes ra) {
        log.info("/ex06........");
        ra.addAttribute("name", "AAA");
        ra.addAttribute("age", 10);

        return "redirect:/sample/ex06-2";
    }
}
