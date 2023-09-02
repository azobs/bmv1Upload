package com.c2psi.bmv1.inventory.inventory.services;

import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofInventoryDto;
import com.c2psi.bmv1.dto.InventoryDto;

import java.util.List;

public interface InventoryService {
    InventoryDto saveInventory(InventoryDto inventoryDto);
    InventoryDto updateInventory(InventoryDto inventoryDto);
    Boolean deleteInventoryById(Long id);
    InventoryDto getInventoryById(Long id);
    List<InventoryDto> getListofInventory(FilterRequest filterRequest);
    PageofInventoryDto getPageofInventory(FilterRequest filterRequest);
    Boolean isInventoryExistWith(Long id);
}
