package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// for Thymeleaf check
@Controller
public class HelloController {

    @GetMapping("hello") // (/hello) url으로 들어오면 hello.html이 호출된다.
    public String hello(Model model){
        model.addAttribute("data", "hello!");
        return "hello"; //hello.html 호출
    }
}
