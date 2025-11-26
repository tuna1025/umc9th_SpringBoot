package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    /*
    SELECT
    um.id AS user_mission_id,
    m.reward,
    s.name AS store_name,
    m.content AS mission_content,
    um.status AS mission_status
    FROM USER_MISSION AS um
    JOIN MISSION AS m ON um.mission_id = m.id
    JOIN STORE AS s ON m.store_id = s.id
    WHERE um.user_id = :userId
    AND um.id < :cursorId
    ORDER BY um.id DESC
    LIMIT 10;
    */
    @Query("""
        SELECT mm
        FROM MemberMission mm
        JOIN FETCH mm.mission m
        JOIN FETCH m.store s
        WHERE mm.member = :member
        AND mm.id < :cursorId
        ORDER BY mm.id DESC
        """) // """ """ 텍스트 블록 + 연산자 안쓰고 연결 가능
    List<MemberMission> findMyMissionsByCursor(
            @Param("member") Member member,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    ); // 커서 크기는 서비스레이어에서 정의

    // 이미 도전중인지 확인
    boolean existsByMemberIdAndMissionId(Long memberId, Long missionId);

    // 진행중인 미션 조회
    Page<MemberMission> findAllByMemberIdAndIsComplete(Long memberId, boolean isComplete, Pageable pageable);
}
