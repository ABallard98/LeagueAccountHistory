package com.ballard.ayden.demo.repositories;

import com.ballard.ayden.demo.league.account.LeagueAccount;
import org.springframework.data.repository.CrudRepository;

public interface LeagueAccountRepository extends CrudRepository<LeagueAccount, Long> {
}
