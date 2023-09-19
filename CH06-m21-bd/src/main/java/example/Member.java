package example;

import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String username;

//    @JoinColumn(name = "TEAM_ID")
//    private Team team;

    public Member() { }

    public Member(String username) { this.username = username; }

//    public void setTeam(Team team) { this.team = team; }


    public Long getId() { return id; }

    public String getUsername() { return username; }

//    public Team getTeam() { return team; }
}