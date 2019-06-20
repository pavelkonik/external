package com.pavelk.servlets;

import com.pavelk.connection.SftpConnectionToServer;
import com.pavelk.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "ListCfgmmlFiles", urlPatterns = "/listCfgmmlFiles")

public class ListCfgmmlFiles extends HttpServlet {
    private static final Logger LOGGERLISTCFGMMLFILES = LoggerFactory.getLogger(ListCfgmmlFiles.class.getSimpleName());

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Model model = Model.getInstance();
        LOGGERLISTCFGMMLFILES.info("select POST method");
        if (model.getCfgmmlFilesList()!=null) {
            req.setAttribute("listCfgmmlFiles", model.getCfgmmlFilesList());
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/listCfgmmlFiles.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Model model = Model.getInstance();

        if (model.getCfgmmlFilesList() == null) {
            model.setAccessData(req);
            if (model.getAccessData().getUser() != null) {
                model.CfgmmlFilesList();
            }
        }
        req.setAttribute("listCfgmmlFiles", model.getCfgmmlFilesList());
        doGet(req, resp);

    }
}
