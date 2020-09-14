package com.ballard.ayden.demo.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LeagueAccountController {

    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }


}
