package com.aydenballard.demo;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonMatchParser {

    /**
     * Method to take a Match ID and calculate which team won
     * @param region - region ID
     * @param matchId - match ID
     * @return Boolean - true if blue side won
     */
    public static boolean blueSideWin(String region, Long matchId){
        final String URL = "https://" + region + ".api.riotgames.com/lol/match/v4/matches/" + matchId + "?api_key=" +
                FileReader.readApiKey();
        try{
            JSONObject jsonMatchData = JsonReader.readJsonFromUrl(URL);
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
     * Method to get the champion name the participant chose
     * @param region - region ID
     * @param matchId - match ID
     * @param participantId - participant ID
     * @return
     */
    private static String getChampionPickedInMatchId(String region, Long matchId, int participantId){
        participantId = participantId-1; //must be -1 as participantID starts at 1, but participants start at 0
        final String URL = "https://" + region + ".api.riotgames.com/lol/match/v4/matches/" + matchId + "?api_key=" +
                FileReader.readApiKey();
        try{
            JSONObject jsonMatchData = JsonReader.readJsonFromUrl(URL);
            JSONArray participants = jsonMatchData.getJSONArray("participants");
            JSONObject participantData = participants.getJSONObject(participantId);
            int pickedChampionId = participantData.getInt("championId");
            return JsonParser.getChampionNameFromId(pickedChampionId);
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method to calculate whether a player won their game
     * @param region - region ID
     * @param matchId - match ID
     * @param accountId - account ID
     * @return boolean - true if won
     */
    public static boolean didPlayerWin(String region, Long matchId, String accountId){
        //get participant id
        final String URL = "https://" + region + ".api.riotgames.com/lol/match/v4/matches/" + matchId + "?api_key=" +
                FileReader.readApiKey();
        try{
            JSONObject jsonMatchData = JsonReader.readJsonFromUrl(URL);
            JSONArray jsonParticipants = jsonMatchData.getJSONArray("participantIdentities");
            for(int i = 0; i < jsonParticipants.length(); i++){
                JSONObject participant = jsonParticipants.getJSONObject(i);
                int participantId = participant.getInt("participantId");
                JSONObject playerInfo = participant.getJSONObject("player");

                if(playerInfo.getString("accountId").equals(accountId)){
                    //String championPicked = getChampionPickedInMatchId(region,matchId,participantId-1);
                    if(participantId <= 5){ //if player on blue side
                        if(blueSideWin(region,matchId)){
                            return true;
                        } else {
                            return false;
                        }
                    } else { //else player on red side
                        if(blueSideWin(region,matchId)){
                            return false;
                        } else{
                            return true;
                        }
                    }
                }//end of if
            }//end of for loop
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }



}
