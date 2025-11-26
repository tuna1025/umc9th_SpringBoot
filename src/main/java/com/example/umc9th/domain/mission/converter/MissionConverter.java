package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.dto.response.MissionResponseDTO;
import com.example.umc9th.domain.mission.entity.Mission;

import java.time.LocalDateTime;

public class MissionConverter {
    public static MemberMission toMemberMission(Member member, Mission mission){
        return MemberMission.builder()
                .mission(mission)
                .member(member)
                .isComplete(false) // 처음 도전 시 완료 여부는 false
                .build();
    }

    public static MissionResponseDTO.ChallengeMissionResultDTO toChallengeMissionResultDTO(MemberMission memberMission){
        return new MissionResponseDTO.ChallengeMissionResultDTO(
                memberMission.getId(),
                memberMission.getMember().getId(),
                memberMission.getMission().getId(),
                LocalDateTime.now()
        );
    }
}
