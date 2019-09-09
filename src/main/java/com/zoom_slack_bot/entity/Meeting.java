package com.zoom_slack_bot.entity;

import java.util.List;
import java.util.Objects;

public class Meeting {

    private String uuid; // Meeting unique ID.
    private String id; // Meeting ID - also known as the meeting number.
    private String account_id; // ID of the user account.
    private String host_id; // ID of the user set as host of meeting.
    private String topic; // Meeting topic.
    private String start_time; // Meeting start time. Format: date-time
    private int duration; // Meeting duration.
    private String total_size; // Total size.
    private String recording_count; // Recording count.
    private List<Recording> recording_files; // List of recording file.

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
        return account_id;
    }

    public void setAccountId(String account_id) {
        this.account_id = account_id;
    }

    public String getHostId() {
        return host_id;
    }

    public void setHostId(String host_id) {
        this.host_id = host_id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getStartTime() {
        return start_time;
    }

    public void setStartTime(String start_time) {
        this.start_time = start_time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTotalSize() {
        return total_size;
    }

    public void setTotalSize(String total_size) {
        this.total_size = total_size;
    }

    public String getRecordingCount() {
        return recording_count;
    }

    public void setRecordingCount(String recording_count) {
        this.recording_count = recording_count;
    }

    public List<Recording> getRecordingFiles() {
        return recording_files;
    }

    public void setRecordingFiles(List<Recording> recording_files) {
        this.recording_files = recording_files;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(uuid, meeting.uuid) &&
                Objects.equals(id, meeting.id) &&
                Objects.equals(account_id, meeting.account_id) &&
                Objects.equals(host_id, meeting.host_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, id, account_id, host_id, topic, start_time, duration, total_size, recording_count, recording_files);
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "uuid='" + uuid + '\'' +
                ", id='" + id + '\'' +
                ", account_id='" + account_id + '\'' +
                ", host_id='" + host_id + '\'' +
                ", topic='" + topic + '\'' +
                ", start_time='" + start_time + '\'' +
                ", duration=" + duration +
                ", total_size='" + total_size + '\'' +
                ", recording_count='" + recording_count + '\'' +
                ", recording_files=" + recording_files +
                '}';
    }
}
