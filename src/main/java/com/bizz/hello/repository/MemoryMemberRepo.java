package com.bizz.hello.repository;

import com.bizz.hello.domain.Member;

import java.util.*;

public class MemoryMemberRepo implements MemberRepo{

    private static Map<String, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Member save(Member member) {
        String uuid = UUID.randomUUID().toString(); // UUID 생성
        member.setUuid(uuid);
        store.put(member.getUuid(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(String uuid) {
        return Optional.ofNullable(store.get(uuid));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}
