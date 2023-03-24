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
    public Optional<LaptopDTO> getLaptopByUuid(UUID uuid) {
        Optional<LaptopDTO> laptop = laptopRepoImp.getLaptopByName(uuid);
        if(laptop.isPresent())
            return laptop;
        else
            return Optional.empty();
    }

    public boolean addToCard(Card card) {
        Card card1 = cardRepo.addToCard(card);
        if (card1 != null)
            return  true;
        return  false;
    }
}
