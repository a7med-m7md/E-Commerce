package com.laphup.controller;

import com.google.gson.Gson;
import com.laphup.dtos.LaptopDTO;
import com.laphup.persistence.entities.Laptop;
import com.laphup.service.LaptopService;
import jakarta.json.Json;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@WebServlet(name = "laptop", value = "/laptop")
public class LaptopServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get uuid from request
        String uuid = req.getParameter("productId");

        //Convert uuid from string to UUID
        UUID productUUID = UUID.fromString(uuid);

        //Call service to call repositories to get laptop by uuid
        LaptopService laptopService = new LaptopService(req);
        LaptopDTO laptopDTO = laptopService.getLaptop(productUUID);

        //Convert laptop page to json object
        Jsonb jsonb = JsonbBuilder.create();
        String messageJson = jsonb.toJson(laptopDTO);
        //Write json resposte to client
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println(messageJson);
    }
}
