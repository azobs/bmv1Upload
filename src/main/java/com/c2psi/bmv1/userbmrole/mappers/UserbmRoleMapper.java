package com.c2psi.bmv1.userbmrole.mappers;


import com.c2psi.bmv1.dto.UserbmRoleDto;
import com.c2psi.bmv1.userbmrole.models.UserbmRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "spring")
public interface UserbmRoleMapper {

    @Mapping(source = "userbm.id", target = "userbmId")
    @Mapping(source = "role.id", target = "roleId")
    UserbmRoleDto entityToDto(UserbmRole userbmRole);

    UserbmRole dtoToEntity(UserbmRoleDto userbmRoleDto);
    List<UserbmRoleDto> entityToDto(List<UserbmRole> userbmRole);
    List<UserbmRole> dtoToEntity(List<UserbmRoleDto> userbmRoleDto);

//    @Named("userbmIdToUserbm")
//    static Userbm userbmIdToUserbm(Long userbmId){
//        return new MapUserbm().userbmIdToUserbm(userbmId);
//    }
//
//    @Named("roleIdToRole")
//    static Role roleIdToRole(Long roleId){
//        return MapRole.roleIdToRole(roleId);
//    }
}
