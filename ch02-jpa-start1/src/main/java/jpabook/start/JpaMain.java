package jpabook.start;

import javax.persistence.*;
import java.util.List;

/**
 * @author holyeye
 */
public class JpaMain {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {

            tx.begin(); //트랜잭션 시작
//            logic(em);  //비즈니스 로직
            logic2(em);
            System.out.println("#############TRY################");
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }
//INSERT INTO  MEMBER (ID,NAME,AGE) VALUES ('id2','원희',40)

    public static void logic2(EntityManager em){
//        Board board = new Board();
//        em.persist(board);
//        System.out.println("^^^^^^logic2^^^^^^");
//
////        Hibernate:
////        call next value for BOARD_SEQ
//        System.out.println("board.id = " + board.getId());
        for(int i =0; i <60; i++){
            Board board = new Board();
            em.persist(board);
            System.out.println("^^^^^^logic2^^^^^^");
            System.out.println("board.id = " + board.getId());
        }
    }
    public static void logic(EntityManager em) {

        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("지한");
        member.setAge(2);

        //등록
        em.persist(member);
        System.out.println("---------------등록----------------");

        //수정
        member.setAge(20);
        System.out.println("---------------수정----------------");

        //한 건 조회
        Member findMember = em.find(Member.class, id); // find : Entity Context에서 찾겠다.
        System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());
        System.out.println("-------------한건조회----------------");

//        String willFilendId = "id2";
//        Member foundMember = em.find(Member.class, willFilendId);
//        //목록 조회
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("members.size=" + members.size());
        System.out.println("---------------목록조회----------------");

        //삭제
//        em.remove(member);

    }
}

// drop table MEMBER
// SELECT * FROM MEMBER