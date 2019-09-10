package com.zoom_slack_bot.services;

import com.zoom_slack_bot.entity.Token;

import java.time.LocalDate;
import java.util.Optional;

public interface TokenService {
    Optional<Token> getTokenByEmail (String email);
    Optional<Token> getValidTokenByEmail (String email, LocalDate localDate);
    void saveToken(Token token);
}
