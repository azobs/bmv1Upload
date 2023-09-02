package com.c2psi.bmv1.product.pf.mapper;

import com.c2psi.bmv1.dto.ProductformatedDto;
import com.c2psi.bmv1.product.pf.models.Productformated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductformatedMapper {
    @Mapping(source = "pfFormat.id", target = "pfFormatId")
    @Mapping(source = "pfProduct.id", target = "pfProductId")
    ProductformatedDto entityToDto(Productformated productformated);
    List<ProductformatedDto> entityToDto(List<Productformated> productformated);
    Productformated dtoToEntity(ProductformatedDto productformatedDto);
    List<Productformated> dtoToEntity(List<ProductformatedDto> productformatedDto);
}
