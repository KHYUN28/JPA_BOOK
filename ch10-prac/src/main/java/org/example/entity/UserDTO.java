package org.example.entity;

public class UserDTO {

    private String username;

    private int age;

    public UserDTO(String username, int age){
        this.username = username;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getUsername() {
        return username;
    }
}
