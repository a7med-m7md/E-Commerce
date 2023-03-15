package com.laphup.controller;

import com.laphup.persistence.entities.Laptop;
import com.laphup.persistence.entities.LaptopImage;
import com.laphup.service.LaptopService;
import com.laphup.util.enums.ImgaeType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@WebServlet(name = "addLaptopServlet", value = "/addLaptop")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10
)
public class AddLaptopServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.getWriter().write("Hello add laptop");
        request.getRequestDispatcher("add-new-post.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String laptopName = getPartAsString(request.getPart("laptop-name"));
        Double laptopPrice = Double.parseDouble(getPartAsString(request.getPart("laptop-price")));
        String laptopDescription = getPartAsString(request.getPart("laptop-description"));
        Integer laptopQuantity = Integer.parseInt(getPartAsString(request.getPart("laptop-quantity")));
//        String laptopCategory = request.getParameter("laptop-category");
        LaptopService laptopService = new LaptopService(request);
//        LaptopDTO laptopDTO = new LaptopDTO().
//        UUID uuid = UUID.randomUUID();
        Part filePart = request.getPart("product-image");
        InputStream fileContent = filePart.getInputStream();
        String  fileName =filePart.getSubmittedFileName();
        // Save
        String uploadPath = getServletContext().getRealPath("/") + "uploads/";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()){
            uploadDir.mkdir();
        }
        File file = new File(uploadDir, fileName);
        try (OutputStream outputStream= new FileOutputStream(file)){
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = fileContent.read(buffer)) != -1){
                outputStream.write(buffer, 0, bytesRead);
            }
        }


        Laptop laptop = new Laptop();
//        laptop.setUuidLaptop(uuid);
        laptop.setName(laptopName);
        laptop.setPrice(laptopPrice);
        laptop.setDescription(laptopDescription);
        laptop.setQuantities(laptopQuantity);
        Set<LaptopImage> images = new HashSet<>();
//        images.add(laptopImage);
        laptop.setLaptopImage(images);
        laptopService.addLaptop(laptop);
        System.out.println("UUID:: " + laptop.getUuidLaptop());
        LaptopImage laptopImage = new LaptopImage();
        laptopImage.setImagPath(file.getPath());
        laptopImage.setImgaeType(ImgaeType.PRODUCT_IMAGE);
        laptopImage.setLaptop(laptop);
        laptopService.saveImage(laptopImage);

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private String getPartAsString(Part part){
        try(BufferedReader val = new BufferedReader(new InputStreamReader(part.getInputStream()))){
            return val.readLine();
        }catch (IOException ex){}
        return null;
    }
}
