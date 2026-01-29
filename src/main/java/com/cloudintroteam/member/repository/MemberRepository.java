package com.cloudintroteam.member.repository;

import com.cloudintroteam.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
