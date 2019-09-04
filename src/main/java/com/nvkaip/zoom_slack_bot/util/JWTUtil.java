package com.nvkaip.zoom_slack_bot.util;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

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
        Date now = new Date();
        byte[] apiKeySecretBytes = apiSecret.getBytes();
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);
        if (days > 0) {
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.DAY_OF_MONTH, days);
            Date expDate = c.getTime();
            builder.setExpiration(expDate);
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
