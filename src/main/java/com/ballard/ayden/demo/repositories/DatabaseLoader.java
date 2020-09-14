package com.ballard.ayden.demo.repositories;

import com.ballard.ayden.demo.league.account.LeagueAccountBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final LeagueAccountRepository repository;

    @Autowired
    public DatabaseLoader(LeagueAccountRepository repository){
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception{
        this.repository.save(LeagueAccountBuilder.build("euw1","meme_patrol"));
        this.repository.save(LeagueAccountBuilder.build("euw1","quinn_senpai"));
        this.repository.save(LeagueAccountBuilder.build("euw1","hac"));
    }

}
