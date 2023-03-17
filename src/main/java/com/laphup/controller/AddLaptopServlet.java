package com.laphup.controller;

import com.laphup.persistence.entities.Laptop;
import com.laphup.persistence.entities.LaptopCategory;
import com.laphup.persistence.entities.LaptopImage;
import com.laphup.service.CategoryService;
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
import java.util.*;
import java.util.stream.Collectors;

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
        System.out.println("Hello");
        request.getRequestDispatcher("add-laptop.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String laptopName = getPartAsString(request.getPart("laptop-name"));
        Double laptopPrice = Double.parseDouble(getPartAsString(request.getPart("laptop-price")));
        String laptopDescription = getPartAsString(request.getPart("laptop-description"));
        Integer laptopQuantity = Integer.parseInt(getPartAsString(request.getPart("laptop-quantity")));
        String laptopCategory = getPartAsString(request.getPart("category"));
        System.out.println(laptopCategory + " category");
        Collection<Part> allParts = request.getParts();
        List<Part> additionalImagesParts = allParts.stream().filter(part -> part.getName().equals("additional-photos[]")).collect(Collectors.toList());

        LaptopService laptopService = new LaptopService(request);

        Part filePart = request.getPart("product-image");
        InputStream fileContent = filePart.getInputStream();
        String fileName = filePart.getSubmittedFileName();
        String uploadPath = getServletContext().getRealPath("/") + "uploads/";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        File file = new File(uploadDir, fileName);
        saveImage(file, fileContent);

        Laptop laptop = new Laptop();
        laptop.setName(laptopName);
        laptop.setPrice(laptopPrice);
        laptop.setDescription(laptopDescription);
        laptop.setQuantities(laptopQuantity);

        CategoryService categoryService = new CategoryService(request);
        LaptopCategory category = categoryService.getCategoryName(laptopCategory);
        laptop.setLaptopCategory(category);

        Set<LaptopImage> images = new HashSet<>();

        createImage(ImgaeType.PRODUCT_IMAGE, file, laptop, images, laptopService);

        // Iterate over additional images and create LaptopImage objects for each
        for (Part part : additionalImagesParts) {
            InputStream additionalImageContent = part.getInputStream();
            String additionalImageName = part.getSubmittedFileName();
            File additionalImageFile = new File(uploadDir, additionalImageName);
            saveImage(additionalImageFile, additionalImageContent);
            createImage(ImgaeType.OTHER, additionalImageFile, laptop, images, laptopService);
        }
        images.forEach(image-> laptopService.saveImage(image));

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
    private String getPartAsString(Part part) {
        try (BufferedReader val = new BufferedReader(new InputStreamReader(part.getInputStream()))) {
            return val.readLine();
        } catch (IOException ex) {
        }
        return null;
    }

    private void saveImage(File file, InputStream fileContent){
        try (OutputStream outputStream = new FileOutputStream(file)) {
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = fileContent.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void createImage(ImgaeType type, File file, Laptop laptop, Set<LaptopImage> images, LaptopService laptopService){
        LaptopImage laptopImage = new LaptopImage();
        laptopImage.setImagPath(file.getPath());
        laptopImage.setImgaeType(ImgaeType.PRODUCT_IMAGE);
        laptopImage.setLaptop(laptop);
        images.add(laptopImage);
        laptopService.addLaptop(laptop);

    }

}
