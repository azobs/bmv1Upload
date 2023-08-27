package com.c2psi.bmv1.auth.controllers;

import com.c2psi.bmv1.api.TestApi;
import com.c2psi.bmv1.dto.TestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController implements TestApi {
    @Override
    public ResponseEntity<TestDto> apiTest() {
        TestDto testDto = new TestDto();
        testDto.setTest("Hello from unsecure endpoint of the api");
        return  ResponseEntity.ok(testDto);
    }

    @Override
    public ResponseEntity<TestDto> apiTestSecure() {
        TestDto testDto = new TestDto();
        testDto.setTest("Hello from Secure endpoint of the api");
        return  ResponseEntity.ok(testDto);
    }
}
