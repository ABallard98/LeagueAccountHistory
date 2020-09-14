package com.ballard.ayden.demo.account.controller;

import com.ballard.ayden.demo.league.account.LeagueAccount;
import com.ballard.ayden.demo.league.account.LeagueAccountBuilder;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountRestController {

    @RequestMapping(value = "/api/euw")
    public LeagueAccount euwAccount(@RequestParam(value="name") String summonerName){
        try{
            return LeagueAccountBuilder.build("euw1",summonerName);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/api/na")
    public LeagueAccount naAccount(@RequestParam(value="name") String summonerName){
        try{
            return LeagueAccountBuilder.build("na1",summonerName);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
