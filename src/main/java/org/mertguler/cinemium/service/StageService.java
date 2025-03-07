package org.mertguler.cinemium.service;

import jakarta.validation.Valid;
import org.mertguler.cinemium.payload.dto.StageDTO;
import org.mertguler.cinemium.payload.response.StageResponse;

public interface StageService {
    StageResponse getStages(Long cinemaId);

    StageDTO createStage(@Valid StageDTO stageDTO, Long cinemaId);

    StageDTO updateStage(@Valid StageDTO stageDTO, Long stageId);

    StageDTO deleteStage(Long stageId);
}
