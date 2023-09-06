package com.c2psi.bmv1.sale.delivery.delivery.services;

import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofDeliveryDto;
import com.c2psi.bmv1.dto.DeliveryDto;

import java.util.List;

public interface DeliveryService {
    DeliveryDto saveDelivery(DeliveryDto siDto);
    DeliveryDto updateDelivery(DeliveryDto siDto);
    Boolean deleteDeliveryById(Long id);
    DeliveryDto getDeliveryById(Long id);
    List<DeliveryDto> getListofDelivery(FilterRequest filterRequest);
    PageofDeliveryDto getPageofDelivery(FilterRequest filterRequest);
    Boolean isDeliveryExistWith(Long id);
}
