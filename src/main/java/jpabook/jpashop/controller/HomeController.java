package jpabook.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j  //log를 찍어주는 annotation
public class HomeController {


    @RequestMapping("/")
    public String home(){
        log.info("home controller");
        return "home";
    }
}