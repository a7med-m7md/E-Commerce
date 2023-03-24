package com.laphup.persistence.repositoryImp;

import com.laphup.persistence.entities.Card;
import com.laphup.persistence.repository.BaseRepo;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;

public class CardRepo extends BaseRepo {
    private EntityManager entityManager;
    public CardRepo(HttpServletRequest request) {
        super(request);
        entityManager = (EntityManager) request.getAttribute("EntityManager");
    }

    public Card addToCard (Card card) {
       Card card1 = (Card) save(card);
       return card1;
    }
}
