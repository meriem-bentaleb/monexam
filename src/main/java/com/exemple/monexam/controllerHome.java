package com.exemple.monexam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "Bienvenue sur l'application MonExam !";
    }
}

