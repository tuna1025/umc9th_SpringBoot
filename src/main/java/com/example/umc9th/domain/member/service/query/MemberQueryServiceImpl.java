package com.example.umc9th.domain.member.service.query;


import com.example.umc9th.domain.member.Exception.MemberErrorCode;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.member.repository.MemberMissionRepository;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.global.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
                .orElseThrow(() -> new GeneralException(MemberErrorCode.NOT_FOUND));
    }

    @Override
    public List<MemberMission> getMyMissions(Member member, Long cursorId) {
        // 페이지 사이즈 10개 (LIMIT 10), 개인 미션은 개수가 적기때문에 첫번째 커서를 안주고 필요시 주면됨
        return memberMissionRepository.findMyMissionsByCursor(member, cursorId, PageRequest.of(0, 10));
    }

    @Override
    public Page<MemberMission> getMyChallengingMissions(Long memberId, Integer page) {
        // 멤버 존재 확인
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(MemberErrorCode.NOT_FOUND));

        // 진행중(false)인 미션 조회, 페이징 (page-1)
        return memberMissionRepository.findAllByMemberIdAndIsComplete(memberId, false, PageRequest.of(page - 1, 10));
    }

}
