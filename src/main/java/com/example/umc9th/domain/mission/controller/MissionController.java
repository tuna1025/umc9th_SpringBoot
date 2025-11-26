package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.member.Exception.MemberSuccessCode;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.member.service.query.MemberQueryService;
import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.request.MissionRequestDTO;
import com.example.umc9th.domain.mission.dto.response.MissionResponseDTO;
import com.example.umc9th.domain.mission.exception.MissionSuccessCode;
import com.example.umc9th.domain.mission.service.command.MissionCommandService;
import com.example.umc9th.domain.store.service.query.StoreQueryService;
import com.example.umc9th.global.annotation.CheckPage;
import com.example.umc9th.global.apiPaylode.ApiResponse;
import com.example.umc9th.global.exception.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionCommandService missionCommandService;
    private final StoreQueryService storeQueryService;
    private final MemberQueryService memberQueryService;

    @PostMapping("/missions/challenge")
    public ApiResponse<MissionResponseDTO.ChallengeMissionResultDTO> challengeMission(
            @RequestBody @Valid MissionRequestDTO.ChallengeDTO request
    ){
        MemberMission memberMission = missionCommandService.challengeMission(request);
        return ApiResponse.onSuccess(MissionSuccessCode.ADD, MissionConverter.toChallengeMissionResultDTO(memberMission));
    }

    @GetMapping("/stores/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게의 미션을 쿼리 스트링으로 받은 페이지 번호(1 이상)에 따라 10개씩 조회합니다.")
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다."),
            @Parameter(name = "page", description = "페이지 번호, 1번이 1 페이지 입니다.")
    })
    public ApiResponse<MissionResponseDTO.MissionPreviewListDTO> getMissions(
            @PathVariable(name = "storeId") Long storeId,
            @CheckPage @RequestParam(name = "page") Integer page
    ) {
        // 서비스 호출 (이미 DTO로 변환되어 반환됨)
        MissionResponseDTO.MissionPreviewListDTO missionList = storeQueryService.getMissionList(storeId, page);

        // 응답 반환
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionList);
    }

    @GetMapping("/members/missions")
    @Operation(summary = "내가 진행중인 미션 목록 조회 API", description = "진행중인 미션을 10개씩 조회합니다.")
    @Parameters({
            @Parameter(name = "memberId", description = "사용자 ID"),
            @Parameter(name = "page", description = "페이지 번호 (1 이상)")
    })
    public ApiResponse<MissionResponseDTO.MyMissionPreviewListDTO> getMyMissions(
            @RequestParam(name = "memberId") Long memberId,
            @CheckPage @RequestParam(name = "page") Integer page
    ) {
        // 1. Service에서 엔티티 Page를 받아옴 (Page<MemberMission>)
        Page<MemberMission> missionPage = memberQueryService.getMyChallengingMissions(memberId, page);

        // 2. MemberMission 엔티티 페이지를 DTO로 변환
        // (MemberMission 객체에 상태 정보가 있으므로, map(MemberMission::getMission)을 하지 않고 그대로 넘깁니다.)
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, MissionConverter.toMyMissionPreviewListDTO(missionPage));
    }

}
