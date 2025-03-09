package org.mertguler.cinemium.impl;

import org.mertguler.cinemium.exception.APIException;
import org.mertguler.cinemium.exception.ResourceAlreadyExistException;
import org.mertguler.cinemium.exception.ResourceNotFoundException;
import org.mertguler.cinemium.mapper.CustomMapper;
import org.mertguler.cinemium.model.building.Seat;
import org.mertguler.cinemium.model.building.SeatType;
import org.mertguler.cinemium.model.building.Stage;
import org.mertguler.cinemium.payload.dto.SeatDTO;
import org.mertguler.cinemium.payload.response.SeatResponse;
import org.mertguler.cinemium.repository.SeatRepository;
import org.mertguler.cinemium.repository.StageRepository;
import org.mertguler.cinemium.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private StageRepository stageRepository;

    @Autowired
    private CustomMapper mapper;


    @Override
    public SeatResponse getSeats(String stageId) {
        List<Seat> seats = seatRepository.findAllByStageId(stageId)
                .orElseThrow(() -> new ResourceNotFoundException("Stage", "stageId", stageId));

        List<SeatDTO> seatDTOs = seats.stream()
                .map(seat -> mapper.toSeatDto(seat))
                .toList();

        SeatResponse seatResponse = new SeatResponse();
        seatResponse.setContent(seatDTOs);
        return seatResponse;
    }

    @Override
    public SeatDTO createSeat(SeatDTO seatDTO, String stageId) {
        Stage stage = stageRepository.findByStageId(stageId);

        if (stage == null){
            throw new ResourceNotFoundException("Stage", "stageId", stageId);
        }

        Seat seat = mapper.toSeat(seatDTO);

        Integer rowIndex = seat.getRowIndex();
        Integer columnIndex = seat.getColumnIndex();
        Seat seatFromDb = seatRepository.findSeatByStageIdAndPosition(stageId ,rowIndex,columnIndex );

        if (seatFromDb != null) {
            throw new ResourceAlreadyExistException("Seat", rowIndex, columnIndex);
        }

        seat.setStage(stage);

        Seat savedSeat = seatRepository.save(seat);
        return mapper.toSeatDto(savedSeat);
    }

    @Override
    public SeatDTO updateSeat(SeatDTO seatDTO, UUID seatId) {
        Seat savedSeat = seatRepository.findBySeatId(seatId);

        if (savedSeat == null){
            throw new ResourceNotFoundException("Seat", "seatId", seatId);
        }

        Seat seat = mapper.toSeat(seatDTO);
        seat.setSeatId(seatId);
        seat.setStage(savedSeat.getStage());
        savedSeat = seatRepository.save(seat);
        return mapper.toSeatDto(savedSeat);
    }

    @Override
    public SeatDTO deleteSeat(UUID seatId) {
        Seat seatFromDb = seatRepository.findBySeatId(seatId);

        if (seatFromDb == null){
            throw new ResourceNotFoundException("Seat", "seatId", seatId);
        }

        seatRepository.delete(seatFromDb);
        return mapper.toSeatDto(seatFromDb);
    }

    @Override
    public SeatResponse createCoupleSeats(String stageId, SeatDTO firstSeatDTO, SeatDTO secondSeatDTO) {
        Stage stage = stageRepository.findByStageId(stageId);

        if (stage == null){
            throw new ResourceNotFoundException("Stage", "stageId", stageId);
        }

        Seat firstSeat = mapper.toSeat(firstSeatDTO);
        Seat secondSeat = mapper.toSeat(secondSeatDTO);

        if (!(firstSeat.getType().equals(SeatType.COUPLE.name())
                || !(secondSeat.getType().equals(SeatType.COUPLE.name())))){
            throw new APIException("Both seats must be type of: SeatType.COUPLE");
        }

        Integer firstRowIndex = firstSeat.getRowIndex();
        Integer secondRowIndex = secondSeat.getRowIndex();
        Integer firstColumnIndex = firstSeat.getColumnIndex();
        Integer secondColumnIndex = secondSeat.getColumnIndex();

        if (!(firstColumnIndex + 1 == secondColumnIndex && firstRowIndex.equals(secondRowIndex))){
            throw new APIException("Seats row indexes are not valid for: SeatType.COUPLE");
        }

        Seat firstSeatFromDb = seatRepository.findSeatByStageIdAndPosition(stageId ,firstRowIndex,firstColumnIndex );

        if (firstSeatFromDb != null) {
            throw new ResourceAlreadyExistException("Seat", firstRowIndex, firstColumnIndex);
            // Daha iyi bir exception kullanilabilir
        }

        Seat secondSeatFromDb = seatRepository.findSeatByStageIdAndPosition(stageId ,secondRowIndex,secondColumnIndex );

        if (secondSeatFromDb != null) {
            throw new ResourceAlreadyExistException("Seat", secondRowIndex, secondColumnIndex);
            // Daha iyi bir exception kullanilabilir
        }

        firstSeat.setStage(stage);
        secondSeat.setStage(stage);

        SeatDTO savedFirstSeatDTO = mapper.toSeatDto(firstSeat);
        SeatDTO savedSecondSeatDTO = mapper.toSeatDto(secondSeat);

        List<SeatDTO> seatDTOs = new ArrayList<>();
        return new SeatResponse(seatDTOs);

    }

}
