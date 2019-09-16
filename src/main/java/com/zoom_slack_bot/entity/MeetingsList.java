package com.zoom_slack_bot.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class MeetingsList {

    private List<Meeting> meetings;

//    public MeetingsList() {
//        meetings = new ArrayList<>();
//    }

//    public List<Meeting> getMeetings() {
//        return meetings;
//    }
//
//    public void setMeetings(List<Meeting> meetings) {
//        this.meetings = meetings;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        MeetingsList that = (MeetingsList) o;
//        return Objects.equals(meetings, that.meetings);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(meetings);
//    }
//
//    @Override
//    public String toString() {
//        return "MeetingsList{" +
//                "meetings=" + meetings +
//                '}';
//    }
}
