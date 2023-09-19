package org.example.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String Name;

    @OneToMany(mappedBy = "team", orphanRemoval = true/*, cascade = CascadeType.PERSIST*/)
    private List<Member> members = new ArrayList<>();

    public Team(String incheon) { }

    public Team() { }

    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public Long getId() {
        return id;
    }

    public List<Member> getChildren() { return members; }
}


