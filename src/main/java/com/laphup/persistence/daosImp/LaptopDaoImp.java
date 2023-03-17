package com.laphup.persistence.daosImp;

import com.laphup.Listeners.RequestServlet;
import com.laphup.persistence.entities.Laptop;
import com.laphup.persistence.entities.LaptopCategory;
import com.laphup.persistence.repository.BaseDao;
import com.laphup.util.Constants;
import com.laphup.util.enums.Rate;
import com.laphup.util.enums.SortBy;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LaptopDaoImp extends BaseDao<LaptopCategory, UUID, String> {
    private HttpServletRequest request;
    public LaptopDaoImp(HttpServletRequest request){
        super(request);
        this.request = request;
    }

    public List<Laptop> getAll(int pageNumber, int count, String laptopCategory, SortBy sortedBy, double minPrice, double maxPrice) {
        List<Laptop> laptops = new ArrayList<>();

        Laptop laptop = new Laptop();
        laptop.setName("Hp Envi");
        laptop.setPrice(20000);
        laptop.setRate(Rate.FIVE);
        laptop.setUuidLaptop(UUID.randomUUID());
//        laptop.setLaptopCategory(new LaptopCategory());
//        laptop.setPurchasingCount(5);
        //laptop.setImagPath(Constants.MAIN_DIR + "/webapps/store/img/laptops/dar.png");
        laptops.add(laptop);
        return laptops;
    }
}