package org.mertguler.cinemium.impl;

import org.mertguler.cinemium.exception.ResourceAlreadyExistException;
import org.mertguler.cinemium.exception.ResourceNotFoundException;
import org.mertguler.cinemium.model.building.Cinema;
import org.mertguler.cinemium.model.building.Stage;
import org.mertguler.cinemium.payload.dto.StageDTO;
import org.mertguler.cinemium.payload.response.StageResponse;
import org.mertguler.cinemium.repository.CinemaRepository;
import org.mertguler.cinemium.repository.StageRepository;
import org.mertguler.cinemium.service.StageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StageServiceImpl implements StageService {
    @Autowired
    private StageRepository stageRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    public StageResponse getStages(Long cinemaId) {
        List<Stage> stages = stageRepository.findAllByCinemaId(cinemaId)
                .orElseThrow(() -> new ResourceNotFoundException("Cinema", "cinemaId", cinemaId));


        List<StageDTO> stageDTOS = stages.stream()
                .map(stage -> modelMapper.map(stage, StageDTO.class))
                .toList();

        StageResponse stageResponse = new StageResponse();
        stageResponse.setContent(stageDTOS);
        return stageResponse;
    }

    @Override
    public StageDTO createStage(StageDTO stageDTO, Long cinemaId) {
        Stage stage = modelMapper.map(stageDTO, Stage.class);
        Cinema cinema = cinemaRepository.findById(cinemaId)
                .orElseThrow(() -> new ResourceNotFoundException("Cinema", "cinemaId", cinemaId));

        String name = stage.getName();
        Stage stageFromDb = stageRepository.findStageByCinemaIdAndName(cinemaId ,name);

        if (stageFromDb != null) {
            throw new ResourceAlreadyExistException("Stage", "name", name);
        }

        stage.setCinema(cinema);

        Stage savedStage = stageRepository.save(stage);
        return modelMapper.map(savedStage, StageDTO.class);
    }

    @Override
    public StageDTO updateStage(StageDTO stageDTO, Long stageId) {
        Stage savedStage = stageRepository.findById(stageId)
                .orElseThrow(() -> new ResourceNotFoundException("Stage", "stageId", stageId));

        Stage stage = modelMapper.map(stageDTO, Stage.class);
        stage.setStageId(stageId);
        stage.setCinema(savedStage.getCinema());
        savedStage = stageRepository.save(stage);
        return modelMapper.map(savedStage, StageDTO.class);
    }

    @Override
    public StageDTO deleteStage(Long stageId) {
        Stage stageFromDb = stageRepository.findById(stageId)
                .orElseThrow(() -> new ResourceNotFoundException("Stage", "stageId", stageId));

        stageRepository.delete(stageFromDb);
        return modelMapper.map(stageFromDb, StageDTO.class);
    }
}
