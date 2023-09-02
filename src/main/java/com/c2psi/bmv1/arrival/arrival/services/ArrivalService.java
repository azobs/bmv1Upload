package com.c2psi.bmv1.arrival.arrival.services;

import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofArrivalDto;
import com.c2psi.bmv1.dto.ArrivalDto;

import java.util.List;

public interface ArrivalService {
    ArrivalDto saveArrival(ArrivalDto arrivalDto);
    ArrivalDto updateArrival(ArrivalDto arrivalDto);
    Boolean deleteArrivalById(Long id);
    ArrivalDto getArrivalById(Long id);
    List<ArrivalDto> getListofArrival(FilterRequest filterRequest);
    PageofArrivalDto getPageofArrival(FilterRequest filterRequest);
    Boolean isArrivalExistWith(Long id);
}
