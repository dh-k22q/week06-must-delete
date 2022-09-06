package com.sparta.week06.repository;

import com.sparta.week06.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {
    Optional<Member> findMemberById(String id);
    static Optional<Member> staticFindMemberById(String id);
    Optional<Member> findByUsername(String username);

    @EntityGraph(attributePaths = "authorities")
    Optional<Member> findOneWithAuthoritiesByUsername(String username);

    Optional<Member> findByNickname(String nickname);

    @Override
    void delete(Member entity);
}
