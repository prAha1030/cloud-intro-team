package com.cloudintroteam.member.service;

import com.cloudintroteam.member.dto.request.SaveMemberRequest;
import com.cloudintroteam.member.dto.response.GetMemberResponse;
import com.cloudintroteam.member.dto.response.SaveMemberResponse;
import com.cloudintroteam.member.entity.Member;
import com.cloudintroteam.member.exception.MemberNotFoundException;
import com.cloudintroteam.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 팀원 저장
    @Transactional
    public SaveMemberResponse save(SaveMemberRequest request) {
        Member member = new Member(
                request.getName(),
                request.getAge(),
                request.getMbti());

        Member savedMember = memberRepository.save(member);
        return new SaveMemberResponse(
                savedMember.getId(),
                savedMember.getName(),
                savedMember.getAge(),
                savedMember.getMbti());
    }

    // 팀원 조회
    @Transactional(readOnly = true)
    public GetMemberResponse get(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException("존재하지 않는 팀원입니다."));

        return new GetMemberResponse(
                member.getId(),
                member.getName(),
                member.getAge(),
                member.getMbti());
    }
}
