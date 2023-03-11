package com.laphup.persistence.entities;

import com.laphup.persistence.entities.CompositeID.CardId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Card {
    @EmbeddedId
    private CardId cardId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userUuid")
    private User user;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uuidLaptop")
    private Laptop laptop;
    private int quantity;
}
