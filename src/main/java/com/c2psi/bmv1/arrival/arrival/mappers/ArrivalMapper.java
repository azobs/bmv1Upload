package com.c2psi.bmv1.arrival.arrival.mappers;

import com.c2psi.bmv1.arrival.arrival.models.Arrival;
import com.c2psi.bmv1.bmapp.enumerations.ArrivalNatureEnum;
import com.c2psi.bmv1.bmapp.enumerations.ArrivalTypeEnum;
import com.c2psi.bmv1.dto.ArrivalDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArrivalMapper {
    @ValueMappings({
            @ValueMapping(source = "STANDARD", target = "STANDARD"),
            @ValueMapping(source = "DIVERS", target = "DIVERS"),
            @ValueMapping(source = "CASH", target = "CASH"),
            @ValueMapping(source = "COVER", target = "COVER"),
            @ValueMapping(source = "DAMAGE", target = "DAMAGE")
    })
    @Mapping(source = "arrivalArticle.id", target = "arrivalArticleId")
    @Mapping(source = "arrivalSi.id", target = "arrivalInvoiceId")
    ArrivalDto entityToDto(Arrival arrival);
    List<ArrivalDto> entityToDto(List<Arrival> arrival);
    @ValueMappings({
            @ValueMapping(source = "STANDARD", target = "STANDARD"),
            @ValueMapping(source = "DIVERS", target = "DIVERS"),
            @ValueMapping(source = "CASH", target = "CASH"),
            @ValueMapping(source = "COVER", target = "COVER"),
            @ValueMapping(source = "DAMAGE", target = "DAMAGE")
    })
    Arrival dtoToEntity(ArrivalDto arrivalDto);
    List<Arrival> dtoToEntity(List<ArrivalDto> arrivalDto);
}
