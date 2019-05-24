package com.pavelk;

public class AccessData {
    String IP;
    String user;
    String password;
    int port;
    String protocol;
    String pathToFiles;

    private AccessData accessData;
    public void setIP(String IP) {
        this.IP = IP;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setPathToFiles(String pathToFiles) {
        this.pathToFiles = pathToFiles;
    }

    public AccessData() {
    }

    public AccessData(String IP, String user, String password) {
        this.IP = IP;
        this.user = user;
        this.password = password;
    }

    public AccessData(String IP, String user, String password, int port, String protocol) {
        this.IP = IP;
        this.user = user;
        this.password = password;
        this.port = port;
        this.protocol = protocol;
    }

    public AccessData(String IP, String user, String password, int port, String protocol, String pathToFiles) {
        this.IP = IP;
        this.user = user;
        this.password = password;
        this.port = port;
        this.protocol = protocol;
        this.pathToFiles = pathToFiles;
    }

    public String getIP() {
        return IP;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getPathToFiles() {
        return pathToFiles;
    }

//    public AccessData getAccessData) {
//        return getAccessData;
//    }
}
