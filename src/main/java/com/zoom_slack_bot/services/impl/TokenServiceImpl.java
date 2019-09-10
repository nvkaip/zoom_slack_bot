package com.zoom_slack_bot.services.impl;

import com.zoom_slack_bot.entity.Token;
import com.zoom_slack_bot.repository.TokenRepository;
import com.zoom_slack_bot.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {

    private TokenRepository tokenRepository;

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Optional<Token> getTokenByEmail(String email) {
        return tokenRepository.getTokenByEmail(email);
    }

    @Override
    public Optional<Token> getValidTokenByEmail(String email, LocalDate localDate) {
        return tokenRepository.getTokenByEmailAndExpDateAfter(email, localDate);
    }

    @Override
    public void saveToken(Token token) {
        tokenRepository.save(token);
    }
}
