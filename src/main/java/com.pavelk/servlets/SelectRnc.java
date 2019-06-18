package com.pavelk.servlets;

import com.pavelk.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SelectRnc extends HttpServlet {
    private static final Logger LOGGER_SEKECT_RNC = LoggerFactory.getLogger(SelectRnc.class.getSimpleName());
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER_SEKECT_RNC.info("doGet");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/selectRnc.jsp");
        LOGGER_SEKECT_RNC.info("before requestDispatcher.forward(req, resp)");
        requestDispatcher.forward(req, resp);
        LOGGER_SEKECT_RNC.info("after requestDispatcher.forward(req, resp)");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Model model = Model.getInstance();
        model.RncListForCheckExtPsc(req);

        if (model.getRncListForCheckExtPsc()!=null || model.getRncListForCheckExtPsc().size()>0 ) {
            LOGGER_SEKECT_RNC.info("before defIncorrectExternalPsc");
            //  model.setCellsList();
            model.defIncorrectExternalPsc();
        }
        LOGGER_SEKECT_RNC.info("after defIncorrectExternalPsc");

        LOGGER_SEKECT_RNC.info("set attr listIncorrectExtPsc");
        req.setAttribute("listIncorrectExtPSC", model.getIncorrectExternalPsc());

//        if (model.getIncorrectExternalPsc()!=null) {
//            LOGGER_SEKECT_RNC.info("set attr listIncorrectExtPsc");
//            req.setAttribute("listIncorrectExtPSC", listIncorrectExtPsc);
//        }

        LOGGER_SEKECT_RNC.info("before doGet");
        doGet(req, resp);

    }
}

