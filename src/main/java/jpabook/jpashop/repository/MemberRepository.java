package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // Rule: select m from m where m.name = ?
    // By 뒤에 Name을 보고 m.name을 가져온다.
    List<Member> findByName(String name);
}
