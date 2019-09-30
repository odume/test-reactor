package com.emasphere.testreactor.model;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class MovementKey {

    private int injectionNumber;
    private String displayMonth;
    private String account;
}
