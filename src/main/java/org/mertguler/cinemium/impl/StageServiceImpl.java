package org.mertguler.cinemium.impl;

import org.mertguler.cinemium.exception.ResourceAlreadyExistException;
import org.mertguler.cinemium.exception.ResourceNotFoundException;
import org.mertguler.cinemium.mapper.CustomMapper;
import org.mertguler.cinemium.model.building.Cinema;
import org.mertguler.cinemium.model.building.Stage;
import org.mertguler.cinemium.payload.dto.StageDTO;
import org.mertguler.cinemium.payload.response.StageResponse;
import org.mertguler.cinemium.repository.CinemaRepository;
import org.mertguler.cinemium.repository.StageRepository;
import org.mertguler.cinemium.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StageServiceImpl implements StageService {
    @Autowired
    private StageRepository stageRepository;

    @Autowired
    private CustomMapper mapper;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    public StageResponse getStages(Long cinemaId) {
        List<Stage> stages = stageRepository.findAllByCinemaId(cinemaId)
                .orElseThrow(() -> new ResourceNotFoundException("Cinema", "cinemaId", cinemaId));


        List<StageDTO> stageDTOS = stages.stream()
                .map(stage -> mapper.toStageDto(stage))
                .toList();

        StageResponse stageResponse = new StageResponse();
        stageResponse.setContent(stageDTOS);
        return stageResponse;
    }

    @Override
    public StageDTO createStage(StageDTO stageDTO, String cinemaId) {
        Stage stage = mapper.toStage(stageDTO);
        Cinema cinema = cinemaRepository.findById(cinemaId)
                .orElseThrow(() -> new ResourceNotFoundException("Cinema", "cinemaId", cinemaId));

        String name = stage.getName();
        Stage stageFromDb = stageRepository.findStageByCinemaIdAndName(cinemaId ,name);

        if (stageFromDb != null) {
            throw new ResourceAlreadyExistException("Stage", "name", name);
        }

        stage.setCinema(cinema);

        Stage savedStage = stageRepository.save(stage);
        return mapper.toStageDto(savedStage);
    }

    @Override
    public StageDTO updateStage(StageDTO stageDTO, String stageId) {
        Stage savedStage = stageRepository.findByStageId(stageId);

        if (savedStage == null){
            throw new ResourceNotFoundException("Stage", "stageId", stageId);
        }
        Stage stage = mapper.toStage(stageDTO);
        stage.setStageId(stageId);
        stage.setCinema(savedStage.getCinema());
        savedStage = stageRepository.save(stage);
        return mapper.toStageDto(savedStage);
    }

    @Override
    public StageDTO deleteStage(String stageId) {
        Stage stageFromDb = stageRepository.findByStageId(stageId);

        if (stageFromDb == null){
            throw new ResourceNotFoundException("Stage", "stageId", stageId);
        }

        stageRepository.delete(stageFromDb);
        return mapper.toStageDto(stageFromDb);
    }
}
