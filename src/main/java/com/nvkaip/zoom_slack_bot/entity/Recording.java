package com.nvkaip.zoom_slack_bot.entity;

import java.util.Objects;

public class Recording {

    private String id; //The recording file ID. Response in general query.
    private String meeting_id; //The meeting ID.
    private String recording_start; //The recording start time.
    private String recording_end; //The recording end time. Response in general query.
    private String file_type; //The recording file type.
    private Number file_size; //The recording file size.
    private String play_url; //The recording file play URL. Response in general query.
    private String download_url; //The recording download URL. Response in general query.
    private String status; //The recording status. Response in general query.
    private String deleted_time; //The recording delete time. Response in trash query.
    private String recording_type; //The recording file type:
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

    public String getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(String meeting_id) {
        this.meeting_id = meeting_id;
    }

    public String getRecording_start() {
        return recording_start;
    }

    public void setRecording_start(String recording_start) {
        this.recording_start = recording_start;
    }

    public String getRecording_end() {
        return recording_end;
    }

    public void setRecording_end(String recording_end) {
        this.recording_end = recording_end;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public Number getFile_size() {
        return file_size;
    }

    public void setFile_size(Number file_size) {
        this.file_size = file_size;
    }

    public String getPlay_url() {
        return play_url;
    }

    public void setPlay_url(String play_url) {
        this.play_url = play_url;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeleted_time() {
        return deleted_time;
    }

    public void setDeleted_time(String deleted_time) {
        this.deleted_time = deleted_time;
    }

    public String getRecording_type() {
        return recording_type;
    }

    public void setRecording_type(String recording_type) {
        this.recording_type = recording_type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recording recording = (Recording) o;
        return Objects.equals(id, recording.id) &&
                Objects.equals(meeting_id, recording.meeting_id) &&
                Objects.equals(recording_start, recording.recording_start) &&
                Objects.equals(recording_end, recording.recording_end) &&
                Objects.equals(file_type, recording.file_type) &&
                Objects.equals(file_size, recording.file_size) &&
                Objects.equals(play_url, recording.play_url) &&
                Objects.equals(download_url, recording.download_url) &&
                Objects.equals(status, recording.status) &&
                Objects.equals(deleted_time, recording.deleted_time) &&
                Objects.equals(recording_type, recording.recording_type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, meeting_id, recording_start, recording_end, file_type, file_size, play_url, download_url, status, deleted_time, recording_type);
    }

    @Override
    public String toString() {
        return "Recording{" +
                "id='" + id + '\'' +
                ", meeting_id='" + meeting_id + '\'' +
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
