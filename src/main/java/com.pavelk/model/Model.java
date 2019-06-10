package com.pavelk.model;

import com.pavelk.AccessData;
import com.pavelk.Main;
import com.pavelk.cells.ResultCell;
import com.pavelk.comparation.Comparation;
import com.pavelk.comparation.ImplementComparation;
import com.pavelk.connection.ConnectionToServer;
import com.pavelk.connection.SftpConnectionToServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.pavelk.cells.Cell.cellList;
import static com.pavelk.cells.ResultCell.resultCellList;

public class Model {
    private static final Logger logger2 = LoggerFactory.getLogger(Model.class);

    private static Model instance = new Model();
    private AccessData accessData;

    private List<String> cfgmmlFilesList = new ArrayList<>();
    private List<ResultCell> incorrectExternalPsc;

    public List<ResultCell> getIncorrectExternalPsc() {
        return incorrectExternalPsc;
    }

    public void setCfgmmlFilesList(List<String> cfgmmlFilesList) {
        this.cfgmmlFilesList = cfgmmlFilesList;
    }

    public List<String> getCfgmmlFilesList() {
        return cfgmmlFilesList;
    }

    public static Model getInstance() {
        return instance;
    }

    public AccessData setAccessData(HttpServletRequest request) {
        accessData = new AccessData();
        accessData.setUser(request.getParameter("user"));
        accessData.setPassword(request.getParameter("pass"));
        accessData.setIP(request.getParameter("serverIP"));
        accessData.setPathToFiles("/opt/raw_data/huawei/sd");
        accessData.setPort(22);
        accessData.setProtocol("sftp");
        return accessData;
    }

    public List<String> getCfgmmlFilesListMethod() {

        ConnectionToServer connectionToServer = new SftpConnectionToServer();
        cfgmmlFilesList = connectionToServer.getCfgmmlFilesListFromServer(accessData);
        return cfgmmlFilesList;

    }

    private List<String> rncListForCheckExtPsc = new ArrayList<>();

    public List<String> getRncListForCheckExtPsc() {
        return rncListForCheckExtPsc;
    }

    public List<String> getListRnc(HttpServletRequest req) {
        String rncs = req.getParameter("rncs");
        List<String> rncsList = Arrays.asList(rncs.split(","));
        for (int i = 0; i < rncsList.size(); i++) {
            if (rncsList.get(i).trim().matches("[0-9]+"))
                rncListForCheckExtPsc.add(rncsList.get(i).trim());
        }
        return rncListForCheckExtPsc;
    }

    public List<ResultCell> defIncorrectExternalPsc() {
        ConnectionToServer connectionToServer = new SftpConnectionToServer();
        connectionToServer.getCfgmmlDataFromServer(accessData, getPathToRnc(rncListForCheckExtPsc));
        Comparation comparation = null;
        if (cellList != null)
            comparation = new ImplementComparation();
        else {
            System.out.println("There are no cells");
            return null;
        }
        incorrectExternalPsc = comparation.pscExternal3GComparation();

        return incorrectExternalPsc;

    }

    private List<String> getPathToRnc(List<String> listRnc) {
//        System.out.println("Enter RNCs Id then exit");
// //        String result = "";
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        int RncId = 0;
//        List<Integer> listRnc = new ArrayList<>();
        List<String> result = new ArrayList<>();
//        String s = "";
//        try {
//            while (!(s = reader.readLine()).equals("exit")) {
//                RncId = Integer.parseInt(s);
//                listRnc.add(RncId);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        for (int i = 0; i < listRnc.size(); i++) {
            long maxData = 0;
            for (String str : cfgmmlFilesList) {
                if (str.contains("RNC" + listRnc.get(i))) {
                    Long data = Long.parseLong(str.split("-")[3].substring(0, 14));
                    if (data > maxData)
                        maxData = data;
                }
            }

            for (String str : cfgmmlFilesList) {
                if (str.contains("RNC" + listRnc.get(i)) && str.contains(Long.toString(maxData))) {
                    result.add(str);
                    break;
                }
            }
        }
        return result;
    }

}
