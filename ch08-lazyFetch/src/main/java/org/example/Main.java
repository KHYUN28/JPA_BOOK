package org.example;

import org.example.entity.Member;
import org.example.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.DoubleSummaryStatistics;

//EAGERFETCH
public class Main {
    public static void main(String[] args) {
        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {

            tx.begin(); //트랜잭션 시작

            Team team = new Team("incheon");
            em.persist(team);

            Member member = new Member("KKH");
            member.setTeam(team);
            em.persist(member);

//            Hibernate:
//            call next value for hibernate_sequence
//            Hibernate:
//            call next value for hibernate_sequence

            em.flush(); // query 내보내기

            Long memberId = member.getId();

            em.clear();

            printUserAndTeam(em, member.getId());
//            findUser(em, member.getId());
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
        emf.close(); //엔티티 매니저 팩토리 종료
    }

    public static void printUserAndTeam(EntityManager em, Long id){

        Member member = em.find(Member.class, id);
        Team team = member.getTeam(); // Team = Proxy
        System.out.println("회원 이름 : " + member.getUsername()); // 이때 팀 query 가져옴
        System.out.println("팀 : " +team.getUsername());
    }

    public static void findUser(EntityManager em, Long id){

        Member member = em.find(Member.class, id);
        System.out.println("회원 이름 : " + member.getUsername());
    }
}

/*
*  Left Outer Join (왼쪽 외부 조인): 왼쪽 테이블의 모든 레코드를 포함하며,
* 오른쪽 테이블의 조건을 충족하는 경우 오른쪽 테이블의 해당 레코드를 포함합니다.
* 오른쪽 테이블의 조건을 충족하지 못하는 경우 NULL 값으로 채웁니다.
* LEFT : MEMBER / RIGHT : TEAM
*/