package com.laphup.controller;

import com.laphup.controller.utility.JSPages;
import com.laphup.dtos.LaptopDTO;
import com.laphup.persistence.entities.LaptopCategory;
import com.laphup.service.CategoryService;
import com.laphup.service.LaptopService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

@WebServlet(name = "uploadServlet", value = "/updateProduct")
public class UpdateLaptopServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productUUID = request.getParameter("uuid");
        LaptopService service = new LaptopService(request);
        LaptopDTO laptopDTO = service.getLaptop(UUID.fromString(productUUID));
        request.setAttribute("item", laptopDTO);
        System.out.println(laptopDTO);
        JSPages.UPDATE_PRODUCT.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = getPartAsString(request.getPart("uuid"));
        String laptopName = getPartAsString(request.getPart("laptop-name"));
        Double laptopPrice = Double.parseDouble(getPartAsString(request.getPart("laptop-price")));
        String laptopDescription = getPartAsString(request.getPart("laptop-description"));
        Integer laptopQuantity = Integer.parseInt(getPartAsString(request.getPart("laptop-quantity")));
        String laptopCategory = getPartAsString(request.getPart("category"));

        LaptopService laptopService = new LaptopService(request);
        CategoryService categoryService = new CategoryService(request);

        LaptopCategory category = categoryService.getCategoryName(laptopName);

        LaptopDTO laptop = laptopService.getLaptop(UUID.fromString(uuid));

        laptop.setName(laptopName);
        laptop.setPrice(laptopPrice);
        laptop.setDescription(laptopDescription);
        laptop.setQuantities(laptopQuantity);
//        laptop.setLaptopCategory();


    }


    private String getPartAsString(Part part) {
        try (BufferedReader val = new BufferedReader(new InputStreamReader(part.getInputStream()))) {
            return val.readLine();
        } catch (IOException ex) {
        }
        return null;
    }
}
