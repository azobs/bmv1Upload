package com.c2psi.bmv1.inventory.inventoryline.services;

import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofInventorylineDto;
import com.c2psi.bmv1.dto.InventorylineDto;

import java.util.List;

public interface InventorylineService {
    InventorylineDto saveInventoryline(InventorylineDto invlineDto);
    InventorylineDto updateInventoryline(InventorylineDto invlineDto);
    Boolean deleteInventorylineById(Long id);
    InventorylineDto getInventorylineById(Long id);
    List<InventorylineDto> getListofInventoryline(FilterRequest filterRequest);
    PageofInventorylineDto getPageofInventoryline(FilterRequest filterRequest);
    Boolean isInventorylineExistWith(Long id);
}
