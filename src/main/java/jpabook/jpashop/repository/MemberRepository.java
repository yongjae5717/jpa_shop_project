package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

//스프링 빈으로 스프링이 등록을 해준다.
@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    /*
    //JPA 엔티티메니저를 주입할 수 있도록하는 어노테이션
    @PersistenceContext
    private EntityManager em;
     */

    // 멤버 저장
    public void save(Member member){
        em.persist(member);
    }

    // 멤버 찾기
    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    //전체 멤버 리스트 반환
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    //이름에 의한 회원 조회기능
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
