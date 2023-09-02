package com.c2psi.bmv1.loading.packagingdetails.services;

import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PackagingdetailsDto;
import com.c2psi.bmv1.dto.PageofPackagingdetailsDto;

import java.util.List;

public interface PackagingdetailsService {
    PackagingdetailsDto savePackagingdetails(PackagingdetailsDto packagingdetailsDto);
    PackagingdetailsDto updatePackagingdetails(PackagingdetailsDto packagingdetailsDto);
    Boolean deletePackagingdetailsById(Long id);
    PackagingdetailsDto getPackagingdetailsById(Long id);
    List<PackagingdetailsDto> getListofPackagingdetails(FilterRequest filterRequest);
    PageofPackagingdetailsDto getPageofPackagingdetails(FilterRequest filterRequest);
    Boolean isPackagingdetailsExistWith(Long id);
}
