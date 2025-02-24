package org.mertguler.cinemium.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mertguler.cinemium.payload.dto.SeatDTO;
import org.mertguler.cinemium.payload.dto.StageDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatResponse {
    private List<SeatDTO> content;
}
