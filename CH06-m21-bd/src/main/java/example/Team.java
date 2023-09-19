package example;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String username;

    @OneToMany(mappedBy = "team")
    @JoinColumn(name = "TEAM_ID")
    private List<Member> members = new ArrayList<>();

    public Team(){}

    public Team(String username) { this.username = username; }

    public String getUsername() { return username; }

    public Long getId() { return id; }

}


