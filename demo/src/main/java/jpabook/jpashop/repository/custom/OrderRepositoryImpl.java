package jpabook.jpashop.repository.custom;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderSearch;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements CustomOrderRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Order> search(OrderSearch orderSearch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> orderRoot = cq.from(Order.class);
        List<Predicate> predicates = new ArrayList<>();

        if (orderSearch.getMemberName() != null && !orderSearch.getMemberName().isEmpty()) {
            Join<Object, Object> memberJoin = orderRoot.join("member", JoinType.INNER);
            predicates.add(cb.like(memberJoin.get("name"), "%" + orderSearch.getMemberName() + "%"));
        }

        if (orderSearch.getOrderStatus() != null) {
            predicates.add(cb.equal(orderRoot.get("status"), orderSearch.getOrderStatus()));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Order> query = em.createQuery(cq);
        return query.getResultList();
    }
}


//package jpabook.jpashop.repository.custom;
//
//import com.mysema.query.jpa.JPQLQuery;
//import jpabook.jpashop.domain.Order;
//import jpabook.jpashop.domain.OrderSearch;
//import jpabook.jpashop.domain.QMember;
//import jpabook.jpashop.domain.QOrder;
//import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
//import org.springframework.util.StringUtils;
//
//import java.util.List;
//
///**
// * @author holyeye
// */
//public class OrderRepositoryImpl extends QueryDslRepositorySupport implements CustomOrderRepository {
//
//    public OrderRepositoryImpl() {
//        super(Order.class);
//    }
//
//    @Override
//    public List<Order> search(OrderSearch orderSearch) {
//
//        QOrder order = QOrder.order;
//        QMember member = QMember.member;
//
//        JPQLQuery query = from(order);
//
//        if (StringUtils.hasText(orderSearch.getMemberName())) {
//            query.leftJoin(order.member, member)
//                    .where(member.name.contains(orderSearch.getMemberName()));
//        }
//
//        if (orderSearch.getOrderStatus() != null) {
//            query.where(order.status.eq(orderSearch.getOrderStatus()));
//        }
//
//        return query.list(order);
//    }
//}
