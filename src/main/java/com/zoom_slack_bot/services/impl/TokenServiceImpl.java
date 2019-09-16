package com.zoom_slack_bot.services.impl;

import com.zoom_slack_bot.entity.Token;
import com.zoom_slack_bot.repository.TokenRepository;
import com.zoom_slack_bot.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {

    private TokenRepository tokenRepository;

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public List<Token> getTokensByEmail(String email) {
        return tokenRepository.getTokensByEmail(email);
    }

    @Override
    public Optional<Token> getValidTokenByEmail(String email, LocalDate localDate) {
        return tokenRepository.getTokenByEmailAndExpDateIsAfter(email, localDate);
    }

    @Override
    public Token saveToken(Token token) {
        return tokenRepository.save(token);
    }
}
