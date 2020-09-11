package com.aydenballard.demo;

import org.json.JSONObject;


public class LeagueAccountBuilder {

    /**
     * Builder method to construct a LeagueAccount object
     * @param regionId - region ID
     * @param jsonObject - JSONObject of grabbed data
     * @return LeagueAccount - account
     */
    public static LeagueAccount build(String regionId, JSONObject jsonObject){
        String accountName = jsonObject.getString("name");
        String accountId = jsonObject.getString("accountId");
        long summonerLevel = jsonObject.getLong("summonerLevel");
        long profileIconId = jsonObject.getLong("profileIconId");

        return new LeagueAccount(regionId,accountName, accountId, summonerLevel,profileIconId);
    }




}
