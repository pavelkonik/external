package com.pavelk;

import com.pavelk.cells.*;
import com.pavelk.connection.ConnectionToServer;
import com.pavelk.connection.SftpConnectionToServer;
import com.pavelk.comparation.*;
//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

import static com.pavelk.cells.Cell.cellList;
import static com.pavelk.cells.ResultCell.resultCellList;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        System.out.println("Enter the user");
        String user = "";
        String pass = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            user= reader.readLine();
            System.out.println("Enter the pass");
            pass = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AccessData accessData = new AccessData("172.17.112.50", user, pass,
                22, "sftp", "/opt/raw_data/huawei/sd");
        ConnectionToServer connectionToServer = new SftpConnectionToServer();
        List<String> filesNameList = connectionToServer.getCfgmmlFilesListFromServer(accessData);
        for (String filesName : filesNameList) {
            System.out.println(filesName);
        }

        List<String> listPathToRnc = getPathToRnc(filesNameList);
        connectionToServer.getCfgmmlDataFromServer(accessData, listPathToRnc);

        Comparation comparation = null;
        if (cellList != null)
            comparation = new ImplementComparation();
        else {
            System.out.println("There are no cells");
            return;
        }

        resultCellList = comparation.pscExternal3GComparation();
        if (resultCellList == null) System.out.println("There is not external cells with incorrect PSC");
        else
            for (ResultCell resultCell : resultCellList) {
                System.out.println(resultCell.getCell().getCellName() +
                        " on " + resultCell.getCell().getRnc() + "RNC with PSC = " + resultCell.getCell().getPsc() +
                        " has external with PSC = " + resultCell.getExternalCell().getPsc() + " on "
                        + resultCell.getExternalCell().getRnc() + "RNC");
            }
    }

    private static List<String> getPathToRnc(List<String> filesNamList) {
        System.out.println("Enter RNCs Id then exit");
    //    String result = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int RncId = 0;
        List<Integer> listRnc = new ArrayList<>();
        List<String> result = new ArrayList<>();
        String s = "";
        try {
            while (!(s = reader.readLine()).equals("exit")) {
                RncId = Integer.parseInt(s);
                listRnc.add(RncId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < listRnc.size(); i++) {
            long maxData = 0;
            for (String str : filesNamList) {
                if (str.contains("RNC" + listRnc.get(i))) {
                    Long data = Long.parseLong(str.split("-")[3].substring(0, 14));
                    if (data > maxData)
                        maxData = data;
                }
            }

            for (String str : filesNamList) {
                if (str.contains("RNC" + listRnc.get(i)) && str.contains(Long.toString(maxData))) {
                    result.add(str);
                    break;
                }
            }
        }
        return result;
    }


}
