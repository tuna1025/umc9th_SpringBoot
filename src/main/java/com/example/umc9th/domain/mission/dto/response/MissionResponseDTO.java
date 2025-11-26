package com.example.umc9th.domain.mission.dto.response;


import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResponseDTO {

    @Builder
    public record ChallengeMissionResultDTO(
            Long memberMissionId,
            Long memberId,
            Long missionId,
            LocalDateTime createdAt
    ){}

    @Builder
    public record MissionPreviewDTO(
            Long missionId,
            Integer point,
            Long price,
            String content,
            LocalDateTime createdAt
    ){}

    @Builder
    public record MissionPreviewListDTO(
            List<MissionPreviewDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}
}