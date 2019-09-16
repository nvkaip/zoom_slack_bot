package com.zoom_slack_bot.entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "token")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NonNull
    @Column(name = "jwt")
    private String jwt;
    @NonNull
    @Column(name = "email", nullable = false)
    private String email;
    @NonNull
    @Temporal(TemporalType.DATE)
    @Column(name = "exp_date")
    private LocalDate expDate;
}
