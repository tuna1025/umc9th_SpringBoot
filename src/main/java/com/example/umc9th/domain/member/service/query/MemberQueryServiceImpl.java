package com.example.umc9th.domain.member.service.query;


import com.example.umc9th.domain.member.Exception.MemberErrorCode;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.global.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberQueryServiceImpl {

    private final MemberRepository memberRepository;

    public Member getMyPageInfo(Long memberId) {
        return memberRepository.findMyPageInfo(memberId)
                .orElseThrow(() -> new GeneralException(MemberErrorCode.MEMBER_NOT_FOUND));
    }
}
