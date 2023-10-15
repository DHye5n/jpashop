package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository // 저장소
@RequiredArgsConstructor // 생성자 주입
public class MemberRepository {

    @PersistenceContext //영속성
    private EntityManager em;

    //회원가입
    public void save(Member member) { //저장
        em.persist(member);
    }

    //회원 조회
    public Member findOne(Long id) { //하나만 찾기
        return em.find(Member.class, id);
    }

    //회원 전체 조회
    public List<Member> findAll() { //전체 찾기
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    //회원 이름으로 찾기
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

    //@Repository : 스프링 Bean으로 등록. JPA예외를 스프링 기반 예외로 변환
    //@PersistenceContext : 엔티티매니저 주입
    //@PersistenceUnit : 엔티티매니저팩토리를 주입


    // 다음에 만들어야 할 것
    // 회원 서비스 개발
    // 상품 도메인 개발
    // 상품 엔티티 개발(비지니스 로직 추가)
    // 상품 레포지토리 개발
    // 주문 도메인 개발
}
