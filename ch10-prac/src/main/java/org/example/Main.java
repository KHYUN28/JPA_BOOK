package org.example;

import org.example.entity.*;
import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.Iterator;
import java.util.List;

//Transection
public class Main {
    public static void main(String[] args) {
        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {

            tx.begin(); //트랜잭션 시작

//            Team team = new Team("incheon");
//            em.persist(team);
//
//            Member member = new Member("KKH");
//            member.setTeam(team);
//            em.persist(member);

//            member.setAge(40);
            em.flush(); // query 내보내기
//            Long memberId = member.getId();

//            tx.commit();

//            em.clear();
//            em.close();
//            emf.close();

//            printUserAndTeam(member.getId());
//            findUser(em, member.getId());
//            Query(em);
//            Param_binding(em);
//            Param_binding(em, member);
//            ManyProjection(em);
//            projection(em);
//            testNewDto(em);
//            testPagingAPI(em);
//            jpqlJoin10_25(em);
            collectionJoin(em);
//            testfetchjoin(em);
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
        emf.close(); //엔티티 매니저 팩토리 종료
    }

    public static void printUserAndTeam(Long id){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        tx.begin();

        Member member = em.find(Member.class, id);
        Team team = member.getTeam(); // Team = Proxy

        System.out.println("회원 이름 : " + member.getUsername()); // 이때 팀 query 가져옴
        System.out.println("팀 : " + team.getName());

        tx.commit();
    }

    public static void findUser(EntityManager em, Long id){

        Member member = em.find(Member.class, id);
        System.out.println("회원 이름 : " + member.getUsername());
    }


    //JPQL

//    public static void projection(EntityManager em){
//        Team team = new Team();
//        team.setName("incheon");
//        em.persist(team);
//
//        Member member1 = new Member();
//        member1.setUsername("1st");
//        member1.setTeam(team);
//        em.persist(member1);
//
//        Member member2 = new Member();
//        member2.setUsername("2nd");
//        member2.setTeam(team);
//        em.persist(member2);
//
//        Member member3 = new Member();
//        member3.setUsername("3rd");
//        member3.setTeam(team);
//        em.persist(member3);
//
//        em.flush();
//        em.clear();
//
//        TypedQuery<Team> query = em.createQuery("SELECT m.team FROM Member m", Team.class);
//        List<Team> teams = query.getResultList();
//
//        for (Team t : teams){
//            System.out.println("----------------------------------------------");
//            System.out.println("team name = " + t.getName());
//            for (Member m : t.getMembers()){
//                System.out.println("member name = " + m.getUsername());
//            }
//        }
//    }
    public static void TypeQuery (EntityManager em){
        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m", Member.class);
        List<Member> resultList = query.getResultList();
        for (Member member : resultList){
            System.out.println("member = " + member);
        }
    }

    public static void Query(EntityManager em){
        Query query = em.createQuery("SELECT m.username, m.age from Member m");
        List resultList = query.getResultList();

        for (Object o : resultList){
            Object[] result = (Object[]) o;
            System.out.println("username = " + result[0]);
            System.out.println("age = " + result[1]);
        }
    }

    public static void Param_binding(EntityManager em, Member member){
        String userNameParam = "1st";

        List<Member> members =
                em.createQuery("SELECT m FROM Member m where m.username = :username", Member.class)
                .setParameter("username", userNameParam)
                        .getResultList();

        for (Member m : members){
            System.out.println("================================================");
            System.out.println("name = " + m.getUsername());
        }
    }

//    public static void getProjectResult(EntityManager em){
//        TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
//
//        TypedQuery<Team> query1 = em.
//    }

    public static void ManyProjection (EntityManager em){
        Query query = em.createQuery("SELECT m.username, m.age FROM Member m");
        List resultList = query.getResultList();

        Iterator iterator = resultList.iterator();
        while (iterator.hasNext()){
            Object[] row = (Object[]) iterator.next();
            String username = (String) row[0];
            Integer age = (Integer) row[1];
        }
    }

//    public static void orders(EntityManager em){
//
//        Product product1 = new Product();
//        product1.setName("음료1");
//        product1.setPrice(1000);
//        product1.setStockAmount(100);
//        em.persist(product1);
//
//        Product product2 = new Product();
//        product2.setName("음료2");
//        product2.setPrice(2000);
//        product2.setStockAmount(200);
//        em.persist(product2);
//
//        Product product3 = new Product();
//        product3.setName("음료3");
//        product3.setPrice(2000);
//        product3.setStockAmount(300);
//        em.persist(product3);
//
//        Product product4 = new Product();
//        product4.setName("음료4");
//        product4.setPrice(3000);
//        product4.setStockAmount(400);
//        em.persist(product4);
//
//        Member member1 = em.createQuery("select m from Member m where m.username = :name", Member.class)
//                .setParameter("name", "1st").getSingleResult();
//
//        System.out.println("result = " + member1.getUsername());
//
//        Member member2 = em.createQuery("select m from Member m where m.username = :name", Member.class)
//                .setParameter("name", "2nd").getSingleResult();
//
//        System.out.println("result = " + member2.getUsername());
//
//        Address address = new Address();
//        address.setStreet("haeundae");
//        address.setCity("busan");
//
//        Order order1 = new Order();
//        order1.setOrderDate(LocalDateTime.now());
//        order1.setMember(member1);
//        order1.setAddress(address);
//    }
    public static void testNewDto(EntityManager em){
        TypedQuery<UserDTO> query = em.createQuery("select new org.example.entity.UserDTO(m.username, m.age) from Member m", UserDTO.class);

        List<UserDTO> resultList = query.getResultList();
        for (UserDTO d : resultList) {

            System.out.println("name = " + d.getUsername());
            System.out.println("age = " + d.getAge());
        }
    }

    public static void testPagingAPI(EntityManager em){
        for (int i = 0; i <100; i++){
            Member member = new Member();
            member.setAge(20 + i);
            member.setUsername("member" + i);
            em.persist(member);
        }

        TypedQuery<Member> query = em.createQuery("select m from Member m ORDER By m.username DESC", Member.class);

        query.setFirstResult(10);
        query.setMaxResults(20);
        List<Member> members = query.getResultList();

        for (Member m : members){
            System.out.println("member = " + m.getId());
        }
    }

    public static void jpqlJoin10_25(EntityManager em){
        Team team1 = new Team();
        team1.setName("incheon1");
        em.persist(team1);

        Team team2 = new Team();
        team2.setName("incheon2");
        em.persist(team2);

        Member member1 = new Member();
        member1.setUsername("1st");
        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member();
        member2.setUsername("2nd");
        member2.setTeam(team1);
        em.persist(member2);

        Member member3 = new Member();
        member3.setUsername("3rd");
        member3.setTeam(team2);
        em.persist(member3);

        em.flush();
        em.clear();

        Query query = em.createQuery("SELECT m, t FROM Member m JOIN m.team t");
        List<Object[]> objects = query.getResultList();
        for (Object[] object : objects){
            Member member = (Member) object[0];
            Team team = (Team) object[1];
            System.out.println("member name = " + member.getUsername() + "," + "team name = " + team.getName() );
        }
    }

    public static void collectionJoin(EntityManager em){
        Team team1 = new Team();
        team1.setName("incheon1");
        em.persist(team1);

        Team team2 = new Team();
        team2.setName("incheon2");
        em.persist(team2);

        Member member1 = new Member();
        member1.setUsername("1st");
        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member();
        member2.setUsername("2nd");
        member2.setTeam(team1);
        em.persist(member2);

        Member member3 = new Member();
        member3.setUsername("3rd");
        member3.setTeam(team2);
        em.persist(member3);

        em.flush();
        em.clear();

//        Query query = em.createQuery("SELECT t, m FROM Team t JOIN t.member m", Member.class);
        Query query = em.createQuery("SELECT m, t FROM Member m JOIN m.team t");
        List<Object[]> objects = query.getResultList();
        for (Object[] object : objects){
            Member member = (Member) object[0];
            Team team = (Team) object[1];
            System.out.println("member name = " + member.getUsername() + "," + "team name = " + team.getName() );
        }
    }

    public static void testfetchjoin(EntityManager em) {
        Team team1 = new Team();
        team1.setName("incheon1");
        em.persist(team1);

        Team team2 = new Team();
        team2.setName("incheon2");
        em.persist(team2);

        Member member1 = new Member();
        member1.setUsername("1st");
        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member();
        member2.setUsername("2nd");
        member2.setTeam(team1);
        em.persist(member2);

        Member member3 = new Member();
        member3.setUsername("3rd");
        member3.setTeam(team2);
        em.persist(member3);

        em.flush();
        em.clear();

//        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m join fetch m.team", Member.class);
//        List<Member> members = query.getResultList();

//        for (Member m : members){
//            Team team = m.getTeam();
//            System.out.println("memberName = " + m.getUsername() +
//                    ", " + "teamId = " + team.getId() +
//                    ", " + "teamName = " + m.getTeam().getName());
//        }

        //--------------------
//        List<Team> teams = em.createQuery("SELECT t FROM Team t join fetch t.members where t.name = :name",Team.class)
//                .setParameter("name", "incheon")
//                .getResultList();

        // OneToMany에서 중복된 엔티티가 발생한다.
        List<Team> teams = em.createQuery("SELECT DISTINCT t FROM Team t join fetch t.members where t.name = :name", Team.class)
                .setParameter("name", "incheon")
                .getResultList();

        // for 문 실행안됨.
        for (Team t : teams){
            System.out.println("===========================================================");
            System.out.println("teamName = " + t.getName());
            for (Member m : t.getMembers())
                System.out.println("memberName = " + m.getUsername() + ", " + "teamId = " + m.getId() +
                    ", " + "teamName = " + m.getUsername());
        }
    }

//    public static void order (EntityManager em) {
//
//
//
//        Order order1 = new Order();
//        order1.setMember(member1);
//        order1.setAddress(address);
//        order1.setProduct(product4);
//        order1.setOrderAmount(10000L);
//        em.persist(order1);
//
//        Order order2 = new Order();
//        order2.setMember(member2);
//        order2.setAddress(address);
//        order2.setProduct(product3);
//        order2.setOrderAmount(20000L);
//        em.persist(order2);
//
//        em.flush();
//        em.clear();
//
//        List<Address> address = em.createQuery("SELECT o.address from Order o", Address.class)
//                .getResultList();
//
//        for (Address a : address) {
//            System.out.println("city : " + a.getCity());
//            System.out.println("street : " + a.getStreet());
//            System.out.println("zipcode : " + a.getZipcode());
//        }
//    }
}

/*
*  Left Outer Join (왼쪽 외부 조인): 왼쪽 테이블의 모든 레코드를 포함하며,
* 오른쪽 테이블의 조건을 충족하는 경우 오른쪽 테이블의 해당 레코드를 포함합니다.
* 오른쪽 테이블의 조건을 충족하지 못하는 경우 NULL 값으로 채웁니다.
* LEFT : MEMBER / RIGHT : TEAM
*/