package com.pavelk.connection;

import com.jcraft.jsch.*;
import com.pavelk.AccessData;
import com.pavelk.cells.Cell;
import com.pavelk.cells.External3GCell;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.pavelk.cells.Cell.cellList;
import static com.pavelk.cells.External3GCell.external3GCells;

public class SftpConnectionToServer implements ConnectionToServer {

    @Override
    public void getConnection(AccessData accessData) {

    }

    @Override
    public void closeConnection() {

    }

    @Override
    public List<String> getCfgmmlFilesListFromServer(AccessData accessData) {

        String userName = accessData.getUser();
        String host = accessData.getIP();
        String password = accessData.getPassword();
        int port = accessData.getPort();
        String protocol = accessData.getProtocol();

        JSch ssh = new JSch();
        Channel channel = null;
        Session session = null;
        List<String> list = new ArrayList<>();
        try {
            session = ssh.getSession(userName, host, port);
            session.setPassword(password);
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            channel = session.openChannel(protocol);
            channel.connect();
            ChannelSftp sftp = (ChannelSftp) channel;
            try {
                List<ChannelSftp.LsEntry> files = sftp.ls(accessData.getPathToFiles());
                for (ChannelSftp.LsEntry lsEntry : files) {
                    if (lsEntry.getFilename().startsWith("CFGMML"))
                        list.add(lsEntry.getFilename());
                }
            } catch (SftpException e) {
                e.printStackTrace();
            }
        } catch (JSchException e) {
            e.printStackTrace();
        } finally {
            if (channel != null) channel.disconnect();
            if (session != null) session.disconnect();
        }
        return list;
    }

    @Override
    public void getCfgmmlDataFromServer(AccessData accessData, List<String> listPathToRnc) {
        String userName = accessData.getUser();
        String host = accessData.getIP();
        String password = accessData.getPassword();
        int port = accessData.getPort();
        String protocol = accessData.getProtocol();

        JSch ssh = new JSch();
        Channel channel = null;
        Session session = null;
        InputStream inputStream = null;
        try {
            session = ssh.getSession(userName, host, port);
            session.setPassword(password);
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            channel = session.openChannel(protocol);
            channel.connect();
            ChannelSftp sftp = (ChannelSftp) channel;
            for (int i = 0; i <listPathToRnc.size(); i++) {
                inputStream = sftp.get(accessData.getPathToFiles() + "/" + listPathToRnc.get(i));
                getCellsFromCfgmml(inputStream);
            }
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (channel != null) channel.disconnect();
            if (session != null) session.disconnect();
        }
    }

    private void getCellsFromCfgmml(InputStream inputStream) {
        try (ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(zipInputStream));
            String s = "";
            String cellName;
            int cellId;
            int psc;
            int lac;
            int rnc;
            int nRnc;
            while ((s = bufferedReader.readLine()) != null) {
                if (s.contains("ADD UCELLSETUP:")) {
                    cellId = Integer.parseInt(s.substring(s.indexOf("CELLID=") + 7, s.indexOf(",", s.indexOf("CELLID=") + 7)));
                    psc = Integer.parseInt(s.substring(s.indexOf("PSCRAMBCODE=") + 12, s.indexOf(",", s.indexOf("PSCRAMBCODE=") + 12)));
                    rnc = Integer.parseInt(s.substring(s.indexOf("LOGICRNCID=") + 11, s.indexOf(",", s.indexOf("LOGICRNCID=") + 11)));
                    String tmp = s.substring(s.indexOf("LAC=") + 4, s.indexOf(",", s.indexOf("LAC=") + 4));
                    lac = Integer.parseInt(tmp.substring(2), 16);
                    tmp = s.substring(s.indexOf("CELLNAME=") + 9, s.indexOf(",", s.indexOf("CELLNAME=") + 9));
                    cellName = tmp.substring(1, tmp.length() - 1);
                    cellList.add(new Cell(cellName, cellId, rnc, lac, psc));
                }
                if (s.contains("ADD UEXT3GCELL:")) {
                    cellId = Integer.parseInt(s.substring(s.indexOf("CELLID=") + 7, s.indexOf(",", s.indexOf("CELLID=") + 7)));
                    psc = Integer.parseInt(s.substring(s.indexOf("PSCRAMBCODE=") + 12, s.indexOf(",", s.indexOf("PSCRAMBCODE=") + 12)));
                    rnc = Integer.parseInt(s.substring(s.indexOf("LOGICRNCID=") + 11, s.indexOf(",", s.indexOf("LOGICRNCID=") + 11)));
                    String tmp = s.substring(s.indexOf("LAC=") + 4, s.indexOf(",", s.indexOf("LAC=") + 4));
                    lac = Integer.parseInt(tmp.substring(2), 16);
                    tmp = s.substring(s.indexOf("CELLNAME=") + 9, s.indexOf(",", s.indexOf("CELLNAME=") + 9));
                    cellName = tmp.substring(1, tmp.length() - 1);
                    nRnc = Integer.parseInt(s.substring(s.indexOf("NRNCID=")+7, s.indexOf(",", s.indexOf("NRNCID=")+7)));
                    external3GCells.add(new External3GCell(cellName, cellId, rnc, lac, psc, nRnc));
                }

            }
            zipInputStream.closeEntry();

        } catch (IOException e) {
            e.printStackTrace();

        }

    }


}
