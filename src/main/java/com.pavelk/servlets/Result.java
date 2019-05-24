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
//        Model model = Model.getInstance();
//        List<String> names = model.list();
//        req.setAttribute("userNames", names);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/result.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Model model = Model.getInstance();
        AccessData accessData = model.setAccessData(req);
        List<String> stringList = null;
        if (accessData.getUser()!=null) {
            stringList = model.getCfgmmlFiles();
        }
//
        if (stringList!=null) {
            req.setAttribute("listCfgmmlFiles", stringList);
        }
        doGet(req, resp);

    }
}
