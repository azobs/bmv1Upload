package com.c2psi.bmv1.sale.command.controllers;

import com.c2psi.bmv1.api.CommandApi;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.dto.CommandDto;
import com.c2psi.bmv1.sale.command.services.CommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CommandController implements CommandApi {
    final CommandService commandService;

    @Override
    public ResponseEntity<Boolean> deleteCommandById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = commandService.deleteCommandById(id);
        log.info("Command deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Command deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CommandDto> getCommandById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        CommandDto commandDto = commandService.getCommandById(id);
        log.info("Command found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Command found successfully");
        map.put("data", commandDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CommandDto>> getCommandList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<CommandDto> commandDtoList = commandService.getListofCommand(filterRequest);
        log.info("Command list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Command list found successfully");
        map.put("data", commandDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofCommandDto> getCommandPage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofCommandDto pageofCommandDto = commandService.getPageofCommand(filterRequest);
        log.info("Command page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Command page found successfully");
        map.put("data", pageofCommandDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CommandDto> saveCommand(CommandDto commandDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        CommandDto commandDtoSaved = commandService.saveCommand(commandDto);
        log.info("Entity Command saved successfully {}", commandDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Command created successfully");
        map.put("data", commandDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CommandDto> updateCommand(CommandDto commandDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        CommandDto commandDtoUpdated = commandService.updateCommand(commandDto);
        log.info("Entity Command updated successfully {}", commandDtoUpdated);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Command updated successfully");
        map.put("data", commandDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
