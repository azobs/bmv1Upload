package com.c2psi.bmv1.userbm.services;

import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.userbm.models.Userbm;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface UserbmService {
    UserbmDto saveUserbm(UserbmDto userbmDto);
    UserbmDto updateUserbm(UserbmDto userbmDto);
    Boolean deleteUserbmById(Long id);
    UserbmDto getUserbmById(Long id);
    UserbmDto getUserbmByCni(String userCni);
    UserbmDto getUserbmByLogin(String userLogin);
    UserbmDto getUserbmByEmail(String userEmail);
     List<UserbmDto> getListofUserbm(FilterRequest filterRequest);
     PageofUserbmDto getPageofUserbm(FilterRequest filterRequest);

}
