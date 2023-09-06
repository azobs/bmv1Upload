package com.c2psi.bmv1.sale.sale.mappers;

import com.c2psi.bmv1.dto.SaleDto;
import com.c2psi.bmv1.sale.sale.models.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SaleMapper {
    @ValueMappings({
            @ValueMapping(source = "Details", target = "DETAILS"),
            @ValueMapping(source = "Permutation", target = "PERMUTATION"),
            @ValueMapping(source = "Semiwhole", target = "SEMIWHOLE"),
            @ValueMapping(source = "Whole", target = "WHOLE")
    })
    @Mapping(source = "saleArticle.id", target = "saleArticleId")
    @Mapping(source = "saleCommand.id", target = "saleCommandId")
    SaleDto entityToDto(Sale sale);
    List<SaleDto> entityToDto(List<Sale> sale);
    @ValueMappings({
            @ValueMapping(target = "Details", source = "DETAILS"),
            @ValueMapping(target = "Permutation", source = "PERMUTATION"),
            @ValueMapping(target = "Semiwhole", source = "SEMIWHOLE"),
            @ValueMapping(target = "Whole", source = "WHOLE")
    })
    Sale dtoToEntity(SaleDto saleDto);
    List<Sale> dtoToEntity(List<SaleDto> saleDto);
}
