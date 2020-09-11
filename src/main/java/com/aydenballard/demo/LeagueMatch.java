package com.aydenballard.demo;

import java.util.ArrayList;

public class LeagueMatch {

    private Long matchId;
    private String regionId;


    private ArrayList<LeagueAccount> blueSidePlayers;
    private ArrayList<LeagueAccount> redSidePlayers;

    private ArrayList<String> blueSideBans;
    private ArrayList<String> redSideBans;

    private ArrayList<String> blueSidePicks;
    private ArrayList<String> redSidePicks;

    private boolean blueTeamWin;

    public LeagueMatch(String regionId, Long matchId){
        this.regionId = regionId;
        this.matchId = matchId;
        this.blueTeamWin = JsonMatchParser.blueSideWin(regionId,matchId);

    }





}
