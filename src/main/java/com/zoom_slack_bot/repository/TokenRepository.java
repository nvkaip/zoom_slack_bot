package com.zoom_slack_bot.repository;

import com.zoom_slack_bot.entity.Token;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface TokenRepository extends CrudRepository<Token, Long> {
    Optional<Token> getTokenByEmail(String email);
    Optional<Token> getTokenByEmailAndExpDateAfter(String email, LocalDate localDate);
}
