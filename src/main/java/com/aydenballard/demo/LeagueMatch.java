package com.aydenballard.demo;

import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LeagueMatch {

    private JSONObject jsonMatchData;

    private Long matchId; //match ID of the game
    private String regionId; //region ID of the game
    private String gameMode; //game mode

    private ArrayList<String> blueSidePlayers; //account IDs of blue team
    private ArrayList<String> redSidePlayers; //account IDs of red team

    private ArrayList<String> blueSideBans; //champion IDs of blue side bans
    private ArrayList<String> redSideBans; //champions IDs of red side bans

    private ArrayList<String> blueSidePicks; //champion IDs of blue side picks
    private ArrayList<String> redSidePicks; //champion IDs of red side picks

    private boolean blueTeamWin; //true if blue won, false if red won

    public LeagueMatch(String regionId, Long matchId, JSONObject jsonMatchData){
        this.jsonMatchData = jsonMatchData;
        this.regionId = regionId;
        this.matchId = matchId;
        this.blueTeamWin = setBlueSideWin();
        //populate players on each team
        this.blueSidePlayers = setBlueSidePlayers();
        this.redSidePlayers = setRedSidePlayers();
        //populate picks of each team
        this.blueSidePicks = setBlueSidePicks();
        this.redSidePicks = setRedSidePicks();
        //populate bans of each team
        this.blueSideBans = setBlueSideBans();
        this.redSideBans = setRedSideBans();
    }

    /**
     * Method to allocate the BlueSideWin boolean to correspond to the winning team
     * @return boolean - true if blue win, else false
     */
    private boolean setBlueSideWin(){
        try{
            JSONArray jsonTeamsDataArray = jsonMatchData.getJSONArray("teams");
            JSONObject jsonBlueSide = jsonTeamsDataArray.getJSONObject(0);
            String winOrLoss = jsonBlueSide.getString("win");
            if(winOrLoss.equals("Fail")){
                return false;
            } else{
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Method to populate the BlueSidePlayers ArrayList
     * @return ArrayList - String of account IDs
     */
    private ArrayList<String> setBlueSidePlayers(){
        ArrayList<String> blueSidePlayers = new ArrayList<>();
        JSONArray participantIdentities = jsonMatchData.getJSONArray("participantIdentities");
        for(int i = 0; i < 5; i++){
            JSONObject participant = participantIdentities.getJSONObject(i);
            JSONObject participantData = participant.getJSONObject("player");
            blueSidePlayers.add(participantData.getString("accountId"));
        }
        return blueSidePlayers;
    }

    /**
     * Method to populate the RedSidePlayers ArrayList
     * @return ArrayList - String of account IDs
     */
    private ArrayList<String> setRedSidePlayers(){
        ArrayList<String> redSidePlayers = new ArrayList<>();
        JSONArray participantIdentities = jsonMatchData.getJSONArray("participantIdentities");
        for(int i = 5; i < 10; i++){
            JSONObject participant = participantIdentities.getJSONObject(i);
            JSONObject participantData = participant.getJSONObject("player");
            redSidePlayers.add(participantData.getString("accountId"));
        }
        return redSidePlayers;
    }

    /**
     * Method to populate the BlueSidePicks ArrayList
     * @return ArrayList - Names of champions picked by blue team
     */
    private ArrayList<String> setBlueSidePicks(){
        ArrayList<String> blueSidePicks = new ArrayList<>();
        JSONArray participants = jsonMatchData.getJSONArray("participants");
        for(int i = 0; i < 5; i++) {
            JSONObject participant = participants.getJSONObject(i);
            int championId = participant.getInt("championId");
            blueSidePicks.add(JsonParser.getChampionNameFromId(championId));
        }
        return blueSidePicks;
    }

    /**
     * Method to populate the RedSidePicks ArrayList
     * @return ArrayList - Names of champions picked by red team
     */
    private ArrayList<String> setRedSidePicks(){
        ArrayList<String> redSidePicks = new ArrayList<>();
        JSONArray participants = jsonMatchData.getJSONArray("participants");
        for(int i = 5; i < 10; i++) {
            JSONObject participant = participants.getJSONObject(i);
            redSidePicks.add(JsonParser.getChampionNameFromId(participant.getInt("championId")));
        }
        return redSidePicks;
    }

    /**
     * Method to populate the BlueSideBans ArrayList
     * @return ArrayList - names of champions banned by blue team
     */
    private ArrayList<String> setBlueSideBans(){
        ArrayList<String> blueSideBans = new ArrayList<>();
        JSONObject teamData = jsonMatchData.getJSONArray("teams").getJSONObject(0);
        JSONArray teamBans = teamData.getJSONArray("bans");
        for(int i = 0; i < 5; i++){
            blueSideBans.add(
                    JsonParser.getChampionNameFromId(teamBans.getJSONObject(i).getInt("championId")));
        }
        return blueSideBans;
    }

    /**
     * Method to populate the red side bans ArrayList
     * @return ArrayList - names of champions banned by red team
     */
    private ArrayList<String> setRedSideBans(){
        ArrayList<String> redSideBans = new ArrayList<>();
        JSONObject teamData = jsonMatchData.getJSONArray("teams").getJSONObject(1);
        JSONArray teamBans = teamData.getJSONArray("bans");
        for(int i = 0; i < 5; i++){
            redSideBans.add(
                    JsonParser.getChampionNameFromId(teamBans.getJSONObject(i).getInt("championId")));
        }
        return redSideBans;
    }

    /**
     * Method to return the BlueSideWin boolean
     * @return boolean - true if blue won, else false
     */
    public boolean getBlueSideWin(){
        return this.blueTeamWin;
    }

    /**
     * Method to return the account IDs of blue side players
     * @return List
     */
    public List<String> getBlueSidePlayers(){
        return this.blueSidePlayers;
    }

    /**
     * Method to return the account IDs of red side players
     * @return List
     */
    public List<String> getRedSidePlayers(){
        return this.redSidePlayers;
    }

    /**
     * Method to return the names of champions picked by blue side
     * @return List
     */
    public List<String> getBlueSidePicks(){
        return this.blueSidePicks;
    }

    /**
     * Method to return the names of champions picked by red side
     * @return List
     */
    public List<String> getRedSidePicks(){
        return this.redSidePicks;
    }

    /**
     * Method to return the names of champions banned by blue side
     * @return List
     */
    public List<String> getBlueSideBans(){
        return this.blueSideBans;
    }

    /**
     * Method to return the names of champions banned by red side
     * @return List
     */
    public List<String> getRedSideBans(){
        return this.redSideBans;
    }

}
