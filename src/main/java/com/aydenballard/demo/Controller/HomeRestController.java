package com.aydenballard.demo.Controller;

import com.aydenballard.demo.*;
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

    @RequestMapping(value = "/na")
    public LeagueAccount indexNa(@RequestParam(value = "name") String summonerName) throws IOException {
        String url = "https://na1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerName
                + "?api_key=" + FileReader.readApiKey();
        JSONObject jsonLeagueAccount = JsonReader.readJsonFromUrl(url);
        return LeagueAccountBuilder.build("na",jsonLeagueAccount);
    }

    @RequestMapping(value = "/euw/game")
    public LeagueMatch indexEuwGame(@RequestParam(value = "match") long matchId) throws IOException {
        final String URL = "https://euw1.api.riotgames.com/lol/match/v4/matches/" + matchId + "?api_key=" +
                FileReader.readApiKey();
        JSONObject jsonLeagueMatch = JsonReader.readJsonFromUrl(URL);
        return LeagueMatchBuilder.build("euw1",matchId,jsonLeagueMatch);
    }

    @RequestMapping(value = "/na/game")
    public LeagueMatch indexNaGame(@RequestParam(value = "match") long matchId) throws IOException {
        final String URL = "https://na1.api.riotgames.com/lol/match/v4/matches/" + matchId + "?api_key=" +
                FileReader.readApiKey();
        JSONObject jsonLeagueMatch = JsonReader.readJsonFromUrl(URL);
        return LeagueMatchBuilder.build("na1",matchId,jsonLeagueMatch);
    }



}
