package com.c2psi.bmv1.bmapp.enumerations;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RoleTypeEnum {
    ADMINBM ("Adminbm"),
    ADMINENTERPRISE ("AdminEnterprise"),
    EMPLOYE ("Employe");

    @Getter
    private final String roleType;
}
