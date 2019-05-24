package com.pavelk.model;

import com.pavelk.AccessData;
import com.pavelk.connection.ConnectionToServer;
import com.pavelk.connection.SftpConnectionToServer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class Model {
    private static Model instance = new Model();
    private AccessData accessData;

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

    public List<String> getCfgmmlFiles() {

        ConnectionToServer connectionToServer = new SftpConnectionToServer();
        return connectionToServer.getCfgmmlFilesListFromServer(accessData);

    }

}
