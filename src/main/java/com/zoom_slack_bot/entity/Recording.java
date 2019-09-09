package com.zoom_slack_bot.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Recording {

    private String id; // The recording file ID. Response in general query.

    @SerializedName("meetingId")
    private String meetingId; // The meeting ID.
    private String recording_start; // The recording start time.
    private String recording_end; // The recording end time. Response in general query.
    private String file_type; // The recording file type.
    private Number file_size; // The recording file size.
    private String play_url; // The recording file play URL. Response in general query.
    private String download_url; // The recording download URL. Response in general query.
    private String status; // The recording status. Response in general query.
    private String deleted_time; // The recording delete time. Response in trash query.
    private String recording_type; // The recording file type:
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
        return recording_start;
    }

    public void setRecordingStart(String recording_start) {
        this.recording_start = recording_start;
    }

    public String getRecordingEnd() {
        return recording_end;
    }

    public void setRecordingEnd(String recording_end) {
        this.recording_end = recording_end;
    }

    public String getFileType() {
        return file_type;
    }

    public void setFileType(String file_type) {
        this.file_type = file_type;
    }

    public Number getFileSize() {
        return file_size;
    }

    public void setFileSize(Number file_size) {
        this.file_size = file_size;
    }

    public String getPlayUrl() {
        return play_url;
    }

    public void setPlayUrl(String play_url) {
        this.play_url = play_url;
    }

    public String getDownloadUrl() {
        return download_url;
    }

    public void setDownloadUrl(String download_url) {
        this.download_url = download_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeletedTime() {
        return deleted_time;
    }

    public void setDeletedTime(String deleted_time) {
        this.deleted_time = deleted_time;
    }

    public String getRecordingType() {
        return recording_type;
    }

    public void setRecordingType(String recording_type) {
        this.recording_type = recording_type;
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
        return Objects.hash(id, meetingId, recording_start, recording_end, file_type,
                file_size, play_url, download_url, status, deleted_time, recording_type);
    }

    @Override
    public String toString() {
        return "Recording{" +
                "id='" + id + '\'' +
                ", meetingId='" + meetingId + '\'' +
                ", recording_start='" + recording_start + '\'' +
                ", recording_end='" + recording_end + '\'' +
                ", file_type='" + file_type + '\'' +
                ", file_size=" + file_size +
                ", play_url='" + play_url + '\'' +
                ", download_url='" + download_url + '\'' +
                ", status='" + status + '\'' +
                ", deleted_time='" + deleted_time + '\'' +
                ", recording_type='" + recording_type + '\'' +
                '}';
    }
}
