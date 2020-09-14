package com.ballard.ayden.demo.league.account;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class LeagueAccount {

    private @Id String accountId;
    private String summonerId;
    private String accountName;
    private String regionId;
    private long summonerLevel;
    private long profilePicId;
    private String mainChampion;

    public LeagueAccount(){

    }

    public LeagueAccount(String regionId, String accountId, String summonerId,
                         String accountName, long summonerLevel, long profilePicId, String mainChampion){
        this.accountName = accountName;
        this.accountId = accountId;
        this.regionId = regionId;
        this.summonerId = summonerId;
        this.summonerLevel = summonerLevel;
        this.profilePicId = profilePicId;
        this.mainChampion = mainChampion;
    }


    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId){
        this.summonerId = summonerId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName){
        this.accountName = accountName;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId){
        this.regionId = regionId;
    }

    public long getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(long summonerLevel){
        this.summonerLevel = summonerLevel;
    }

    public long getProfilePicId() {
        return profilePicId;
    }

    public void setProfilePicId(long profilePicId){
        this.profilePicId = profilePicId;
    }

    public String getMainChampion() {
        return mainChampion;
    }

    public void setMainChampion(String mainChampion){
        this.mainChampion = mainChampion;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || o.getClass() != getClass()) return false;
        LeagueAccount toCompare = (LeagueAccount) o;
        return Objects.equals(summonerId,toCompare.summonerId) &&
                Objects.equals(regionId, toCompare.regionId) &&
                Objects.equals(accountName,toCompare.accountName) &&
                Objects.equals(summonerLevel,toCompare.summonerLevel);
    }

    @Override
    public int hashCode(){
        return Objects.hash(summonerId, regionId, accountName, summonerLevel);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + summonerId +
                ", region_id='" + regionId + '\'' +
                ", account_name='" + accountName + '\'' +
                ", summoner_level='" + summonerLevel + '\'' +
                '}';
    }
}
