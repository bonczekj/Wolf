package com.wolf.scheduller;

/**
 * Created by Jirka on 23.04.2017.
 */

import com.wolf.Domain.LogFile;
import com.wolf.Repository.LogFilesRepository;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.Date;
import java.util.Properties;

@Configuration
@PropertySource(value = "classpath:ftp.properties")

@Component
public class ftpDownloader {
    static Properties props;
    @Autowired
    private Environment environment;

    @Value("${ftp.server.address}")
    private String ftpAddress;
    @Value("${ftp.server.port}")
    private String ftpPort;
    @Value("${ftp.login.user}")
    private String ftpUser;
    @Value("${ftp.login.password}")
    private String ftpPassword;
    @Value("${ftp.dir.remote}")
    private String ftpDirRemote;
    @Value("${ftp.dir.local}")
    private String ftpDirLocal;

    @Autowired
    LogFilesRepository logFilesRepository;

    //@Scheduled(fixedDelay=7200000)
    public void ftpDownloader() throws IOException, SAXException, ParserConfigurationException {
        System.out.println("Scheduled FTP downloader started: "+ new Date(System.currentTimeMillis()).toString());
        props = new Properties();

        FTPClient ftpClient = new FTPClient();
        try {

            ftpClient.setControlEncoding("UTF-8");
            ftpClient.connect( ftpAddress, Integer.parseInt(ftpPort));
            ftpClient.login(ftpUser, ftpPassword);

            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            String remoteFilePath = ftpDirRemote;
            FTPFile[] ftpFiles = ftpClient.listFiles(remoteFilePath, FTPFile::isFile);
            for (FTPFile ftpFile : ftpFiles) {
                if (ftpFile.getName().endsWith(".cslog")){
                    if (ftpFile.getSize() > 50000){
                        LogFile logFile = logFilesRepository.findByFile(ftpFile.getName());
                        if (logFile == null ){
                            File localfile = new File(ftpDirLocal+"/"+ftpFile.getName());
                            /*OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(localfile));
                            boolean success = ftpClient.retrieveFile(ftpDirRemote + '/' + ftpFile.getName(), outputStream);*/

                            //Create an InputStream to the File Data and use FileOutputStream to write it
                            InputStream inputStream = ftpClient.retrieveFileStream(ftpDirRemote + '/' + ftpFile.getName());
                            FileOutputStream fileOutputStream = new FileOutputStream(localfile);
                            //Using org.apache.commons.io.IOUtils
                            IOUtils.copy(inputStream, fileOutputStream);
                            fileOutputStream.flush();
                            IOUtils.closeQuietly(fileOutputStream);
                            IOUtils.closeQuietly(inputStream);
                            boolean success = ftpClient.completePendingCommand();

                            /*OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(localfile));
                            InputStream inputStream = ftpClient.retrieveFileStream(ftpFile.getName());
                            byte[] bytesArray = new byte[4096];
                            int bytesRead = -1;
                            while ((bytesRead = inputStream.read(bytesArray)) != -1) {
                                outputStream.write(bytesArray, 0, bytesRead);
                            }
                            boolean success = ftpClient.completePendingCommand();
                            outputStream.close();*/

                            if (success) {
                                System.out.println("Downloaded file: " + ftpFile.getName());
                            }else {
                                System.out.println("Error in file downloading: " + ftpFile.getName());
                            }

                        }
                    }
                }
            }
            /*File localfile = new File(environment.getProperty("ftp_local_dir"));
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(localfile));
            boolean success = ftpClient.retrieveFile(remoteFilePath, outputStream);
            outputStream.close();

            if (success) {
                System.out.println("Ftp file successfully download.");
            }*/

        } catch (IOException ex) {
            System.out.println("Error occurs in downloading files from ftp Server : " + ex.getMessage());
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
