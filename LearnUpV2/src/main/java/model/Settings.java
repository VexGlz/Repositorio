package model;

public class Settings {
    private Boolean notifications; 
    private Boolean darkMode;    
    private String language;

    public Settings() {}
    public Settings(Boolean notifications, Boolean darkMode, String language) {
        this.notifications = notifications;
        this.darkMode = darkMode;
        this.language = language;
    }

    public Boolean getNotifications() { return notifications; }
    public void setNotifications(Boolean notifications) { this.notifications = notifications; }
    public Boolean getDarkMode() { return darkMode; }
    public void setDarkMode(Boolean darkMode) { this.darkMode = darkMode; }
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
}