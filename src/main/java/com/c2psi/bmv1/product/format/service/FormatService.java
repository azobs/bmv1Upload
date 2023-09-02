package com.c2psi.bmv1.product.format.service;

import com.c2psi.bmv1.dto.FormatDto;
import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofFormatDto;

import java.util.List;

public interface FormatService {
    FormatDto saveFormat(FormatDto formatDto);
    FormatDto updateFormat(FormatDto formatDto);
    Boolean deleteFormatById(Long id);
    FormatDto getFormatById(Long id);
    List<FormatDto> getListofFormat(FilterRequest filterRequest);
    PageofFormatDto getPageofFormat(FilterRequest filterRequest);
    Boolean isFormatExistWith(Long id);
}
