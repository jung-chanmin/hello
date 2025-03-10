package com.bizz.hello.repository;

import com.bizz.hello.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;

@Slf4j
class MemoryMemberRepositoryTest {
    private MemberRepo repository;

    @BeforeEach
    void setUp() {
        repository = new MemoryMemberRepo(); // 메모리 기반 저장소 사용
    }

    @AfterEach
    public void AfterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        log.info("[save]");

        //given
        Member member = new Member();
        member.setName("spring");

        //when
        repository.save(member);

        //then
        log.info("[save] member.getName() : {}", member.getName());

        Member result = repository.findByName(member.getName()).orElse(null);

        log.info("[save] member : {}", member);
        log.info("[save] result : {}", result);

        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //when
        Member result = repository.findByName("spring1").get();

        log.info("[findByName] member1 : {}", member1);
        log.info("[findByName] result : {}", result);
        //then
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //when
        List<Member> result = repository.findAll();

        log.info("[findAll] result : {}", result);
        //then
        assertThat(result.size()).isEqualTo(2);
    }
}
