package com.cloudintroteam.member.service;

import com.cloudintroteam.member.dto.request.SaveMemberRequest;
import com.cloudintroteam.member.dto.response.GetMemberResponse;
import com.cloudintroteam.member.dto.response.SaveMemberResponse;
import com.cloudintroteam.member.entity.Member;
import com.cloudintroteam.member.exception.FileUploadFailedException;
import com.cloudintroteam.member.exception.MemberNotFoundException;
import com.cloudintroteam.member.repository.MemberRepository;
import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final S3Template s3Template;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

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

    // 팀원 프로필 사진 저장 및 업로드
    @Transactional
    public void upload(Long memberId, MultipartFile file) {
        if(file.isEmpty()) {
            throw new FileUploadFailedException("저장할 이미지가 없습니다.");
        }

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException("존재하지 않는 팀원입니다."));

        String key = "uploads/" + UUID.randomUUID() + "_" + file.getOriginalFilename();

        // 프로필 사진 업로드
        try {
            s3Template.upload(bucket, key, file.getInputStream());
        } catch (IOException e) {
            throw new FileUploadFailedException("파일 업로드에 실패했습니다.");
        }

        // 프로필 사진 저장
        member.saveProfileImage(key);
    }
}
