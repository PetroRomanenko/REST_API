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
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/file")
public class FileServletNew extends HttpServlet {
    private FileRepository fileRepository = new HibernateFileRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<File> fileList = fileRepository.getAll();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html><head><title>File List</title></head><body>");
        htmlBuilder.append("<h1>File List</h1>");
        htmlBuilder.append("<table>");
        htmlBuilder.append("<thead><tr><th>ID</th><th>Name</th><th>File Path</th><th>Action</th></tr></thead>");
        htmlBuilder.append("<tbody>");

        for (File file : fileList) {
            htmlBuilder.append("<tr>");
            htmlBuilder.append("<td>").append(file.getId()).append("</td>");
            htmlBuilder.append("<td>").append(file.getName()).append("</td>");
            htmlBuilder.append("<td>").append(file.getFilePath()).append("</td>");
            htmlBuilder.append("<td><a href=\"downloadFile?id=").append(file.getId()).append("\">Download</a></td>");
            htmlBuilder.append("</tr>");
        }

        htmlBuilder.append("</tbody>");
        htmlBuilder.append("</table>");
        htmlBuilder.append("</body></html>");

        response.getWriter().println(htmlBuilder.toString());
    }
}