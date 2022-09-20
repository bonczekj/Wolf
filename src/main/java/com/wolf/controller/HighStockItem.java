package com.wolf.controller;

import java.util.Date;

/**
 * Created by Jirka on 16.01.2017.
 */
public class HighStockItem {
//    private Date timestamp;
    private Long[] value;

  /*  public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }*/

    public Long[] getValue() {
        return value;
    }

    public void setValue(Long[] value) {
        this.value = value;
    }
}
