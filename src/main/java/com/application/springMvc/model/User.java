package com.application.springMvc.model;

/**
 * Model
 * @author Ihor Savchenko
 * @version 1.0
 */
public class User {

    private String login;
    private String password;
    private int level;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", level=" + level +
                '}';
    }
}
