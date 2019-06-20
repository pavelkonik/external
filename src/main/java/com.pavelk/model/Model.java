package com.pavelk.model;

import com.pavelk.AccessData;
import com.pavelk.cells.Cell;
import com.pavelk.cells.External3GCell;
import com.pavelk.cells.ResultCell;
import com.pavelk.comparation.Comparation;
import com.pavelk.comparation.ImplementComparation;
import com.pavelk.connection.ConnectionToServer;
import com.pavelk.connection.SftpConnectionToServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import static com.pavelk.cells.Cell.cellList;
import static com.pavelk.cells.Cell.getCellList;

public class Model {
    private static final Logger logger2 = LoggerFactory.getLogger(Model.class.getSimpleName());

    private static Model instance = new Model();
    private AccessData accessData;

//    private List<Cell> cellsList;
    private List<External3GCell> external3GCellsList;
    private List<ResultCell> resultCellsList;

    public AccessData getAccessData() {
        return accessData;
    }

//    public List<Cell> getCellsList() {
//        return cellsList;
//    }
//
//    public void setCellsList(List<Cell> cellsList) {
//        this.cellsList = cellsList;
//    }

    public List<External3GCell> getExternal3GCellsList() {
        return external3GCellsList;
    }

    public void setExternal3GCellsList(List<External3GCell> external3GCellsList) {
        this.external3GCellsList = external3GCellsList;
    }

    public List<ResultCell> getResultCellsList() {
        return resultCellsList;
    }

    public void setResultCellsList(List<ResultCell> resultCellsList) {
        this.resultCellsList = resultCellsList;
    }

    private List<String> cfgmmlFilesList ;
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

    public void CfgmmlFilesList() {
        cfgmmlFilesList = new ArrayList<>();
        ConnectionToServer connectionToServer = new SftpConnectionToServer();
        cfgmmlFilesList = connectionToServer.getCfgmmlFilesListFromServer(accessData);
   //     return cfgmmlFilesList;

    }

    private List<String> rncListForCheckExtPsc ;

    public List<String> getRncListForCheckExtPsc() {
        return rncListForCheckExtPsc;
    }

    public void rncListForCheckExtPsc(HttpServletRequest req) {
        rncListForCheckExtPsc = new ArrayList<>();
        String rncs = req.getParameter("rncs");
        List<String> rncsList = Arrays.asList(rncs.split(","));
        for (int i = 0; i < rncsList.size(); i++) {
            if (rncsList.get(i).trim().matches("[0-9]+"))
                rncListForCheckExtPsc.add(rncsList.get(i).trim());
        }
    //    return rncListForCheckExtPsc;
    }

    public void incorrectExternalPsc() {
//        logger2.info(" incorrectExternalPsc SIZE" + incorrectExternalPsc.size());
        incorrectExternalPsc = new ArrayList<>();
        ConnectionToServer connectionToServer = new SftpConnectionToServer();
        connectionToServer.cfgmmlDataFromServer(accessData, getPathToRnc(rncListForCheckExtPsc));
        if (getCellList() == null) {
            System.out.println("There are no cells");
            incorrectExternalPsc = null;
            return ;
        }

        Comparation comparation = new ImplementComparation();
        incorrectExternalPsc = comparation.pscExternal3GComparation();
      //  return incorrectExternalPsc;

    }

    private List<String> getPathToRnc(List<String> listRnc) {
        List<String> result = new ArrayList<>();

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
