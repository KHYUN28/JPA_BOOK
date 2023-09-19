package org.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    @Id @Column(name = "PRODUCT_ID")

    private Long id;
    private String username;

    @ManyToMany(mappedBy = "products")
    private List<Member> members = new ArrayList<>();

    public Product(String username) {
        this.username = username;
    }

    public Product(Long id) {
        this.id = id;
    }

    public Product() { }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public void setName(String username) {
    }

    public void setId(String productA) {
    }
}