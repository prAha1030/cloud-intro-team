package com.cloudintroteam.member.dto.request;

import com.cloudintroteam.member.entity.Mbti;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SaveMemberRequest {
    // 팀원 저장 시 : 이름, 나이, MBTI 요청

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotNull(message = "나이를 입력해주세요.")
    private int age;

    @NotNull(message = "mbti를 입력해주세요.")
    private Mbti mbti;
}
