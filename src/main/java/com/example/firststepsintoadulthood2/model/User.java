package com.example.firststepsintoadulthood2.model;


public class User {

    private String fullName, birthday, username, password, option, description, profileDescription,imagePath;
    private String reporter, reportDescription;
    private int isAdmin, reportCheck;

    public User() {

    }

    public User(String fullName, String birthday, String username, String password) {

        this.fullName = fullName;
        this.birthday = birthday;
        this.username = username;
        this.password = password;
        this.isAdmin = 0;
    }

    public User(int reportCheck, String username, String reporter, String option, String reportDescription){
        this.reportCheck = reportCheck;
        this.username = username;
        this.reporter = reporter;
        this.option = option;
        this.reportDescription = reportDescription;
        this.isAdmin = 0;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFullName() {

        return fullName;

    }

    public void setFullName(String fullName) {

        this.fullName = fullName;

    }

    public String getBirthday() {

        return birthday;

    }

    public void setBirthday(String birthday) {

        this.birthday = birthday;

    }

    public String getUsername() {

        return username;

    }

    public void setUsername(String username) {

        this.username = username;

    }

    public String getPassword() {

        return password;

    }

    public void setPassword(String password) {

        this.password = password;

    }

    public void setProfileDescription(String newDescription){

        this.profileDescription = newDescription;

    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!fullName.equals(user.fullName)) return false;
        if (!username.equals(user.username)) return false;
        if (!password.equals(user.password)) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;

    }


    @Override
    public String toString() {

        return "UserDTO{" +
                "fullName='" + fullName + '\'' +
                "birthday='" + birthday + '\'' +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';

    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }

    public int getReportCheck() {
        return reportCheck;
    }

    public void setReportCheck(int reportCheck) {
        this.reportCheck = reportCheck;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }
}
