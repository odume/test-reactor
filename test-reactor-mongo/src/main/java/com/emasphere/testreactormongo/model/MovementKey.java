package com.emasphere.testreactormongo.model;

import lombok.Value;

@Value
public class MovementKey {

    private int injectionNumber;
    private String displayMonth;
    private String account;
}
