package com.c2psi.bmv1.userbm.mapper;

import com.c2psi.bmv1.dto.UserbmDto;
import com.c2psi.bmv1.userbm.models.Userbm;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserbmMapper {
    @Mapping(source = "userPassword", target = "userRepassword")
    @ValueMappings({
            @ValueMapping(source = "Activated", target = "ACTIVATED"),
            @ValueMapping(source = "Deactivated", target = "DEACTIVATED"),
            @ValueMapping(source = "Connected", target = "CONNECTED"),
            @ValueMapping(source = "Disconnected", target = "DISCONNECTED")
    })
    UserbmDto entityToDto(Userbm userbm);
    @Mapping(source = "userState", target = "userState")
    @ValueMappings({
            @ValueMapping(target = "Activated", source = "ACTIVATED"),
            @ValueMapping(target = "Deactivated", source = "DEACTIVATED"),
            @ValueMapping(target = "Connected", source = "CONNECTED"),
            @ValueMapping(target = "Disconnected", source = "DISCONNECTED")
    })
    Userbm dtoToEntity(UserbmDto userbmDto);
    List<UserbmDto> entityToDto(List<Userbm> userbm);
    List<Userbm> dtoToEntity(List<UserbmDto> userbmDto);


}
