package org.example.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "findByUsername", query = "SELECT m FROM Member m"),
        @NamedQuery(name = "findByUsernameId", query = "SELECT m FROM Member m WHERE m.team.name = :name")
})
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "name")
    private String username;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY) //LAZYfetch 쓰는 JOIN을 안함 //Default = EagerFetch
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToMany
    @JoinColumn(name = "ORDER_ID")
    private List<Order> orders = new ArrayList<>();
//    private List<Order> orders;

    public Member() { }

    public void setUsername(String username) {
        this.username = username;
    }

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
//        this.team.setmember.add(this);
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}