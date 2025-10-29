package com.example.umc9th.domain.member.service.query;


import com.example.umc9th.domain.member.Exception.MemberErrorCode;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.member.repository.MemberMissionRepository;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.global.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberQueryServiceImpl {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    public Member getMyPageInfo(Long memberId) {
        return memberRepository.findMyPageInfo(memberId)
                .orElseThrow(() -> new GeneralException(MemberErrorCode.MEMBER_NOT_FOUND));
    }
    public List<MemberMission> getMyMissions(Member member, Long cursorId) {
        // 페이지 사이즈 10개 (LIMIT 10)
        return memberMissionRepository.findMyMissionsByCursor(member, cursorId, PageRequest.of(0, 10));
    }

}
