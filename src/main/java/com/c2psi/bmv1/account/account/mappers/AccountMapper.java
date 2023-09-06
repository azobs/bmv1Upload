package com.c2psi.bmv1.account.account.mappers;

import com.c2psi.bmv1.account.account.models.Account;
import com.c2psi.bmv1.dto.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @ValueMappings({
            @ValueMapping(source = "Client", target = "CLIENT"),
            @ValueMapping(source = "Pos", target = "POS"),
            @ValueMapping(source = "Provider", target = "PROVIDER")
    })
    @Mapping(source = "accountClient.id", target = "accountClientId")
    @Mapping(source = "accountPos.id", target = "accountPosId")
    @Mapping(source = "accountProvider.id", target = "accountProviderId")
    @Mapping(source = "accountArticle.id", target = "accountArticleId")
    @Mapping(source = "accountPackaging.id", target = "accountPackagingId")
    AccountDto entityToDto(Account account);
    List<AccountDto> entityToDto(List<Account> account);
    @ValueMappings({
            @ValueMapping(source = "CLIENT", target = "Client"),
            @ValueMapping(source = "POS", target = "Pos"),
            @ValueMapping(source = "PROVIDER", target = "Provider")
    })
    Account dtoToEntity(AccountDto accountDto);
    List<Account> dtoToEntity(List<AccountDto> accountDto);
}
