package com.c2psi.bmv1.userbm.service;

import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofUserbmDto;
import com.c2psi.bmv1.dto.UserbmDto;

import java.util.List;

public interface UserbmService {
    UserbmDto saveUserbm(UserbmDto userbmDto);
    UserbmDto updateUserbm(UserbmDto userbmDto);
    Boolean deleteUserbmById(Long id);
    UserbmDto getUserbmById(Long id);
    UserbmDto getUserbmByCni(String userCni);
    UserbmDto getUserbmByLogin(String userLogin);
    UserbmDto getUserbmByEmail(String userEmail);
    List<UserbmDto> getUserbmList(FilterRequest filterRequest);
//    Page<UserbmDto> getUserbmPage(FilterRequest filterRequest);
    PageofUserbmDto getUserbmPage(FilterRequest filterRequest);

}
