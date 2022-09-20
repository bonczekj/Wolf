package com.wolf.Domain;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jirka on 04.01.2017.
 */
public class LogItemPK implements Serializable {

    private Integer paramno;
    private Date timestamp;

    public LogItemPK(){

    }

    public LogItemPK(Integer paramNo, Date timestamp){
        this.paramno = paramNo;
        this.timestamp = timestamp;
        //setTimestamp(timestamp);
    }

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + paramno;
        result = prime * result + timestamp.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LogItemPK other = (LogItemPK) obj;
        if (!timestamp.equals(other.timestamp))
            return false;
        if (paramno != other.paramno)
            return false;
        return true;
    }
}
