package com.aydenballard.demo;

import java.util.ArrayList;
import java.util.List;

public class LeagueAccount {

    private String accountName; //summoner name of user
    private String summonerID; //encrypted summoner ID
    private String regionId; //region id of user
    private String accountId; //encrypted ID of user
    private long summonerLevel; //level of user
    private long profileIconId; //profile icon ID

    private ArrayList<Long> lastTenMatchIds; //match IDs of last 10 games
    private ArrayList<Boolean> winLossHistory; //booleans of games won - true if win

    private ArrayList<Integer> mostPlayedChampionIds; //IDs of most played champions
    private ArrayList<String> mostPlayedChampions; //names of most played champions

    /**
     * Constructor for league account object
     * @param regionId - region of user
     * @param accountName - account name
     * @param accountId - encrypted account ID
     * @param summonerLevel - level of summoner
     * @param profileIconId - profile icon ID
     */
    public LeagueAccount(String regionId, String accountName, String summonerID, String accountId, long summonerLevel, long profileIconId){
        //initialise properties
        this.regionId = regionId;
        this.summonerID = summonerID;
        this.accountName = accountName;
        this.accountId = accountId;
        this.summonerLevel = summonerLevel;
        this.profileIconId = profileIconId;
        //initialise lists
        this.mostPlayedChampions = new ArrayList<>();
        this.lastTenMatchIds = new ArrayList<>();
        this.winLossHistory = new ArrayList<>();
        //fill lists with data
        try{
            this.lastTenMatchIds = JsonParser.getMatchHistoryIds(regionId,accountId);
            this.mostPlayedChampionIds = JsonParser.mostPlayedChamps(regionId,summonerID);
            generateMostPlayedChampionsNames();
            generateWinLossHistory();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Method to generate the list of the user's most played champions
     */
    private void generateMostPlayedChampionsNames() {
        for(Integer i : mostPlayedChampionIds){
            mostPlayedChampions.add(JsonParser.getChampionNameFromId(i));
        }
    }

    private void generateWinLossHistory(){
        for(Long l : lastTenMatchIds){
            winLossHistory.add(JsonMatchParser.didPlayerWin(regionId,l,accountId));
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
     * Method to get the encrypted account ID of the user
     * @return String - encrypted account ID
     */
    public String getAccountId(){
        return this.accountId;
    }

    public String getSummonerID(){
        return this.summonerID;
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
    public List<Long> getLastTenMatchIds(){
        return lastTenMatchIds;
    }

    public List<Boolean> getWinLossHistory(){
        return winLossHistory;
    }

    /**
     * Method to get the List of the user's top 5 played champions
     * @return List - list of champion name's
     */
    public List<String> getMostPlayedChampionNames(){
        return this.mostPlayedChampions;
    }


}
