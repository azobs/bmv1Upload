package com.c2psi.bmv1.inventory.inventory.mappers;

import com.c2psi.bmv1.dto.InventoryDto;
import com.c2psi.bmv1.inventory.inventory.models.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
    @Mapping(source = "invPos.id", target = "invPosId")
    InventoryDto entityToDto(Inventory inventory);
    List<InventoryDto> entityToDto(List<Inventory> inventory);
    Inventory dtoToEntity(InventoryDto inventoryDto);
    List<Inventory> dtoToEntity(List<InventoryDto> inventoryDto);
}
