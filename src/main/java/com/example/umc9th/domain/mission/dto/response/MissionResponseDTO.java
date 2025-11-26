package com.example.umc9th.domain.mission.dto.response;

import java.time.LocalDateTime;

public class MissionResponseDTO {
    public record ChallengeMissionResultDTO(
            Long memberMissionId,
            Long memberId,
            Long missionId,
            LocalDateTime createdAt
    ){}
}
