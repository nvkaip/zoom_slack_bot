package com.zoom_slack_bot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User {

    private String id;

    @JsonProperty("first_name")
    private String firstName;

    private String email;
}
