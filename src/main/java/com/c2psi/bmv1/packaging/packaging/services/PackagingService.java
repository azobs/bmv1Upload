package com.c2psi.bmv1.packaging.packaging.services;

import com.c2psi.bmv1.dto.PackagingDto;
import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofPackagingDto;

import java.util.List;

public interface PackagingService {
    PackagingDto savePackaging(PackagingDto packagingDto);
    PackagingDto updatePackaging(PackagingDto packagingDto);
    Boolean deletePackagingById(Long id);
    PackagingDto getPackagingById(Long id);
    List<PackagingDto> getListofPackaging(FilterRequest filterRequest);
    PageofPackagingDto getPageofPackaging(FilterRequest filterRequest);
    Boolean isPackagingExistWith(Long id);
}
