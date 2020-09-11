package com.aydenballard.demo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class JsonParser {

    /**
     * Method to collect and return a List of the match IDs of the user's last 10 games played
     * @param regionId - region ID of user
     * @param accountId - account ID of user
     * @return List - List of long objects containing match IDs
     * @throws IOException
     */
    public static ArrayList<Long> getMatchHistoryIds(String regionId, String accountId) throws IOException {

        ArrayList<Long> matchIds = new ArrayList<>();

        final String URL = "https://" + regionId +".api.riotgames.com/lol/match/v4/matchlists/by-account/" +
                accountId + "?api_key=" + FileReader.readApiKey();

        JSONObject jsonMatchHistory = JsonReader.readJsonFromUrl(URL);

        JSONArray matches = jsonMatchHistory.getJSONArray("matches");
        for(int i = 0; i < 10; i++){
            JSONObject match = matches.getJSONObject(i);
            matchIds.add(match.getLong("gameId"));
        }

        return matchIds;
    }

    /**
     * Method to return the 5 most played champions of a user
     * @param regionId - region ID
     * @param summonerId - summoner ID
     * @return List - list of ints containing IDs of most played champions
     * @throws IOException
     */
    public static ArrayList<Integer> mostPlayedChamps(String regionId, String summonerId) throws IOException {
        ArrayList<Integer> mostPlayedChamps = new ArrayList<>();

        final String URL = "https://" + regionId + ".api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-summoner/" +
                summonerId + "?api_key=" + FileReader.readApiKey();

        System.out.println(URL);

        JSONArray jsonMostPlayedChamps = JsonReader.readJsonArrayFromUrl(URL);

        for(int i = 0; i < 5; i++){
            JSONObject champion = jsonMostPlayedChamps.getJSONObject(i);
            mostPlayedChamps.add(champion.getInt("championId"));
        }

        return mostPlayedChamps;
    }

    /**
     * Method to collect the name of a champion when given their ID
     * @param id - ID of champion
     * @return String - name of Champion
     */
    public static String getChampionNameFromId(int id) {
        final String URL = "https://ddragon.leagueoflegends.com/cdn/10.18.1/data/en_US/champion.json";
        try{
            JSONObject jsonChampions = JsonReader.readJsonFromUrl(URL);
            JSONObject jsonChampionData = jsonChampions.getJSONObject("data");
            Iterator<String> keys = jsonChampionData.keys();

            while(keys.hasNext()){
                String key = keys.next();
                if(jsonChampionData.get(key) instanceof JSONObject){
                    if(((JSONObject) jsonChampionData.get(key)).getInt("key") == id){
                        System.out.println("CHAMP FOUND - " +
                                ((JSONObject) jsonChampionData.get(key)).getString("name"));
                        return ((JSONObject) jsonChampionData.get(key)).getString("name");
                    }
                }
            }

            return "error";
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
