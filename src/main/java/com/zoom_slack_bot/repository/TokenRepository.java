package com.zoom_slack_bot.repository;

import com.zoom_slack_bot.entity.Token;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TokenRepository extends CrudRepository<Token, Long> {
    List<Token> getTokensByEmail(String email);
    Optional<Token> getTokenByEmailAndExpDateIsAfter(String email, LocalDate localDate);
}
