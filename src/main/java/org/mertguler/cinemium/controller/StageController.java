package org.mertguler.cinemium.controller;

import jakarta.validation.Valid;
import org.mertguler.cinemium.payload.dto.StageDTO;
import org.mertguler.cinemium.payload.response.StageResponse;
import org.mertguler.cinemium.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StageController {
    @Autowired
    private StageService stageService;

    @GetMapping("/public/cinemas/{cinemaId}/stages")
    public ResponseEntity<StageResponse> getStages(@PathVariable Long cinemaId){
        StageResponse stageResponse = stageService.getStages(cinemaId);
        return new ResponseEntity<>(stageResponse, HttpStatus.OK);
    }

    @PostMapping("/public/cinemas/{cinemaId}/stages")
    public ResponseEntity<StageDTO> createStage(@Valid @RequestBody StageDTO stageDTO,
                                                @PathVariable Long cinemaId){
        StageDTO savedStageDTO = stageService.createStage(stageDTO, cinemaId);
        return new ResponseEntity<>(savedStageDTO, HttpStatus.OK);
    }

    @PutMapping("/public/cinemas/stages/{stageId}")
    public ResponseEntity<StageDTO> updateStage(@Valid @RequestBody StageDTO stageDTO,
                                                @PathVariable Long stageId){
        StageDTO savedStageDTO = stageService.updateStage(stageDTO, stageId);
        return new ResponseEntity<>(savedStageDTO, HttpStatus.OK);
    }

    @DeleteMapping("/public/cinemas/stages/{stageId}")
    public ResponseEntity<StageDTO> deleteStage(@PathVariable Long stageId){
        StageDTO deletedStageDTO = stageService.deleteStage(stageId);
        return new ResponseEntity<>(deletedStageDTO, HttpStatus.OK);
    }

}
