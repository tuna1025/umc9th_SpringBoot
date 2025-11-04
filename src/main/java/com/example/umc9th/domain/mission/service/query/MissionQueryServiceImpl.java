package com.example.umc9th.domain.mission.service.query;


import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {
    private final MissionRepository missionRepository;

    public List<Mission> getHomeMissionList(Long memberId, Long regionId, Long cursorId) {
        // 첫 페이지일 경우 제일 큰값
        Long safeCursorId = (cursorId == null) ? Long.MAX_VALUE : cursorId;

        return missionRepository.findAvailableMissionsForHome(
                memberId,
                regionId,
                safeCursorId,
                PageRequest.of(0, 10) //limit 10
        );
    }
}
