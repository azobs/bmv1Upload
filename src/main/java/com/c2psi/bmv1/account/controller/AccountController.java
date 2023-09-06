package com.c2psi.bmv1.account.controller;

import com.c2psi.bmv1.account.account.services.AccountService;
import com.c2psi.bmv1.account.accountoperation.models.AccountOperation;
import com.c2psi.bmv1.account.accountoperation.services.AccountOperationService;
import com.c2psi.bmv1.account.accountoperation.services.CashOperationService;
import com.c2psi.bmv1.api.AccountApi;
import com.c2psi.bmv1.dto.*;
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
public class AccountController implements AccountApi {
    final AccountService accountService;
    final AccountOperationService accountOperationService;
    final CashOperationService cashOperationService;

    @Override
    public ResponseEntity<Boolean> deleteAccountById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = accountService.deleteAccountById(id);
        log.info("Account deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Account deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteAccountOperationById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = accountOperationService.deleteAccountOperationById(id);
        log.info("Account operation deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Account operation deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteCashOperationById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = cashOperationService.deleteCashOperationById(id);
        log.info("Cash operation deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Cash operation deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AccountDto> getAccountById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        AccountDto accountDto = accountService.getAccountById(id);
        log.info("Account found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Account found successfully");
        map.put("data", accountDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AccountDto>> getAccountList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<AccountDto> accountDtoList = accountService.getListofAccount(filterRequest);
        log.info("Account list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Account list found successfully");
        map.put("data", accountDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AccountOperationDto> getAccountOperationById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        AccountOperationDto accountOperationDto = accountOperationService.getAccountOperationById(id);
        log.info("AccountOperation found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "AccountOperation found successfully");
        map.put("data", accountOperationDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AccountOperationDto>> getAccountOperationList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<AccountOperationDto> accountOperationDtoList = accountOperationService.getListofAccountOperation(filterRequest);
        log.info("AccountOperation list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "AccountOperation list found successfully");
        map.put("data", accountOperationDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofAccountOperationDto> getAccountOperationPage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofAccountOperationDto pageofAccountOperationDto = accountOperationService.getPageofAccountOperation(filterRequest);
        log.info("Account page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "AccountOperation page found successfully");
        map.put("data", pageofAccountOperationDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofAccountDto> getAccountPage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofAccountDto pageofAccountDto = accountService.getPageofAccount(filterRequest);
        log.info("Account page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Account page found successfully");
        map.put("data", pageofAccountDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CashOperationDto> getCashOperationById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        CashOperationDto cashOperationDto = cashOperationService.getCashOperationById(id);
        log.info("CashOperation found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "CashOperation found successfully");
        map.put("data", cashOperationDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CashOperationDto>> getCashOperationList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<CashOperationDto> cashOperationDtoList = cashOperationService.getListofCashOperation(filterRequest);
        log.info("CashOperation list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "CashOperation list found successfully");
        map.put("data", cashOperationDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofCashOperationDto> getCashOperationPage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofCashOperationDto pageofCashOperationDto = cashOperationService.getPageofCashOperation(filterRequest);
        log.info("Account page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "CashOperation page found successfully");
        map.put("data", pageofCashOperationDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AccountDto> saveAccount(AccountDto accountDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        AccountDto accountDtoSaved = accountService.saveAccount(accountDto);
        log.info("Entity Account saved successfully {}", accountDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Account created successfully");
        map.put("data", accountDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AccountOperationDto> saveAccountOperation(AccountOperationDto accountOperationDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        AccountOperationDto accountOperationDtoSaved = accountOperationService.saveAccountOperation(accountOperationDto);
        log.info("Entity AccountOperation saved successfully {}", accountOperationDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "AccountOperation created successfully");
        map.put("data", accountOperationDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CashOperationDto> saveCashOperation(CashOperationDto cashOperationDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        CashOperationDto cashOperationDtoSaved = cashOperationService.saveCashOperation(cashOperationDto);
        log.info("Entity CashOperation saved successfully {}", cashOperationDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "CashOperation created successfully");
        map.put("data", cashOperationDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AccountDto> updateAccount(AccountDto accountDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        AccountDto accountDtoUpdated = accountService.updateAccount(accountDto);
        log.info("Entity Account updated successfully {}", accountDtoUpdated);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Account updated successfully");
        map.put("data", accountDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AccountOperationDto> updateAccountOperation(AccountOperationDto accountOperationDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        AccountOperationDto accountOperationDtoUpdated = accountOperationService.updateAccountOperation(accountOperationDto);
        log.info("Entity AccountOperation updated successfully {}", accountOperationDtoUpdated);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "AccountOperation updated successfully");
        map.put("data", accountOperationDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CashOperationDto> updateCashOperation(CashOperationDto cashOperationDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        CashOperationDto cashOperationDtoUpdated = cashOperationService.updateCashOperation(cashOperationDto);
        log.info("Entity CashOperation updated successfully {}", cashOperationDtoUpdated);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "CashOperation updated successfully");
        map.put("data", cashOperationDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
