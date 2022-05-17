package com.example.firststepsintoadulthood2.model;

import java.util.Objects;

public class Bios {

    private String username,  profileDescription;

    public Bios() {

    }

    public Bios(String username, String profileDescription){

        this.username = username;
        this.profileDescription = profileDescription;

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bios)) return false;
        Bios bios = (Bios) o;
        return Objects.equals(username, bios.username) && Objects.equals(profileDescription, bios.profileDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, profileDescription);
    }

    @Override
    public String toString() {
        return "Bios{" +
                "username='" + username + '\'' +
                ", profileDescription='" + profileDescription + '\'' +
                '}';
    }
}
