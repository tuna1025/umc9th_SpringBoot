package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    /*
    SELECT
    m.id AS mission_id,
    s.name AS store_name,
    ft.name AS food_type_name, --식당 분류
    m.content AS mission_content,
    m.reward
    FROM MISSION AS m
    JOIN STORE AS s ON m.store_id = s.id
    JOIN FOOD_TYPE AS ft ON s.food_type_id = ft.id -- FOOD_TYPE과 JOIN
    LEFT JOIN USER_MISSION AS um ON m.id = um.mission_id AND um.user_id = :userId
    WHERE s.region_id = :regionId  -- 외래키로 조인 안하고 검색 필터링
    AND um.id IS NULL        -- 유저가 아직 수행하지 않은 미션만 필터링
    AND m.id < :cursorId
    ORDER BYm.id DESC -- 최신 미션 순으로 정렬
    LIMIT 10;
    */
    @Query("""
        SELECT DISTINCT m
        FROM Mission m
        JOIN FETCH m.store s
        JOIN FETCH s.foodType ft
        LEFT JOIN MemberMission mm 
            ON mm.mission = m AND mm.member.id = :memberId
        WHERE s.location.id = :regionId
        AND mm.id IS NULL
        AND m.id < :cursorId
        ORDER BY m.id DESC
        """)
    List<Mission> findAvailableMissionsForHome(
            @Param("memberId") Long memberId,
            @Param("regionId") Long regionId,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}
