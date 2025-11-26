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

    @Builder
    public record MyMissionPreviewDTO(
            Long missionId,
            String storeName,
            Integer point,
            String content,
            String status // 진행중 or 성공
    ){}

    @Builder
    public record MyMissionPreviewListDTO(
            List<MyMissionPreviewDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record MissionCompleteDTO(
            Long memberMissionId,
            String missionStatus, // "성공" 등 상태 표시
            Long missionId,
            Integer rewardPoint   // 획득한 포인트 등 정보 표시
    ){}
}