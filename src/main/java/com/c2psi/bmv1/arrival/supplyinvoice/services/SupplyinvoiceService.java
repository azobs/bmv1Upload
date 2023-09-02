package com.c2psi.bmv1.arrival.supplyinvoice.services;

import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofSupplyinvoiceDto;
import com.c2psi.bmv1.dto.SupplyinvoiceDto;

import java.util.List;

public interface SupplyinvoiceService {
    SupplyinvoiceDto saveSupplyinvoice(SupplyinvoiceDto siDto);
    SupplyinvoiceDto updateSupplyinvoice(SupplyinvoiceDto siDto);
    Boolean deleteSupplyinvoiceById(Long id);
    SupplyinvoiceDto getSupplyinvoiceById(Long id);
    List<SupplyinvoiceDto> getListofSupplyinvoice(FilterRequest filterRequest);
    PageofSupplyinvoiceDto getPageofSupplyinvoice(FilterRequest filterRequest);
    Boolean isSupplyinvoiceExistWith(Long id);


    /************************************************************************
     * Converting OffsetDateTime To LocalDateTime
     * public LocalDateTime convertToLocalDateTime() {
     *    OffsetDateTime offsetDateTime = OffsetDateTime.now();
     *    System.out.println(offsetDateTime);
     *    LocalDateTime localDateTime = offsetDateTime.toLocalDateTime();
     *    System.out.println("localDateTime = " + localDateTime);
     *    return  localDateTime;
     * }
     *
     *
     * Converting OffsetDateTime To LocalDate
     * public LocalDate convertToLocalDate() {
     *    OffsetDateTime offsetDateTime = OffsetDateTime.now();
     *    System.out.println(offsetDateTime);
     *    LocalDate localDate=offsetDateTime.toLocalDate();
     *    System.out.println(localDate);
     *    return  localDate;
     * }
     *
     *
     * Converting OffsetDateTime To LocalTime
     * public LocalTime convertToLocalTime() {
     *    OffsetDateTime offsetDateTime = OffsetDateTime.now();
     *    System.out.println(offsetDateTime);
     *    LocalTime localTime=offsetDateTime.toLocalTime();
     *    System.out.println(localTime);
     *    return  localTime;
     * }
     */
}
