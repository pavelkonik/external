package com.pavelk.connection;

import com.pavelk.AccessData;

import java.io.BufferedReader;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class FtpConnectionToServer implements ConnectionToServer{


    @Override
    public List<String> getCfgmmlFilesListFromServer(AccessData accessData) {
        return null;
    }

    @Override
    public void cfgmmlDataFromServer(AccessData accessDat, List<String> listPathToRnc) {

    }
}
