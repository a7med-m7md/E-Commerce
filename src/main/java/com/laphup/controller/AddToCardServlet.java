package com.laphup.controller;

import com.google.gson.Gson;
import com.laphup.dtos.LaptopDTO;
import com.laphup.dtos.UserDto;
import com.laphup.persistence.entities.Card;
import com.laphup.persistence.entities.CompositeID.CardId;
import com.laphup.persistence.entities.Laptop;
import com.laphup.persistence.entities.User;
import com.laphup.service.AddToCardService;
import com.laphup.service.CategoryService;
import com.laphup.util.enums.Rate;
import jakarta.json.JsonObject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/addToCard")
public class AddToCardServlet extends HttpServlet {

    static List<LaptopDTO> laptops = new ArrayList<>();
    Map<UUID, Integer> hm = new HashMap<UUID, Integer>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get UUid Of Product
        UUID productUuid = UUID.fromString(req.getParameter("uuid"));

        // get User
        HttpSession session = req.getSession();
        UserDto user = null;
        if(session.getAttribute("userInfo") == null){
            System.out.println("annonumous");
            CardId cardId = new CardId(UUID.fromString("00000000-0000-0000-0000-000000000000"), productUuid);
            Gson gson = new Gson();
            String jsonObject = gson.toJson(cardId);
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            out.print(jsonObject);
            out.flush();
            return;
        }
        if (session != null) {
            user = (UserDto) session.getAttribute("userInfo");
        }
        // Create Card Object
        CardId cardId = new CardId(user.getUuid(), productUuid);
        Gson gson = new Gson();
        String jsonObject = gson.toJson(cardId);
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(jsonObject);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        UUID uuidstr = gson.fromJson(req.getReader(), UUID.class);

        AddToCardService addToCardService = new AddToCardService(req);
        LaptopDTO laptop = addToCardService.getLaptopByUuid(uuidstr);
        laptops.add(laptop);
        System.out.println(laptops.size());
        for (LaptopDTO laptop1 : laptops)
            System.out.println(laptop1.getName());
        Gson gson1 = new Gson();
        resp.getWriter().print(gson1.toJson(laptops));

    }
}


