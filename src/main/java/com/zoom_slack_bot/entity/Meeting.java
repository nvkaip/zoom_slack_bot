package com.zoom_slack_bot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

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

    public Meeting() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String account_id) {
        this.accountId = account_id;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String host_id) {
        this.hostId = host_id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String start_time) {
        this.startTime = start_time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(String total_size) {
        this.totalSize = total_size;
    }

    public String getRecordingCount() {
        return recordingCount;
    }

    public void setRecordingCount(String recording_count) {
        this.recordingCount = recording_count;
    }

    public List<Recording> getRecordingFiles() {
        return recordingFiles;
    }

    public void setRecordingFiles(List<Recording> recording_files) {
        this.recordingFiles = recording_files;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(uuid, meeting.uuid) &&
                Objects.equals(id, meeting.id) &&
                Objects.equals(accountId, meeting.accountId) &&
                Objects.equals(hostId, meeting.hostId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, id, accountId, hostId, topic, startTime, duration, totalSize, recordingCount, recordingFiles);
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "uuid='" + uuid + '\'' +
                ", id='" + id + '\'' +
                ", accountId='" + accountId + '\'' +
                ", hostId='" + hostId + '\'' +
                ", topic='" + topic + '\'' +
                ", startTime='" + startTime + '\'' +
                ", duration=" + duration +
                ", totalSize='" + totalSize + '\'' +
                ", recordingCount='" + recordingCount + '\'' +
                ", recordingFiles=" + recordingFiles +
                '}';
    }
}
