package com.sns.my.member.service;

import com.sns.my.member.model.Member;
import com.sns.my.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public long join(Member member){
        validateDupMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    public void validateDupMember(Member member){
        memberRepository.findByUsername(member.getUsername())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원 ID");
                });
    }

    public Optional<Member> findByUserName(String username){
        return memberRepository.findByUsername(username);
    }

    public Optional<Member> findByRealName(String realname){
        return memberRepository.findByUsername(realname);
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }
}
