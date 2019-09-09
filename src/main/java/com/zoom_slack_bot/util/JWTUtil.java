package com.zoom_slack_bot.util;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.LocalDate;
import java.util.Objects;
import static java.sql.Date.valueOf;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JWTUtil jwtUtil = (JWTUtil) o;
        return Objects.equals(jwt, jwtUtil.jwt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jwt);
    }

    @Override
    public String toString() {
        return "JWTUtil{" +
                "jwt='" + jwt + '\'' +
                '}';
    }
}
