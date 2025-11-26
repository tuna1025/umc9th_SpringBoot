package com.example.umc9th.domain.member.service.query;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MemberQueryService {
    Member getMyPageInfo(Long memberId);

    List<MemberMission> getMyMissions(Member member, Long cursorId);

    Page<MemberMission> getMyChallengingMissions(Long memberId, Integer page);
}
