package com.aydenballard.demo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class JsonParser {

    public static ArrayList<Long> getMatchHistoryIds(String regionId, String accountId) throws IOException {

        ArrayList<Long> matchIds = new ArrayList<>();

        final String URL = "https://" + regionId +".api.riotgames.com/lol/match/v4/matchlists/by-account/" +
                accountId + "?api_key=" + FileReader.readApiKey();
        System.out.println(URL);
        JSONObject jsonMatchHistory = JsonReader.readJsonFromUrl(URL);

        JSONArray matches = jsonMatchHistory.getJSONArray("matches");
        for(int i = 0; i < 10; i++){
            JSONObject match = matches.getJSONObject(i);
            System.out.println(match.toString());
            matchIds.add(match.getLong("gameId"));
        }

        return matchIds;
    }
}
