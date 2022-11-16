package jpabook.jpashop.domain.repository;

import jpabook.jpashop.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // Rule: select m from m where m.name = ?
    // By 뒤에 Name을 보고 m.name을 가져온다.
    List<Member> findByName(String name);
}
