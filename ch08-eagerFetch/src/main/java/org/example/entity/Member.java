package org.example.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    private String username;

    @ManyToOne //Default = EagerFetch
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Member() { }

    public Member(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setTeam(Team team) {
        this.team = team;
    }


}