package com.laphup.controller;

import com.google.auth.ServiceAccountSigner;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.cloud.storage.Blob;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.laphup.controller.utility.JSPages;
import com.laphup.persistence.entities.Laptop;
import com.laphup.persistence.entities.LaptopCategory;
import com.laphup.persistence.entities.LaptopImage;
import com.laphup.service.CategoryService;
import com.laphup.service.ImageService;
import com.laphup.service.LaptopService;
import com.laphup.util.enums.ImgaeType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@WebServlet(name = "addLaptopServlet", value = "/addLaptop")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10
)
public class AddLaptopServlet extends HttpServlet {
    FirebaseApp firebaseApp;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.getWriter().write("Hello add laptop");
        System.out.println("Hello");
        request.getRequestDispatcher("add-laptop.jsp").forward(request, response);
//        saveImageInFireBase();
        JSPages.ADD_LAPTOP.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String laptopName = getPartAsString(request.getPart("laptop-name"));
        Double laptopPrice = Double.parseDouble(getPartAsNum(request.getPart("laptop-price")));
        String laptopDescription = getPartAsString(request.getPart("laptop-description"));
        Integer laptopQuantity = Integer.parseInt(getPartAsNum(request.getPart("laptop-quantity")));
        String laptopCategory = getPartAsNum(request.getPart("category"));
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
        String path = saveImage(file, fileContent);

        Laptop laptop = new Laptop();
        laptop.setName(laptopName);
        laptop.setPrice(laptopPrice);
        laptop.setDescription(laptopDescription);
        laptop.setQuantities(laptopQuantity);

        CategoryService categoryService = new CategoryService(request);
        LaptopCategory category = categoryService.getCategoryName(laptopCategory);
        laptop.setLaptopCategory(category);

        Set<LaptopImage> images = new HashSet<>();

        createImage(ImgaeType.PRODUCT_IMAGE, path, laptop, images, laptopService);

        // Iterate over additional images and create LaptopImage objects for each
        for (Part part : additionalImagesParts) {
            InputStream additionalImageContent = part.getInputStream();
            String additionalImageName = part.getSubmittedFileName();
            File additionalImageFile = new File(uploadDir, additionalImageName);
            String secondaryImgPath = saveImage(additionalImageFile, additionalImageContent);
            createImage(ImgaeType.OTHER, secondaryImgPath, laptop, images, laptopService);
        }

        ImageService imageService = new ImageService(request);

//        images.forEach(image -> laptopService.saveImage(image));

        images.forEach(image -> imageService.saveImage(image));
        images.forEach(image ->{
            System.out.println("======");
            System.out.println(image.getImgaeType());
        });
//        request.getRequestDispatcher("index.jsp").forward(request, response);
        JSPages.HOME_PAGE.forward(request, response);
    }


    private String getPartAsNum(Part part) {
        try (BufferedReader val = new BufferedReader(new InputStreamReader(part.getInputStream()))) {
            return val.readLine();
        } catch (IOException ex) {
        }
        return null;
    }

    private String getPartAsString(Part part) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(part.getInputStream()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator()); // Add line separator after each line
            }
        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
            return null;
        }
        return stringBuilder.toString();
    }


//    private void saveImage(File file, InputStream fileContent) {
//        try (OutputStream outputStream = new FileOutputStream(file)) {
//            byte[] buffer = new byte[4096];
//            int bytesRead = -1;
//            while ((bytesRead = fileContent.read(buffer)) != -1) {
//                outputStream.write(buffer, 0, bytesRead);
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }


    private String saveImage(File file, InputStream fileContent) throws IOException {

        // Convert the InputStream to a byte array
        byte[] byteArray = inputStreamToByteArray(fileContent);

        System.out.println("Byte array length: " + byteArray.length);

        return saveImageInFireBase(byteArray, file.getName());
        // Print the length of the byte array

    }


//    private void createImage(ImgaeType type, File file, Laptop laptop, Set<LaptopImage> images, LaptopService laptopService) {
//        LaptopImage laptopImage = new LaptopImage();
//        laptopImage.setImagPath(file.getPath());
//        laptopImage.setImgaeType(ImgaeType.PRODUCT_IMAGE);
//        laptopImage.setLaptop(laptop);
//        images.add(laptopImage);
//        laptopService.addLaptop(laptop);
//
//    }


    private void createImage(ImgaeType type, String path, Laptop laptop, Set<LaptopImage> images, LaptopService laptopService) {
        LaptopImage laptopImage = new LaptopImage();
        laptopImage.setImagPath(path);
        laptopImage.setImgaeType(type);
        laptopImage.setLaptop(laptop);
        images.add(laptopImage);
        laptopService.addLaptop(laptop);

    }



    private String saveImageInFireBase(byte[] byteArray, String name) throws IOException {

        GoogleCredentials credentials = GoogleCredentials.fromStream(getClass().getResourceAsStream("/credientails.json"));
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .setStorageBucket("laphub-410fc.appspot.com")
                .build();

        if(firebaseApp == null)
            firebaseApp = FirebaseApp.initializeApp(options);

        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

        Bucket bucket = StorageClient.getInstance(firebaseApp).bucket();
        BlobId blobId = BlobId.of(bucket.getName(),name);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/jpeg").build();
        Blob blob = storage.create(blobInfo,byteArray);
        URL url =blob.signUrl(100,TimeUnit.DAYS, Storage.SignUrlOption.signWith((ServiceAccountSigner) credentials));
        System.out.println(url.getHost()+url.getFile());

        return url.getProtocol()+"://"+url.getHost()+url.getFile();

    }


    public byte[] convertImgToByte(){
        try {
            // Read the image file
            BufferedImage image = ImageIO.read(new File("C:\\Users\\Ahmed Mohamed\\Downloads\\LogoSample_ByTailorBrands.jpg"));

            // Create a ByteArrayOutputStream
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // Write the image to the ByteArrayOutputStream as a JPEG
            ImageIO.write(image, "jpg", baos);

            // Convert the ByteArrayOutputStream to a byte array
            byte[] byteArray = baos.toByteArray();

            // Print the length of the byte array
            System.out.println("Byte array length: " + byteArray.length);
            return byteArray;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static byte[] inputStreamToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) > -1 ) {
            baos.write(buffer, 0, len);
        }
        baos.flush();
        return baos.toByteArray();
    }

}
