package com.cloudintroteam.member.controller;

import com.cloudintroteam.member.dto.request.SaveMemberRequest;
import com.cloudintroteam.member.dto.response.SaveMemberResponse;
import com.cloudintroteam.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // 팀원 저장
    @PostMapping("/api/members")
    public ResponseEntity<SaveMemberResponse> saveMember(@RequestBody SaveMemberRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.save(request));
    }
}
