package com.laphup.persistence.repositoryImp;

import com.laphup.persistence.entities.LaptopImage;
//import com.laphup.persistence.repository.BaseDao;
import com.laphup.persistence.entities.Laptop;
import com.laphup.persistence.repository.BaseRepo;
import com.laphup.util.enums.Rate;
import com.laphup.util.enums.SortBy;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LaptopRepoImp extends BaseRepo<Laptop, UUID, String> {
//public class LaptopRepoImp implements LaptopRepo{
    private HttpServletRequest request;
    public LaptopRepoImp(HttpServletRequest request){
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
        //laptop.setImagPath(Constants.MAIN_DIR + "/webapps/store/img/laptops/dar.png");
        laptops.add(laptop);
        return laptops;
    }

    public void saveImages(LaptopImage laptopImage){
        BaseRepo<LaptopImage, UUID, String> laptopImageUUIDBaseRepo = new BaseRepo<>(request);
        laptopImageUUIDBaseRepo.save(laptopImage);
    }

//    public void save(LaptopDTO laptopDTO) {
//
//    }

//    @Override
//    public List<Laptop> getAll(HttpServletRequest requestServlet, int pageNumber, int count, String laptopCategory, SortBy sortedBy, double minPrice, double maxPrice) {
//        return null;
//    }
//
//    @Override
//    public void create(HttpServletRequest requestServlet, LaptopDTO laptop) {
//        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();
//        entityManager.persist(laptop);
//        transaction.commit();
//    }
}
