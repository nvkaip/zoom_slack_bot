package com.nvkaip.zoom_slack_bot.entity;

import java.util.Objects;

public class User {

    private String id;
    private String first_name;
    private String email;
    private Feature feature;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(first_name, user.first_name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(feature, user.feature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, email, feature);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", first_name='" + first_name + '\'' +
                ", email='" + email + '\'' +
                ", feature=" + feature +
                '}';
    }
}
