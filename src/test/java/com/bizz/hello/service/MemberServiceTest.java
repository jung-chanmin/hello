package com.bizz.hello.service;

import com.bizz.hello.domain.Member;
import com.bizz.hello.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepo;

    @BeforeEach
    public void beforeEach() {
        memberRepo = new MemoryMemberRepository();
        memberService = new MemberService(memberRepo);
    }

    @AfterEach
    public void afterEach() {
        memberService.clearStore();
    }
    @Test
    public void join() throws Exception {
        //Given
        Member member = new Member();
        member.setName("hello");
        //When
        Long saveId = memberService.join(member);
        //Then
        Member findMember = memberService.findOne(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }
    @Test
    public void exceptDuplMember() {
        //Given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
        //When
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    public void exceptDuplMember2() {
        // Given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        // When
        memberService.join(member1);

        // Then
//        assertThatThrownBy(() -> memberService.join(member2))
//                .isInstanceOf(IllegalStateException.class);

        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

//        assertThatThrownBy(() -> memberService.join(member2))
//                .isInstanceOf(Exception.class);
    }
}