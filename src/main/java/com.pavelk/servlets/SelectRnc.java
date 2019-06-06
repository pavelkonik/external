package com.pavelk.servlets;

import com.pavelk.AccessData;
import com.pavelk.cells.ResultCell;
import com.pavelk.model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.pavelk.cells.ResultCell.resultCellList;

public class SelectRnc extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/selectRnc.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Model model = Model.getInstance();
        List<String> listRnc =model.getListRnc(req);
        List<ResultCell> listIncorrectExtPsc =model.defIncorrectExternalPsc();

        if (listRnc!=null) {
            req.setAttribute("listIncorrectExtPSC", listIncorrectExtPsc);
        }

        doGet(req, resp);

    }
}

