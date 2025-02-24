package org.mertguler.cinemium.service.seat;

import org.mertguler.cinemium.exception.model.ResourceAlreadyExistException;
import org.mertguler.cinemium.exception.model.ResourceNotFoundException;
import org.mertguler.cinemium.model.building.Cinema;
import org.mertguler.cinemium.model.building.Stage;
import org.mertguler.cinemium.model.building.seat.Seat;
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
        Seat seat = modelMapper.map(seatDTO, Seat.class);
        Stage stage = stageRepository.findById(stageId)
                .orElseThrow(() -> new ResourceNotFoundException("Stage", "stageId", stageId));

        Integer rowIndex = seat.getRowIndex();
        Integer columnIndex = seat.getColumnIndex();
        Seat seatFromDb = seatRepository.findSeatByStageIdAndPosition(stageId ,rowIndex,columnIndex );

        // Daha iyi bir exception kullanilabilir
        if (seatFromDb != null) {
            throw new ResourceAlreadyExistException("Seat", rowIndex, columnIndex);
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


}
