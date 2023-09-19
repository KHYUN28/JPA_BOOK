import org.example.Member;
import org.example.Product;
import org.example.Team;
import org.example.Locker;

import javax.persistence.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); // 엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 기능 획득
        try {

            tx.begin(); // 트랜잭션 시작
//            logic(em);
//            Save(em);
            findInverse(em);

            tx.commit();// 트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 트랜잭션 롤백
        } finally {
            em.close(); // 엔티티 매니저 종료
        }
        emf.close(); // 엔티티 매니저 팩토리 종료
    }

    public static void logic(EntityManager em){

        Member member1 = new Member("회원1");
        Member member2 = new Member("회원2");

        em.persist(member1);
        em.persist(member2);

        Team team1 = new Team("팀1");
        em.persist(team1);

//        member1.setTeam(team1);
//        member2.setTeam(team1);
    }

    public static void fetchinManeger(EntityManager em){

        System.out.println("--------------------------------------");
        TypedQuery<Member> query = em.createQuery(
                "SELECT m FROM Member m WHERE m.username = :username",
                Member.class);
        query.setParameter("username", "회원1");
        Member member = query.getSingleResult();

        Member retrievedMember = em.find(Member.class, member.getId());
        System.out.println("Member Name: " + retrievedMember.getUsername());
//        System.out.println("Team Id: " + retrievedMember.getTeam().getId());
//        System.out.println("Member Id: " + retrievedMember.getTeam().getUsername());

    }

    public static void testSave(EntityManager em){
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");

        Team team1 = new Team("team1");

        team1.getMembers().add(member1);
        team1.getMembers().add(member2);

        em.persist(member1);
        em.persist(member2);

        em.persist(team1);

//        transaction.commit();
    }

    //-----------------------------------

    public static void Locker(EntityManager em){

        System.out.println("--------------------------------------");
        TypedQuery<Locker> query = em.createQuery(
                "SELECT m FROM Member m WHERE m.username = :username",
                Locker.class);
        query.setParameter("username", "회원1");
        Locker locker = query.getSingleResult();

        Member retrievedMember = em.find(Member.class, locker.getId());
        System.out.println("Member Name: " + retrievedMember.getUsername());
//        System.out.println("Team Id: " + retrievedMember.().getId());
//        System.out.println("Member Id: " + retrievedMember.().getUsername());

    }


    //-----------------------------------

    public static void Product(EntityManager em){

        System.out.println("--------------------------------------");
        TypedQuery<Member> query = em.createQuery(
                "SELECT m FROM Product m WHERE m.username = :username",
                Member.class);
        query.setParameter("username", "회원1");
        Member member = query.getSingleResult();

        Member retrievedMember = em.find(Member.class, member.getId());
        System.out.println("Member Name: " + retrievedMember.getUsername());
        System.out.println("Team Id: " + retrievedMember.getProducts());
        System.out.println("Member Id: " + retrievedMember.getProducts());

    }

    public static void Save(EntityManager em) {
        Product productA = new Product();
        productA.setId("productA");
        productA.setName("상품A");
        em.persist(productA);

        Member member1 = new Member();
        member1.setId("member1");
        member1.setusername("회원1");
        member1.getProducts().add(productA);
        em.persist(member1);
    }

    public static void Find(EntityManager em){
        Member member = em.find(Member.class, "member1");
        List<Product> products = member.getProducts();
        for (Product product : products){
            System.out.println("product.name = " + product.getUsername());
        }
    }

    public static void findInverse(EntityManager em) {
        Product product = em.find(Product.class, "productA");
        List<Member> members = product.getMembers();
        for (Member member : members){
            System.out.println("memeber = " + member.getUsername());
        }
    }

}