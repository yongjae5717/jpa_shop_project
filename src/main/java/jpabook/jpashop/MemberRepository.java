package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {
    @PersistenceContext
    private EntityManager em;

    // command + shift + t를 이용해서 테스트 코드 작성

    //저장하는 코드
    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    //member하나 조회
    public Member find(Long id){
        return em.find(Member.class, id);
    }

}
