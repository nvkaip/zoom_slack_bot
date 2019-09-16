package com.zoom_slack_bot.util;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.LocalDate;
import static java.sql.Date.valueOf;

@EqualsAndHashCode
@ToString
public class JWTUtil {

    private String jwt;

    public JWTUtil(String issuer, String apiSecret, int days) {
        setJwt(issuer, apiSecret, days);
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String issuer, String apiSecret, int days) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        LocalDate today = LocalDate.now();
        byte[] apiKeySecretBytes = apiSecret.getBytes();
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(valueOf(today))
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);
        if (days > 0) {
            LocalDate expDate = LocalDate.now().plusDays(days);
            builder.setExpiration(valueOf(expDate));
        }
        this.jwt = builder.compact();
    }
}
