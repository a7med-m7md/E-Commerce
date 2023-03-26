package com.laphup.dtos;

import java.util.UUID;

public class OrderDetailsIdDTO {
    private UUID orderUuid;
    private UUID uuidLaptop;

    public OrderDetailsIdDTO() {
    }

    public OrderDetailsIdDTO(UUID orderUuid, UUID uuidLaptop) {
        this.orderUuid = orderUuid;
        this.uuidLaptop = uuidLaptop;
    }
}
