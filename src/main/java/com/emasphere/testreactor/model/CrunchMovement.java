package com.emasphere.testreactor.model;

import lombok.Setter;
import lombok.Value;
import lombok.experimental.Accessors;
import lombok.experimental.NonFinal;

import java.math.BigDecimal;
import java.util.UUID;

@Value
@Accessors(chain = true)
public class CrunchMovement {

    private UUID id;
    @NonFinal @Setter()
    private BigDecimal amount;

    public CrunchMovement(MovementKey movementKey) {
        this.id = UUID.randomUUID();
        this.movementKey = movementKey;
        this.amount = BigDecimal.ZERO;
    }

    private MovementKey movementKey;



}
