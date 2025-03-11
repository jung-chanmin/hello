package com.bizz.hello.service;

import com.bizz.hello.repository.MemoryMemberRepository;

import com.bizz.hello.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemoryMemberRepository memberRepository;

    //public MemberService(MemoryMemberRepo memoryMemberRepo) {
    //    this.memberRepo = memoryMemberRepo;
    //
    //}
    /**
     *
     회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        log.info("[join] member : {}" ,member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    log.info("[validateDuplicateMember] 이미 존재하는 회원입니다.");
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     *
     전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public void clearStore() {
        memberRepository.clearStore();
    }
}