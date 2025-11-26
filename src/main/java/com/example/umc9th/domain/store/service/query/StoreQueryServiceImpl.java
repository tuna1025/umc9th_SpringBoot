package com.example.umc9th.domain.store.service.query;

import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.response.MissionResponseDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.store.exception.StoreErrorCode;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Override
    public MissionResponseDTO.MissionPreviewListDTO getMissionList(Long storeId, Integer page) {
        // 가게 존재 여부 검증
        storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        // 페이징 처리 (PageRequest 생성, page는 0부터 시작하므로 -1)
        Page<Mission> missionPage = missionRepository.findAllByStoreId(storeId, PageRequest.of(page - 1, 10));

        // 엔티티 Page를 DTO로 변환
        return MissionConverter.toMissionPreviewListDTO(missionPage);
    }



}