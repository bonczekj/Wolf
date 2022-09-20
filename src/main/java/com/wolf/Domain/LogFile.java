package com.wolf.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Jirka on 04.01.2017.
 */
@Entity(name = "LogFiles")
public class LogFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer fileID;
    private String fileName;
    private Date fileImport;

    public Integer getFileID() {
        return fileID;
    }

    public void setFileID(Integer fileID) {
        this.fileID = fileID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getFileImport() {
        return fileImport;
    }

    public void setFileImport(Date fileImport) {
        this.fileImport = fileImport;
    }
}
