package org.mertguler.cinemium.service;

import jakarta.validation.Valid;
import org.mertguler.cinemium.payload.dto.StageDTO;
import org.mertguler.cinemium.payload.response.StageResponse;

public interface StageService {
    StageResponse getStages(Long cinemaId);

    StageDTO createStage(StageDTO stageDTO, String cinemaId);

    StageDTO updateStage(StageDTO stageDTO, String stageId);

    StageDTO deleteStage(String stageId);
}
