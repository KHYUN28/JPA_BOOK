package org.example.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by holyeye on 2014. 3. 11..
 */
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID") // member_id는 회원과의 관계를 나타내는 외래 키 컬럼 이름입니다.
    private Member member;      //주문 회원

//    @ManyToOne//(mappedBy = "order") // , cascade = CascadeType.ALL /  mappedBy는 OrderItem 엔티티에서의 필드 이름을 나타냅니다.
//    @JoinColumn(name = "order_id")
//    private List<Order> Items = new ArrayList<>();

    private Date orderDate;     //주문시간


    //==Getter, Setter==//
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

}
