package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.request.MissionRequestDTO;
import com.example.umc9th.domain.mission.dto.response.MissionResponseDTO;
import com.example.umc9th.domain.mission.exception.MissionSuccessCode;
import com.example.umc9th.domain.mission.service.command.MissionCommandService;
import com.example.umc9th.global.apiPaylode.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/missions/challenge")
    public ApiResponse<MissionResponseDTO.ChallengeMissionResultDTO> challengeMission(
            @RequestBody @Valid MissionRequestDTO.ChallengeDTO request
    ){
        MemberMission memberMission = missionCommandService.challengeMission(request);
        return ApiResponse.onSuccess(MissionSuccessCode.ADD, MissionConverter.toChallengeMissionResultDTO(memberMission));
    }
}
