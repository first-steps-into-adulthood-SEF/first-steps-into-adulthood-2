package com.example.firststepsintoadulthood2.model;

public class BannedUser {
    private String username, banMessage;
    private int banPeriod;

    public BannedUser(){

    }

    public BannedUser(String username, String banMessage, int banPeriod){
        this.username = username;
        this.banMessage = banMessage;
        this.banPeriod = banPeriod;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBanMessage() {
        return banMessage;
    }

    public void setBanMessage(String banMessage) {
        this.banMessage = banMessage;
    }

    public int getBanPeriod() {
        return banPeriod;
    }

    public void setBanPeriod(int banPeriod) {
        this.banPeriod = banPeriod;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BannedUser user = (BannedUser) o;
        if (!username.equals(user.username)) return false;
        if (!banMessage.equals(user.banMessage)) return false;
        if (!(banPeriod ==user.banPeriod)) return false;
        return true;
    }
}
