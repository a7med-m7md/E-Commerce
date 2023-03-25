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
public class CardId implements Serializable {
    @Column
    private UUID userId;
    @Column
    private UUID laptopId;

    public CardId(UUID userId, UUID laptopId) {
        this.userId = userId;
        this.laptopId = laptopId;
    }

    public CardId() {

    }
}
