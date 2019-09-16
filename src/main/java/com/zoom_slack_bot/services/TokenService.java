package com.zoom_slack_bot.services;

import com.zoom_slack_bot.entity.Token;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TokenService {
    List<Token> getTokensByEmail (String email);
    Optional<Token> getValidTokenByEmail (String email, LocalDate localDate);
    Token saveToken(Token token);
}
