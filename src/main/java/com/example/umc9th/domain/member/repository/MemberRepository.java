package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    /*
    SELECT phone_number, nickname, email
    FROM USER
    WHERE id = :userId;
    */
    @Query("SELECT m FROM Member m WHERE m.id = :memberId")
    Optional<Member> findMyPageInfo(@Param("memberId") Long memberId);


}
