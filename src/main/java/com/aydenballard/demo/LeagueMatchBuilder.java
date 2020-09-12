package com.aydenballard.demo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class LeagueMatchBuilder {

    /**
     * Method to return a new LeagueMatch object
     * @param regionId - region ID
     * @param matchId - match ID
     * @param jsonMatchData - JSON data of match returned from riot API
     * @return LeagueMatch
     */
    public static LeagueMatch build(String regionId, long matchId, JSONObject jsonMatchData){
        return new LeagueMatch(regionId, matchId, jsonMatchData);
    }




}
