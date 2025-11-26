package com.example.umc9th.domain.member.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResDTO {
    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createAt
    ){}
}
