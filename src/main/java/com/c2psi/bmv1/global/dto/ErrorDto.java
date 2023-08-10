package com.c2psi.bmv1.global.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {
    private Integer httpCode;
    private String errorAppMessage;
    private String exceptionMessage;
    private List<String> errorList = new ArrayList<>();
}
