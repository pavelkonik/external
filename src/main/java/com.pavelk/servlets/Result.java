package com.pavelk.servlets;

import com.pavelk.AccessData;
import com.pavelk.model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet(name = "Result", urlPatterns = "/result")

public class Result extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/result.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Model model = Model.getInstance();

        if (model.getCfgmmlFilesList() == null) {
            model.setAccessData(req);
            if (model.getAccessData().getUser() != null) {
                model.getCfgmmlFilesListMethod();
            }
        }
        // if (model.getCfgmmlFilesList() != null) {
        req.setAttribute("listCfgmmlFiles", model.getCfgmmlFilesList());
        //  }
        doGet(req, resp);

    }
}
