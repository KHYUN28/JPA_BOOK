package jpabook.model;

import jpabook.model.entity.item.Album;
import jpabook.model.entity.item.Book;
import jpabook.model.entity.item.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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

            Book book = new Book();
            book.setAuthor("KKH");
            book.setIsbn("12");

            Album album = new Album();
            album.setArtist("hello");
            album.setEtc("empty");

            Movie movie = new Movie();
            movie.setActor("one");
            movie.setDirector("steven");

            em.persist(book);
            em.persist(album);
            em.persist(movie);

            em.flush();

            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
        emf.close(); //엔티티 매니저 팩토리 종료
    }
}
