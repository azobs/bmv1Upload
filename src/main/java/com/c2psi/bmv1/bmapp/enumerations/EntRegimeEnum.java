package com.c2psi.bmv1.bmapp.enumerations;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EntRegimeEnum {
    GRP("GROUPE"),
    IL("IMPOT LIBERATOIRE"),
    SA("SOCIETE ANONYME"),
    SARL("SOCIETE A RESPONSABILITE LIMITE"),
    SI("SIMPLIFIE");

    @Getter
    private final String entRegime;
}
