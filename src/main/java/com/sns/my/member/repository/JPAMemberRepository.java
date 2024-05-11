package com.sns.my.member.repository;

import com.sns.my.member.model.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

@Primary
public interface JPAMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    @Override
    Optional<Member> findByUsername(String username);
    @Override
    Optional<Member> findByRealname(String realname);
}
