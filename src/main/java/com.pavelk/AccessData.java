package com.pavelk;

public class AccessData {
    String IP;
    String user;
    String password;
    int port;
    String protocol;
    String pathToFiles;

    public AccessData() {
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

    public AccessData getData() {
        return null;
    }
}
