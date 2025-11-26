package com.example.umc9th.domain.mission.service.command;


import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.dto.request.MissionRequestDTO;
import org.springframework.transaction.annotation.Transactional;

public interface MissionCommandService {
    MemberMission challengeMission(MissionRequestDTO.ChallengeDTO request);

    MemberMission completeMission(Long memberMissionId);
}
