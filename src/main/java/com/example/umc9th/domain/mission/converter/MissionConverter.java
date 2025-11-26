package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.dto.response.MissionResponseDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public static MissionResponseDTO.MissionPreviewDTO toMissionPreviewDTO(Mission mission) {
        return MissionResponseDTO.MissionPreviewDTO.builder()
                .missionId(mission.getId())
                .point(mission.getPoint())
                .price(mission.getPrice())
                .content(mission.getContent())
                .createdAt(mission.getCreatedAt())
                .build();
    }

    public static MissionResponseDTO.MissionPreviewListDTO toMissionPreviewListDTO(Page<Mission> missionPage) {
        List<MissionResponseDTO.MissionPreviewDTO> missionPreviewDTOList = missionPage.stream()
                .map(MissionConverter::toMissionPreviewDTO)
                .collect(Collectors.toList());

        return MissionResponseDTO.MissionPreviewListDTO.builder()
                .isLast(missionPage.isLast())
                .isFirst(missionPage.isFirst())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .listSize(missionPreviewDTOList.size())
                .missionList(missionPreviewDTOList)
                .build();
    }

    public static MissionResponseDTO.MyMissionPreviewDTO toMyMissionPreviewDTO(MemberMission memberMission) {
        return MissionResponseDTO.MyMissionPreviewDTO.builder()
                .missionId(memberMission.getMission().getId())
                .storeName(memberMission.getMission().getStore().getName())
                .point(memberMission.getMission().getPoint())
                .content(memberMission.getMission().getContent())
                .status(memberMission.getIsComplete() ? "성공" : "진행중") // 상태 결정 로직
                .build();
    }

    public static MissionResponseDTO.MyMissionPreviewListDTO toMyMissionPreviewListDTO(Page<MemberMission> missionPage) {
        List<MissionResponseDTO.MyMissionPreviewDTO> myMissionPreviewDTOList = missionPage.stream()
                .map(MissionConverter::toMyMissionPreviewDTO)
                .collect(Collectors.toList());

        return MissionResponseDTO.MyMissionPreviewListDTO.builder()
                .isLast(missionPage.isLast())
                .isFirst(missionPage.isFirst())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .listSize(myMissionPreviewDTOList.size())
                .missionList(myMissionPreviewDTOList)
                .build();
    }

}
