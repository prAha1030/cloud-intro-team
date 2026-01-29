package com.cloudintroteam.member.dto.request;

import com.cloudintroteam.member.entity.Mbti;
import lombok.Getter;

@Getter
public class SaveMemberRequest {
    // 팀원 저장 시 : 이름, 나이, MBTI 요청
    private String name;
    private int age;
    private Mbti mbti;
}
