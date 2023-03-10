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
    @JoinColumn(name = "uuid")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uuid")
    private Laptop laptop;
    private int quantity;
}