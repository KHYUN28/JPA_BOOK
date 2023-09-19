package jpabook.model;

import jpabook.model.entity.Member;
import jpabook.model.entity.item.Book;
import jpabook.model.entity.item.Item;
import jpabook.model.entity.item.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by 1001218 on 15. 4. 5..
 */
public class Main {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {

            tx.begin(); //트랜잭션 시작
//            testJoined(em);
//            NamedQueries(em);
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }
    public static void testJoined(EntityManager em){

        Book book = new Book();
        book.setName("BookName");
        book.setIsbn("1234");
        book.setAuthor("BookKKH");
        book.setPrice(1000);
        book.setStockQuantity(10);
        em.persist(book);
        em.flush();
        em.clear();

        Movie movie = new Movie();
        movie.setName("hellMovie");
        movie.setActor("kkhMovie");
        movie.setDirector("helloDirector");
        movie.setPrice(2000);
        movie.setStockQuantity(20);
        em.persist(movie);
        em.flush();
        em.clear();


        // 10.2.11 다형성 쿼리 TREAT(JPA 2.1) p.398
        List<Item> items = em.createQuery("select i from Item i where treat(i as Book).author = 'BookKKH'", Item.class)
                .getResultList();

        for (Item i : items) {
            System.out.println("ItemName = " + i.getName());
        }

//        // 10.2.11 다형성 쿼리 TYPE p.398
//        List<Item> items = em.createQuery("select i from Item i where type (i) IN (Book, Movie)", Item.class)
//                .getResultList();
//
//        for (Item i : items) {
//            System.out.println("ItemName = " + i.getName());
//        }

        // 10.2.11 다형성 쿼리 p.397
//        List<Book> books = em.createQuery("select m from Book m", Book.class).getResultList();
//
//        for (Book b : books) {
//            System.out.println("BookName = " + b.getName());
//        }

        // 10.2.11 다형성 쿼리 p.397
//        List<Item> Items = em.createQuery("select i from Item i", Item.class).getResultList();
//
//        for (Item i : Items) {
//            System.out.println("ItemaName = " + i.getName());
//        }

    }

//    public static void NamedQueries(EntityManager em) {
//
//        List<Member> resultList = em.createNamedQuery("Member.findByUsername", Member.class)
//                .setParameter("username", "회원1")
//                .getResultList();
//
//        System.out.println("resultList = " + resultList);
//    }
}
