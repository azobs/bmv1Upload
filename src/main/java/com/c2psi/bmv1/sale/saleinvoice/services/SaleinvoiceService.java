package com.c2psi.bmv1.sale.saleinvoice.services;

import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofSaleinvoiceDto;
import com.c2psi.bmv1.dto.SaleinvoiceDto;

import java.util.List;

public interface SaleinvoiceService {
    SaleinvoiceDto saveSaleinvoice(SaleinvoiceDto siDto);
    SaleinvoiceDto updateSaleinvoice(SaleinvoiceDto siDto);
    Boolean deleteSaleinvoiceById(Long id);
    SaleinvoiceDto getSaleinvoiceById(Long id);
    List<SaleinvoiceDto> getListofSaleinvoice(FilterRequest filterRequest);
    PageofSaleinvoiceDto getPageofSaleinvoice(FilterRequest filterRequest);
    Boolean isSaleinvoiceExistWith(Long id);


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
