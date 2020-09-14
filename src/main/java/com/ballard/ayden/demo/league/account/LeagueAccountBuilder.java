package com.ballard.ayden.demo.league.account;

import com.ballard.ayden.demo.json.JsonUrlReader;
import com.ballard.ayden.demo.util.FileReader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

public class LeagueAccountBuilder {

    /**
     * Builder method to construct a LeagueAccount object
     * @param regionId - region ID
     * @param summonerName - summoner name of user
     * @return LeagueAccount - account
     */
    public static LeagueAccount build(String regionId, String summonerName){
        try{

            final String URL = "https://"+regionId+".api.riotgames.com/lol/summoner/v4/summoners/by-name/"
                    + summonerName + "?api_key=" + FileReader.readApiKey();

            JSONObject jsonObject = JsonUrlReader.readJsonFromUrl(URL);

            String accountName = jsonObject.getString("name");
            String encryptedSummonerId = jsonObject.getString("id");
            String accountId = jsonObject.getString("accountId");
            //todo get main champ info
            String mainChampion = getChampionNameFromId(getMainChamp(regionId,encryptedSummonerId));
            long summonerLevel = jsonObject.getLong("summonerLevel");
            long profileIconId = jsonObject.getLong("profileIconId");

            return new LeagueAccount(regionId, accountId, encryptedSummonerId, accountName, summonerLevel,
                    profileIconId, mainChampion);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static int getMainChamp(String regionId, String summonerId){
        final String URL = "https://" + regionId + ".api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-summoner/" +
                summonerId + "?api_key=" + FileReader.readApiKey();
        System.out.println(URL);
        try{
            JSONArray jsonMostPlayedChamps = JsonUrlReader.readJsonArrayFromUrl(URL);
            JSONObject champion = jsonMostPlayedChamps.getJSONObject(0);
            return champion.getInt("championId");
        } catch(Exception e){
            return 0;
        }
    }

    public static String getChampionNameFromId(int id) {
        final String URL = "https://ddragon.leagueoflegends.com/cdn/10.18.1/data/en_US/champion.json";
        try{
            JSONObject jsonChampions = JsonUrlReader.readJsonFromUrl(URL);
            JSONObject jsonChampionData = jsonChampions.getJSONObject("data");
            Iterator<String> keys = jsonChampionData.keys();

            while(keys.hasNext()){
                String key = keys.next();
                if(jsonChampionData.get(key) instanceof JSONObject){
                    if(((JSONObject) jsonChampionData.get(key)).getInt("key") == id){
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
