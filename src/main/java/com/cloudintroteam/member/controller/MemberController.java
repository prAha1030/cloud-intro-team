package com.cloudintroteam.member.controller;

import com.cloudintroteam.member.dto.request.SaveMemberRequest;
import com.cloudintroteam.member.dto.response.GetMemberResponse;
import com.cloudintroteam.member.dto.response.SaveMemberResponse;
import com.cloudintroteam.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    // 팀원 저장
    @PostMapping("/api/members")
    public ResponseEntity<SaveMemberResponse> saveMember(@Valid @RequestBody SaveMemberRequest request) {
        log.info("[API - LOG] 팀원 저장 요청");
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.save(request));
    }

    // 팀원 조회
    @GetMapping("/api/members/{memberId}")
    public ResponseEntity<GetMemberResponse> getMember(@PathVariable Long memberId) {
        log.info("[API - LOG] 팀원 조회 요청");
        return ResponseEntity.status(HttpStatus.OK).body(memberService.get(memberId));
    }

    // 팀원 프로필 사진 저장 및 업로드
    @PostMapping("/api/members/{memberId}/profile-image")
    public ResponseEntity<Void> fileUpload(
            @PathVariable Long memberId,
            @RequestParam("file")MultipartFile file) {
        log.info("[API - LOG] 팀원 프로필 사진 저장 및 업로드 요청");
        memberService.upload(memberId, file);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
