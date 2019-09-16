package com.zoom_slack_bot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Meeting {

    private String uuid; // Meeting unique ID.
    private String id; // Meeting ID - also known as the meeting number.
    @JsonProperty("account_id")
    private String accountId; // ID of the user account.
    @JsonProperty("host_id")
    private String hostId; // ID of the user set as host of meeting.
    private String topic; // Meeting topic.
    @JsonProperty("start_time")
    private String startTime; // Meeting start time. Format: date-time
    private int duration; // Meeting duration.
    @JsonProperty("total_size")
    private String totalSize; // Total size.
    @JsonProperty("recording_count")
    private String recordingCount; // Recording count.
    @JsonProperty("recording_files")
    private List<Recording> recordingFiles; // List of recording file.
}
