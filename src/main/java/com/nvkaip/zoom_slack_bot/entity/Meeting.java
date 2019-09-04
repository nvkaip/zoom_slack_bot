package com.nvkaip.zoom_slack_bot.entity;

import java.util.List;
import java.util.Objects;

public class Meeting {

    private String uuid; //Meeting unique ID.
    private String id; //Meeting ID - also known as the meeting number.
    private String account_id; //ID of the user account.
    private String host_id; //ID of the user set as host of meeting.
    private String topic; //Meeting topic.
    private String start_time; //Meeting start time. Format: date-time
    private int duration; //Meeting duration.
    private String total_size; //Total size.
    private String recording_count; //Recording count.
    private List<Recording> recording_files; //List of recording file.

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

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getHost_id() {
        return host_id;
    }

    public void setHost_id(String host_id) {
        this.host_id = host_id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTotal_size() {
        return total_size;
    }

    public void setTotal_size(String total_size) {
        this.total_size = total_size;
    }

    public String getRecording_count() {
        return recording_count;
    }

    public void setRecording_count(String recording_count) {
        this.recording_count = recording_count;
    }

    public List<Recording> getRecording_files() {
        return recording_files;
    }

    public void setRecording_files(List<Recording> recording_files) {
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
                Objects.equals(host_id, meeting.host_id) &&
                Objects.equals(topic, meeting.topic) &&
                Objects.equals(start_time, meeting.start_time) &&
                Objects.equals(duration, meeting.duration) &&
                Objects.equals(total_size, meeting.total_size) &&
                Objects.equals(recording_count, meeting.recording_count) &&
                Objects.equals(recording_files, meeting.recording_files);
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
