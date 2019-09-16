package com.zoom_slack_bot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
}
