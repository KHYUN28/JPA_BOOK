package org.example;

import org.example.entity.Member;
import org.example.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.util.DoubleSummaryStatistics;

//Transection
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
//
//            em.flush(); // query 내보내기
//            Long memberId = member.getId();
//
//            tx.commit();
//
//            em.clear();
//            em.close();
//            emf.close();
//
//            printUserAndTeam(member.getId());
////            findUser(em, member.getId());
//            Long memberId = member.getId();
//            testReference(em, memberId);
//            isLoad(em, member);
            Long TeamId = team.getId();
//            teamReference(em,TeamId);
//            saveNoCascade(em);
            orPhanRemoval(em, TeamId);

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

    public static void testReference(EntityManager em, Long id){

        Member member = em.getReference(Member.class, id);

        String name = member.getUsername();

        Team team = member.getTeam();

        String teamName = team.getName();
    }

//    public static void teamReference(EntityManager em, Long id){
//        Team team = em.getReference(Team.class, id);
//        team.getId(); // 8.1.2 프록시와 식별자
//    }

    public static void isLoad(EntityManager em, Object entity) {

        boolean isLoaded = em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(entity);

        System.out.println("-----------------------isLoaded = " + isLoaded);
    }

    public static void saveNoCascade(EntityManager em){

        Team team = new Team();
        em.persist(team);

        Member member1 = new Member();
        member1.setTeam(member1.getTeam());
        team.getChildren().add(member1);
        em.persist(member1);

        Member member2 = new Member();
        member2.setTeam(member2.getTeam());
        team.getChildren().add(member2);
        em.persist(member2);
    }

    private static void saveWithCascade(EntityManager em){

        Member member1 = new Member();
        Member member2 = new Member();

        Team team = new Team();
        member1.setParent(team);
        member2.setParent(team);

        team.getChildren().add(member1);
        team.getChildren().add(member2);

        em.persist(team);
    }

    public static void orPhanRemoval(EntityManager em, Long id) {

        Team team1 = em.find(Team.class, id);
        team1.getChildren().remove(0);
    }
}

/*
*  Left Outer Join (왼쪽 외부 조인): 왼쪽 테이블의 모든 레코드를 포함하며,
* 오른쪽 테이블의 조건을 충족하는 경우 오른쪽 테이블의 해당 레코드를 포함합니다.
* 오른쪽 테이블의 조건을 충족하지 못하는 경우 NULL 값으로 채웁니다.
* LEFT : MEMBER / RIGHT : TEAM
*/