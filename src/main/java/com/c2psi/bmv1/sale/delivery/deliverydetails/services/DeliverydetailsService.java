package com.c2psi.bmv1.sale.delivery.deliverydetails.services;


import com.c2psi.bmv1.dto.DeliverydetailsDto;
import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofDeliverydetailsDto;

import java.util.List;

public interface DeliverydetailsService {
    DeliverydetailsDto saveDeliverydetails(DeliverydetailsDto ddDto);
    DeliverydetailsDto updateDeliverydetails(DeliverydetailsDto ddDto);
    Boolean deleteDeliverydetailsById(Long id);
    DeliverydetailsDto getDeliverydetailsById(Long id);
    List<DeliverydetailsDto> getListofDeliverydetails(FilterRequest filterRequest);
    PageofDeliverydetailsDto getPageofDeliverydetails(FilterRequest filterRequest);
    Boolean isDeliverydetailsExistWith(Long id);
}
