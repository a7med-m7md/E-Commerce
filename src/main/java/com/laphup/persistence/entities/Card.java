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
    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userUuid")
    private User user;
    @MapsId("laptopId")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uuidLaptop")
    private Laptop laptop;
    private int quantity;

    public Card(CardId cardId, User user, Laptop laptop, int quantity) {
        this.cardId = cardId;
        this.user = user;
        this.laptop = laptop;
        this.quantity = quantity;
    }

    public Card() {

    }
}
