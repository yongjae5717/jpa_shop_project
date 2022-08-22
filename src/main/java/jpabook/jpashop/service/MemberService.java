package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//읽기전용이라 최적화하기 좋다.
@Transactional(readOnly = true)
//final이 있는 경우에 생성자 인젝션을 자동으로 생성해줌 (lombok)
@RequiredArgsConstructor
public class MemberService {

    /*
    //Autowired를 통하여 스프링빈에 있는 것을 필드 인젝션을 해준다. (단점: 변경을 하지 못한다)
    @Autowired
    private MemberRepository memberRepository;

     */

    /*
    //생성자 Injection (변경할 일이 없기때문에 final로 지정)
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
     */

    private final MemberRepository memberRepository;


    @Transactional //default: read only false
    //회원 가입
    public Long join(Member member){
        //중복회원 검증
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }


    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //회원 한건 조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }


}
