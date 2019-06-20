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

public class IncorrectPSC extends HttpServlet {
    private static final Logger LOGGER_INCORRECT_PSC = LoggerFactory.getLogger(IncorrectPSC.class.getSimpleName());
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER_INCORRECT_PSC.info("doGet");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/incorrectPSC.jsp");
        requestDispatcher.forward(req, resp);
        LOGGER_INCORRECT_PSC.info("after requestDispatcher.forward(req, resp)");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Model model = Model.getInstance();
        model.rncListForCheckExtPsc(req);

        if (model.getRncListForCheckExtPsc()!=null || model.getRncListForCheckExtPsc().size()>0 ) {
            LOGGER_INCORRECT_PSC.info("before incorrectExternalPsc");
            //  model.setCellsList();
            model.incorrectExternalPsc();
        }
        LOGGER_INCORRECT_PSC.info("after incorrectExternalPsc");

        req.setAttribute("listIncorrectExtPSC", model.getIncorrectExternalPsc());

        LOGGER_INCORRECT_PSC.info("before doGet");
        doGet(req, resp);

    }
}

