package com.bizz.hello.repository;

import com.bizz.hello.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepo {
    Member save(Member member);

    Optional<Member> findById(String uuid);
    Optional<Member> findByName(String name);
    List<Member> findAll();
    void clearStore();
}
