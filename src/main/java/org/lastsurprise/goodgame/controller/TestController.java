package org.lastsurprise.goodgame.controller;

import org.lastsurprise.goodgame.annotation.Check;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/test")
    @Check
    @ResponseBody
    public String test(){
        return "ok";
    }
}
