package jpabook.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "Team")
    private Long id;
    private String name;

    public Team(String team1, String 팀1) {
    }

    public Team() {

    }

    //Getter, Setter
    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}