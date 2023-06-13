package com.ferros.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ferros.model.File;
import com.ferros.repository.FileRepository;
import com.ferros.repository.hibernate.HibernateFileRepositoryImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/file1")
public class FileServlet extends HttpServlet {

    private FileRepository fileRepository = new HibernateFileRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        File file = fileRepository.getById(1);
        resp.setContentType("application/json");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        //Creating a File instance
        List<File> fileList=fileRepository.getAll();


        try (var writer = resp.getWriter()) {
            ObjectMapper objectMapper = new ObjectMapper();
            writer.print(objectMapper.writeValueAsString(fileList));
            writer.flush();
        }

    }
}
