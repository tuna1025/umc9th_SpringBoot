package com.example.umc9th.domain.mission.dto.request;

public class MissionRequestDTO {
    public record ChallengeDTO(
            Long memberId,
            Long missionId
    ){}
}
