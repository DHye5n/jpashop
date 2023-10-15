package jpabook.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j // api 요청
public class HomeController {

    @RequestMapping("/")
    public String home() {
        log.info("home controller");  // Slf4j에 있는 기능(로그남기기)
        return "home";
    }
}
