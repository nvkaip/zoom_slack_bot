package com.nvkaip.zoom_slack_bot.entity;

import java.util.Objects;

public class Feature {

    private int meeting_capacity;
    private boolean large_meeting;

    public Feature() {
    }

    public int getMeeting_capacity() {
        return meeting_capacity;
    }

    public void setMeeting_capacity(int meeting_capacity) {
        this.meeting_capacity = meeting_capacity;
    }

    public boolean isLarge_meeting() {
        return large_meeting;
    }

    public void setLarge_meeting(boolean large_meeting) {
        this.large_meeting = large_meeting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feature feature = (Feature) o;
        return meeting_capacity == feature.meeting_capacity &&
                large_meeting == feature.large_meeting;
    }

    @Override
    public int hashCode() {
        return Objects.hash(meeting_capacity, large_meeting);
    }

    @Override
    public String toString() {
        return "Feature{" +
                "meeting_capacity=" + meeting_capacity +
                ", large_meeting=" + large_meeting +
                '}';
    }
}
