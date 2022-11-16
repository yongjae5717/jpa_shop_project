package jpabook.jpashop.web.memberApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jpabook.jpashop.web.memberApi.dto.*;
import jpabook.jpashop.common.response.Result;
import jpabook.jpashop.domain.entity.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "MemberApi")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberApiController {
    private final MemberService memberService;

    @Operation(description = "회원가입 v1")
    @PostMapping("/v1/members")
    public ResponseEntity<CreateMemberResponse> saveMemberV1(@RequestBody @Valid Member member){
        Long id = memberService.join(member);
        return ResponseEntity.ok(new CreateMemberResponse(id));
    }

    @Operation(description = "회원가입 v2")
    @PostMapping("/v2/members")
    public ResponseEntity<CreateMemberResponse> saveMemberV2(@RequestBody @Valid CreateMemberRequest request){
        Member member = new Member();
        member.MemberSetName(request.getName());
        Long id = memberService.join(member);

        return ResponseEntity.ok(new CreateMemberResponse(id));
    }

    @Operation(description = "회원수정")
    @PutMapping("/members/{id}")
    public ResponseEntity<UpdateMemberResponse> updateMember(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest request){

        memberService.update(id, request.getName());
        Member findMember = memberService.findOne(id);

        return ResponseEntity.ok(new UpdateMemberResponse(findMember.getId(), findMember.getName()));
    }

    @Operation(description = "회원조회 v1")
    @GetMapping("/v1/members")
    public ResponseEntity<List<Member>> memberV1(){
        return ResponseEntity.ok(memberService.findMembers());
    }

    @Operation(description = "회원조회 v2")
    @GetMapping("/v2/members")
    public ResponseEntity<Result> memberV2(){
        List<Member> findMembers = memberService.findMembers();
        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getName(), m.getAddress()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(new Result(collect.size(), collect));
    }
}