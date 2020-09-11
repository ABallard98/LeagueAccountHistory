package com.aydenballard.demo.Controller;

import com.aydenballard.demo.FileReader;
import com.aydenballard.demo.JsonReader;
import com.aydenballard.demo.LeagueAccount;
import com.aydenballard.demo.LeagueAccountBuilder;
import org.json.JSONObject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class HomeRestController {

    @RequestMapping(value = "/euw")
    public LeagueAccount indexEuw(@RequestParam(value = "name") String summonerName) throws IOException {
        String url = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerName
                + "?api_key=" + FileReader.readApiKey();
        JSONObject jsonLeagueAccount = JsonReader.readJsonFromUrl(url);
        return LeagueAccountBuilder.build("euw1", jsonLeagueAccount);
    }

    @RequestMapping(value = "/na1")
    public LeagueAccount indexNa(@RequestParam(value = "name") String summonerName) throws IOException {
        String url = "https://na1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerName
                + "?api_key=" + FileReader.readApiKey();
        JSONObject jsonLeagueAccount = JsonReader.readJsonFromUrl(url);
        return LeagueAccountBuilder.build("na1",jsonLeagueAccount);
    }



}
