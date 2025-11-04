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
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Member getMyPageInfo(Long memberId) {
        return memberRepository.findMyPageInfo(memberId)
                .orElseThrow(() -> new GeneralException(MemberErrorCode.MEMBER_NOT_FOUND));
    }

    @Override
    public List<MemberMission> getMyMissions(Member member, Long cursorId) {
        // 페이지 사이즈 10개 (LIMIT 10), 개인 미션은 개수가 적기때문에 첫번째 커서를 안주고 필요시 주면됨
        return memberMissionRepository.findMyMissionsByCursor(member, cursorId, PageRequest.of(0, 10));
    }



}
