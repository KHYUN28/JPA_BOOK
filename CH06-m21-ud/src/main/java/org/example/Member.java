package org.example;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private String id;
    private String username;

//    @ManyToMany
//    @JoinTable(
//            name = "MEMBER_PRODUCT",
//            joinColumns = @JoinColumn(name = "MEMBER"),
//            inverseJoinColumns = @JoinColumn(name = "PRODUCT")
//    )
    @ManyToMany(mappedBy = "members")
    private List<MemberProduct> memberProducts = new ArrayList<>();

//    private List<Product> products = new ArrayList<>();


    //    @OneToOne
//    @JoinColumn(name = "LOCKER_ID")
//    private Locker locker;
//    @ManyToOne
//    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
//    private Team team;

//        @ManyToOne
//    @JoinColumn(name = "TEAM_ID")
//    private Team team;

    public Member() { }

    public Member(String username) { this.username = username; }

//    public void setTeam(Team team) { this.team = team; }

    public String getId() { return id; }

    public String getUsername() { return username; }

//    public Team getTeam() { return team; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setusername(String username) {
    }

    public Member(List<Product> products) {
        this.products = products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    private class MemberProduct {
    }

//    public Team getTeam() {
//        return team;
//    }
}