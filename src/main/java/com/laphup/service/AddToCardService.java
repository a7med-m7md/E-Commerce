package com.laphup.service;

import com.laphup.dtos.LaptopDTO;
import com.laphup.persistence.entities.Card;
import com.laphup.persistence.entities.Laptop;
import com.laphup.persistence.repositoryImp.CardRepo;
import com.laphup.persistence.repositoryImp.LaptopRepoImp;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;
import java.util.UUID;

public class AddToCardService {

    private LaptopRepoImp laptopRepoImp;

    private CardRepo cardRepo;
    public AddToCardService(HttpServletRequest request){
        laptopRepoImp = new LaptopRepoImp(request);
    }
    public LaptopDTO getLaptopByUuid(UUID uuid) {
        LaptopDTO laptop = laptopRepoImp.getLaptopByName(uuid);
       return  laptop;
    }
    public Laptop getLaptopByUuid2(UUID uuid) {
        Laptop laptop = laptopRepoImp.getById(uuid);
        return  laptop;
    }

    public boolean addToCard(Card card) {
        Card card1 = cardRepo.addToCard(card);
        if (card1 != null)
            return  true;
        return  false;
    }
}
