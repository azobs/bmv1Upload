package com.c2psi.bmv1.auth.services;

import com.c2psi.bmv1.dto.AuthRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Service(value = "AuthValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class AuthValidator {
    // public List<String> validate(TokenDto tokenDto){
    public List<String> validate(AuthRequest request){
        List<String> errors  = new ArrayList<>();
        try{
            String login = request.getLogin();
            String password = request.getPassword();
        }
        catch (Exception e){
            errors.add("Attributes login or password is not available. Please check the object sent in the request");
        }
        return errors;
    }

    public List<String> validate(HttpServletRequest request){
        List<String> errors  = new ArrayList<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            //System.err.println("Header Name - " + headerName + ", Value - " + request.getHeader(headerName));
        }

        System.err.println("params == "+request.getParameterNames().hasMoreElements());
        while(request.getParameterNames().hasMoreElements()){
            //System.err.println("params.nextElement() "+ request.getHeaderNames().nextElement());
            String paramName = request.getParameterNames().nextElement();
            System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
        }

        return errors;
    }
}
