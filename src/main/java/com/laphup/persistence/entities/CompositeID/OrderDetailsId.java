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
    private UUID orderId;
    private UUID laptotId;
}
