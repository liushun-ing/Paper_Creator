package com.shun.pojo;

public class Teacher {
    private final String username;
    private final String password;
    private String type;

    public Teacher(String username, String password, String type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Teacher{"
                + "username='"
                + username
                + '\''
                + ", password='"
                + password
                + '\''
                + ", type="
                + type
                + '}';
    }
}
