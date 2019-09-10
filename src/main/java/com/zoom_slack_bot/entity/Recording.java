package com.zoom_slack_bot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Recording {

    private String id; // The recording file ID. Response in general query.

    @JsonProperty("meeting_id")
    private String meetingId; // The meeting ID.

    @JsonProperty("recording_start")
    private String recordingStart; // The recording start time.

    @JsonProperty("recording_end")
    private String recordingEnd; // The recording end time. Response in general query.

    @JsonProperty("file_type")
    private String fileType; // The recording file type.

    @JsonProperty("file_size")
    private Number fileSize; // The recording file size.

    @JsonProperty("play_url")
    private String playUrl; // The recording file play URL. Response in general query.

    @JsonProperty("download_url")
    private String downloadUrl; // The recording download URL. Response in general query.

    private String status; // The recording status. Response in general query.

    @JsonProperty("deleted_time")
    private String deletedTime; // The recording delete time. Response in trash query.

    @JsonProperty("recording_type")
    private String recordingType; // The recording file type:
                                    // `shared_screen_with_speaker_view(CC)`
                                    // `shared_screen_with_speaker_view`
                                    // `shared_screen_with_gallery_view`
                                    // `speaker_view`
                                    // `gallery_view`
                                    // `shared_screen`
                                    // `audio_only`
                                    // `audio_transcript`
                                    // `chat_file`
                                    // `TIMELINE`


    public Recording() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meeting_id) {
        this.meetingId = meeting_id;
    }

    public String getRecordingStart() {
        return recordingStart;
    }

    public void setRecordingStart(String recording_start) {
        this.recordingStart = recording_start;
    }

    public String getRecordingEnd() {
        return recordingEnd;
    }

    public void setRecordingEnd(String recording_end) {
        this.recordingEnd = recording_end;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String file_type) {
        this.fileType = file_type;
    }

    public Number getFileSize() {
        return fileSize;
    }

    public void setFileSize(Number file_size) {
        this.fileSize = file_size;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String play_url) {
        this.playUrl = play_url;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String download_url) {
        this.downloadUrl = download_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeletedTime() {
        return deletedTime;
    }

    public void setDeletedTime(String deleted_time) {
        this.deletedTime = deleted_time;
    }

    public String getRecordingType() {
        return recordingType;
    }

    public void setRecordingType(String recording_type) {
        this.recordingType = recording_type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recording recording = (Recording) o;
        return Objects.equals(id, recording.id) &&
                Objects.equals(meetingId, recording.meetingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, meetingId, recordingStart, recordingEnd, fileType,
                fileSize, playUrl, downloadUrl, status, deletedTime, recordingType);
    }

    @Override
    public String toString() {
        return "Recording{" +
                "id='" + id + '\'' +
                ", meetingId='" + meetingId + '\'' +
                ", recordingStart='" + recordingStart + '\'' +
                ", recordingEnd='" + recordingEnd + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileSize=" + fileSize +
                ", playUrl='" + playUrl + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", status='" + status + '\'' +
                ", deletedTime='" + deletedTime + '\'' +
                ", recordingType='" + recordingType + '\'' +
                '}';
    }
}
