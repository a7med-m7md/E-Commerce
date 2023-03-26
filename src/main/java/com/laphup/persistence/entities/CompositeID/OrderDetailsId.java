package com.laphup.persistence.entities.CompositeID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Setter
@Getter
public class OrderDetailsId implements Serializable {
    private UUID orderUuid;
    private UUID uuidLaptop;

    public OrderDetailsId() {
    }

    public OrderDetailsId(UUID orderUuid, UUID uuidLaptop) {
        this.orderUuid = orderUuid;
        this.uuidLaptop = uuidLaptop;
    }
}
