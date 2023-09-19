package org.example;

import javax.persistence.*;

@Entity
public class Locker {

    @Id
    @GeneratedValue
    @Column(name = "LOCKER_ID")
    private Long id;
    private String username;

    //bidirection
    @OneToOne//(mappedBy = "locker")
    private Member member;

    public void setMember(Member member) { this.member = member; }

    public void setUsername(String username) { this.username = username; }

    public void setId(Long id) { this.id = id; }

    public Long getId() { return id; }

    public String getUsername() { return username; }

    public Member getMember() { return member; }
}
