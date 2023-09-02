package com.c2psi.bmv1.userbm.services;

import com.c2psi.bmv1.dto.*;

import java.util.List;

public interface UserbmService {
    UserbmDto saveUserbm(UserbmDto userbmDto);
    UserbmDto updateUserbm(UserbmDto userbmDto);
    UserbmDto updateUserbmState(Long userbmId, UserbmDto.UserStateEnum userStateEnum);
    Boolean deleteUserbmById(Long id);
    UserbmDto getUserbmById(Long id);
    UserbmDto getUserbmByCni(String userCni);
    UserbmDto getUserbmByLogin(String userLogin);
    UserbmDto getUserbmByEmail(String userEmail);
     List<UserbmDto> getListofUserbm(FilterRequest filterRequest);
     PageofUserbmDto getPageofUserbm(FilterRequest filterRequest);

     Boolean isUserbmExistWithId(Long userbmId);

     UserbmDto loadUserbmByUsername(String username);

}
