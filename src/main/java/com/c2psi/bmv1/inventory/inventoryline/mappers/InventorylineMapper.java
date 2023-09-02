package com.c2psi.bmv1.inventory.inventoryline.mappers;

import com.c2psi.bmv1.dto.InventorylineDto;
import com.c2psi.bmv1.inventory.inventoryline.models.Inventoryline;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventorylineMapper {
    @Mapping(source = "inventory.id", target = "inventoryId")
    @Mapping(source = "invlineArticle.id", target = "invlineArticleId")
    InventorylineDto entityToDto(Inventoryline inventoryline);
    List<InventorylineDto> entityToDto(List<Inventoryline> inventoryline);
    Inventoryline dtoToEntity(InventorylineDto inventorylineDto);
    List<Inventoryline> dtoToEntity(List<InventorylineDto> inventorylineDto);
}
