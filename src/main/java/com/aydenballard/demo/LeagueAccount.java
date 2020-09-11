package com.aydenballard.demo;

import java.util.ArrayList;
import java.util.List;

public class LeagueAccount {

    private String accountName; //summmoner name of user
    private String regionId; //region id of user
    private String accountId; //encrypted ID of user
    private long summonerLevel; //level of user
    private long profileIconId; //profile icon ID
    private ArrayList<Long> latestMatchIds; //match IDs of last 10 games

    /**
     * Constructor for league account object
     * @param regionId - region of user
     * @param accountName - account name
     * @param accountId - encrypted account ID
     * @param summonerLevel - level of summoner
     * @param profileIconId - profile icon ID
     */
    public LeagueAccount(String regionId, String accountName, String accountId, long summonerLevel, long profileIconId){
        this.regionId = regionId;
        this.accountName = accountName;
        this.accountId = accountId;
        this.summonerLevel = summonerLevel;
        this.profileIconId = profileIconId;
        try{
            this.latestMatchIds = JsonParser.getMatchHistoryIds(regionId,accountId);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Method to get the account name of user
     * @return String - account name
     */
    public String getAccountName(){
        return this.accountName;
    }

    /**
     * Method to get the level of user
     * @return long - account level
     */
    public long getSummonerLevel(){
        return this.summonerLevel;
    }

    /**
     * Method to get the profile icon of user
     * @return long - profile icon ID
     */
    public long getProfileIconId(){
        return this.profileIconId;
    }

    /**
     * Method to get the match IDs of last 10 games played
     * @return List - List of long objects containing match IDs
     */
    public List<Long> getMatchHistory(){
        return latestMatchIds;
    }

}
