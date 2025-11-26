package com.example.umc9th.domain.store.service.query;

import com.example.umc9th.domain.mission.dto.response.MissionResponseDTO;
import org.springframework.data.domain.Page;

public interface StoreQueryService {
    MissionResponseDTO.MissionPreviewListDTO getMissionList(Long storeId, Integer page);
}
