package org.mertguler.cinemium.service.seat;

import org.mertguler.cinemium.exception.model.APIException;
import org.mertguler.cinemium.exception.model.ResourceAlreadyExistException;
import org.mertguler.cinemium.exception.model.ResourceNotFoundException;
import org.mertguler.cinemium.model.building.Cinema;
import org.mertguler.cinemium.model.building.Stage;
import org.mertguler.cinemium.model.building.seat.Seat;
import org.mertguler.cinemium.model.building.seat.SeatType;
import org.mertguler.cinemium.payload.dto.SeatDTO;
import org.mertguler.cinemium.payload.dto.StageDTO;
import org.mertguler.cinemium.payload.response.SeatResponse;
import org.mertguler.cinemium.payload.response.StageResponse;
import org.mertguler.cinemium.repository.SeatRepository;
import org.mertguler.cinemium.repository.StageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatServiceImpl implements SeatService{

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private StageRepository stageRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public SeatResponse getSeats(Long stageId) {
        List<Seat> seats = seatRepository.findAllByStageId(stageId)
                .orElseThrow(() -> new ResourceNotFoundException("Stage", "stageId", stageId));

        List<SeatDTO> seatDTOs = seats.stream()
                .map(seat -> modelMapper.map(seat, SeatDTO.class))
                .toList();

        SeatResponse seatResponse = new SeatResponse();
        seatResponse.setContent(seatDTOs);
        return seatResponse;
    }

    @Override
    public SeatDTO createSeat(SeatDTO seatDTO, Long stageId) {
        Stage stage = stageRepository.findById(stageId)
                .orElseThrow(() -> new ResourceNotFoundException("Stage", "stageId", stageId));

        Seat seat = modelMapper.map(seatDTO, Seat.class);

        Integer rowIndex = seat.getRowIndex();
        Integer columnIndex = seat.getColumnIndex();
        Seat seatFromDb = seatRepository.findSeatByStageIdAndPosition(stageId ,rowIndex,columnIndex );

        if (seatFromDb != null) {
            throw new ResourceAlreadyExistException("Seat", rowIndex, columnIndex);
            // Daha iyi bir exception kullanilabilir
        }

        seat.setStage(stage);

        Seat savedSeat = seatRepository.save(seat);
        return modelMapper.map(savedSeat, SeatDTO.class);
    }

    @Override
    public SeatDTO updateSeat(SeatDTO seatDTO, Long seatId) {
        Seat savedSeat = seatRepository.findById(seatId)
                .orElseThrow(() -> new ResourceNotFoundException("Seat", "seatId", seatId));

        Seat seat = modelMapper.map(seatDTO, Seat.class);
        seat.setSeatId(seatId);
        seat.setStage(savedSeat.getStage());
        savedSeat = seatRepository.save(seat);
        return modelMapper.map(savedSeat, SeatDTO.class);
    }

    @Override
    public SeatDTO deleteSeat(Long seatId) {
        Seat seatFromDb = seatRepository.findById(seatId)
                .orElseThrow(() -> new ResourceNotFoundException("Seat", "seatId", seatId));

        seatRepository.delete(seatFromDb);
        return modelMapper.map(seatFromDb, SeatDTO.class);
    }

    @Override
    public SeatResponse createCoupleSeats(Long stageId, SeatDTO firstSeatDTO, SeatDTO secondSeatDTO) {
        Stage stage = stageRepository.findById(stageId)
                .orElseThrow(() -> new ResourceNotFoundException("Stage", "stageId", stageId));

        Seat firstSeat = modelMapper.map(firstSeatDTO, Seat.class);
        Seat secondSeat = modelMapper.map(secondSeatDTO, Seat.class);

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

        SeatDTO savedFirstSeatDTO = modelMapper.map(seatRepository.save(firstSeat), SeatDTO.class);
        SeatDTO savedSecondSeatDTO = modelMapper.map(seatRepository.save(secondSeat), SeatDTO.class);

        List<SeatDTO> seatDTOs = new ArrayList<>();
        return new SeatResponse(seatDTOs);

    }

    //@Override
    public SeatResponse makeSeatsCouple(Long firstSeatId, Long secondSeatId) {
        Seat firstSeat = seatRepository.findById(firstSeatId)
                .orElseThrow(() -> new ResourceNotFoundException("Seat", "seatId", firstSeatId));

        Seat secondSeat = seatRepository.findById(secondSeatId)
                .orElseThrow(() -> new ResourceNotFoundException("Seat", "seatId", secondSeatId));

        firstSeat.setCoupleId(secondSeatId);
        firstSeat.setType("COUPLE");
        secondSeat.setCoupleId(firstSeatId);
        secondSeat.setType("COUPLE");

        Seat savedFirstSeat = seatRepository.save(firstSeat);
        Seat savedSecondSeat = seatRepository.save(secondSeat);

        SeatDTO savedFirstSeatDTO = modelMapper.map(savedFirstSeat, SeatDTO.class);
        SeatDTO savedSecondSeatDTO = modelMapper.map(savedSecondSeat, SeatDTO.class);
        List<SeatDTO> seatDTOS = new ArrayList<>();
        seatDTOS.add(savedFirstSeatDTO);
        seatDTOS.add(savedSecondSeatDTO);

        return new SeatResponse(seatDTOS);
    }


}
