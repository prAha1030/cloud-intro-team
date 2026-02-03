package com.cloudintroteam.member.dto.response;

import com.cloudintroteam.member.entity.Mbti;
import lombok.Getter;

@Getter
public class SaveMemberResponse {
    private final Long id;
    private final String name;
    private final int age;
    private final Mbti mbti;

    public SaveMemberResponse(Long id, String name, int age, Mbti mbti) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.mbti = mbti;
    }
}
