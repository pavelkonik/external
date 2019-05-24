package com.pavelk.model;

import com.pavelk.AccessData;
import com.pavelk.connection.ConnectionToServer;
import com.pavelk.connection.SftpConnectionToServer;

import java.util.List;

public class Model {
    private static Model instance = new Model();

    public static Model getInstance() {
        return instance;
    }

    public void Start (){
//        AccessData accessData = new AccessData("172.17.112.50", user, pass,
//                22, "sftp", "/opt/raw_data/huawei/sd");
//        ConnectionToServer connectionToServer = new SftpConnectionToServer();
//        List<String> filesNameList = connectionToServer.getCfgmmlFilesListFromServer(accessData);
    }

}
