package com.zoom_slack_bot.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class MeetingsList {

    private List<Meeting> meetings;
}
