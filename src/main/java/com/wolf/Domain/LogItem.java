package com.wolf.Domain;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jirka on 04.01.2017.
 */

@Entity
@IdClass(LogItemPK.class)
public class LogItem {
    @Id
    private Integer paramno;
    @Id
    private Date timestamp;

    private String value;

    private Integer fileID;


    public LogItem(){

    }

    public LogItem(Integer fileID, Integer paramNo, Date timestamp, String value){
        //this.pk = new LogItemPK(paramNo, timestamp);

        this.fileID = fileID;
        this.paramno = paramNo;
        //this.setTimestamp(timestamp);
        this.timestamp = timestamp;
        this.value = value;
    };

    public Integer getParamNo() {
        return  paramno;
    }

    public void setParamNo(Integer paramNo) {
        this.paramno = paramNo;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        //DateTime dtp = ISODateTimeFormat.dateTimeNoMillis().parseDateTime(timestamp);
        //this.timestamp = dtp.toDate();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getFileID() {
        return fileID;
    }

    public void setFileID(Integer fileID) {
        this.fileID = fileID;
    }
}
