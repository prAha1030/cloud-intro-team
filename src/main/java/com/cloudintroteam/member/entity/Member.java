package com.cloudintroteam.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Mbti mbti;

    public Member(String name, int age, Mbti mbti) {
        this.name = name;
        this.age = age;
        this.mbti = mbti;
    }
}
