package com.c2psi.bmv1.sale.command.services;

import com.c2psi.bmv1.dto.CommandDto;
import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofCommandDto;

import java.util.List;

public interface CommandService {
    CommandDto saveCommand(CommandDto cmdDto);
    CommandDto updateCommand(CommandDto cmdDto);
    Boolean deleteCommandById(Long id);
    CommandDto getCommandById(Long id);
    List<CommandDto> getListofCommand(FilterRequest filterRequest);
    PageofCommandDto getPageofCommand(FilterRequest filterRequest);
    Boolean isCommandExistWith(Long id);
}
