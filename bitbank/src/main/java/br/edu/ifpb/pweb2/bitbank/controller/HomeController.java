package br.edu.ifpb.pweb2.bitbank.controller;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

//    @RequestMapping(value = "/home", method = RequestMethod.POST)
    @GetMapping("/home")
    public String showHomepage() {
        return "index";
    }
}
