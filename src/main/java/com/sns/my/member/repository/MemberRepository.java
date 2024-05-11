package com.sns.my.member.repository;

import com.sns.my.member.model.Member;
import java.util.Optional;
import java.util.List;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(long id);
    Optional<Member> findByUsername(String username);
    Optional<Member> findByRealname(String realname);
    List<Member> findAll();
}
