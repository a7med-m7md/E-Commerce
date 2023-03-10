package com.laphup.persistence.entities.CompositeID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class OrderDetailsId implements Serializable {
    @Column(name = "uuid")
    private UUID orderUUID;
    @Column(name = "uuid")
    private UUID productUUID;
}
