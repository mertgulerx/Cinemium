package org.mertguler.cinemium.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mertguler.cinemium.payload.dto.CinemaDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CinemaResponse {
    private List<CinemaDTO> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private boolean lastPage;
}
