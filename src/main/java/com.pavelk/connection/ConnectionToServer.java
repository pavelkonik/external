package com.pavelk.connection;

import com.pavelk.AccessData;

import java.util.List;

public interface ConnectionToServer {
    List<String> getCfgmmlFilesListFromServer(AccessData accessData);
    void cfgmmlDataFromServer(AccessData accessData, List<String> listPathToRnc);
    }
