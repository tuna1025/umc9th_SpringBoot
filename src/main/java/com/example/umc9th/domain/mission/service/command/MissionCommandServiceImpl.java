package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.member.Exception.MemberErrorCode;
import com.example.umc9th.domain.member.Exception.MemberException;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.member.repository.MemberMissionRepository;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.request.MissionRequestDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.exception.MissionErrorCode;
import com.example.umc9th.domain.mission.exception.MissionException;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    @Transactional
    public MemberMission challengeMission(MissionRequestDTO.ChallengeDTO request) {

        // 이미 도전중인지 확인
        if (memberMissionRepository.existsByMemberIdAndMissionId(request.memberId(), request.missionId())) {
            throw new MemberException(MemberErrorCode.ALREADY_CHALLENGING);
        }

        // 엔티티 조회
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        Mission mission = missionRepository.findById(request.missionId())
                .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));

        // 매핑 엔티티 생성
        MemberMission memberMission = MissionConverter.toMemberMission(member, mission);

        // 저장 및 반환
        return memberMissionRepository.save(memberMission);
    }

    @Override
    @Transactional
    public MemberMission completeMission(Long memberMissionId) {
        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));

        memberMission.complete(); // true로 변경

        return memberMission; //변경된 상태를 가진 엔티티를 그대로 반환
    }
}
