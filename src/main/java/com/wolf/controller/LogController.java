package com.wolf.controller;

import com.wolf.Domain.LogItemConv;
import com.wolf.Domain.Parameter;
import com.wolf.Repository.LogItemConvRepository;
import com.wolf.Repository.ParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jirka on 11.01.2017.
 */

@RestController
@EnableAutoConfiguration
public class LogController {

    public final static char CR  = (char) 0x0D;
    public final static char LF  = (char) 0x0A;

    //public final static String CRLF  = "" + CR + LF;     // "" forces conversion to string
    public final static String CRLF  = "\n";

    @Autowired
    LogItemConvRepository logItemConvRepository;
    @Autowired
    ParameterRepository parameterRepository;

    @CrossOrigin
    @RequestMapping("/log")
    public String getLogs() {
        List<LogItemConv> logs = (List<LogItemConv>) logItemConvRepository.findAll();
        String result = "[";
        Boolean flg_exist = false;
        for (LogItemConv log : logs) {
            if (flg_exist) {
                result = result + ',' + CRLF;
            }
            result = result + '['
                    + log.getTimestamp().toInstant().toEpochMilli() + ','
                    + log.getP00().toString() + ','
                    + log.getP01().toString() + ','
                    /*+ log.getP02().toString() + ','
                    + log.getP03().toString() + ','*/
                    + log.getP04().toString() + "]";
            flg_exist = true;
        }
        result = result + CRLF + "]";
        //result = "[[1483040658000,15,85], [1483040717000,18,88], [1483040778000,29,99], [1483040838000,29,99], [1483040898000,15,85], [1483040958000,15,85]]";
        return result;

        //return (List<LogItemConv>) logItemConvRepository.findAll();
    }

    @CrossOrigin
    @RequestMapping("/log2")
    public List<LogItemConv> getLogs2() {
        List<LogItemConv> logs = (List<LogItemConv>) logItemConvRepository.findAll();
        return logs;
    }


    private String getMethodName(Integer paramNo){
        if (paramNo < 10 ){
            return "getP0" + paramNo.toString();
        }else{
            return "getP" + paramNo.toString();
        }
    }

    private Integer getAxe(Integer paramNo){
        Integer axe;
        switch (paramNo){
            case 0 : case 1 : case 2 :  case 3 : axe = 0; break;
            case 10 : case 11 : axe = 1; break;
            default: axe = 2; break;
        }
        return axe;
    }

    private List<HighStockData> getLogsInterval(Date start, Date end) {
        List<HighStockData> hsDataList = new ArrayList<HighStockData>();
        List<Parameter> params = (ArrayList<Parameter>) parameterRepository.findAll();
        List<LogItemConv> logs;
        if (start == null) {
            logs = (List<LogItemConv>) logItemConvRepository.getAll();
        }else{
            logs = (List<LogItemConv>) logItemConvRepository.getInterval(start, end);
        };

        Method method = null;
        for (Parameter param: params){
            try {
                method = LogItemConv.class.getMethod(getMethodName(param.getId()));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            HighStockData hsData = new HighStockData();
            ArrayList<Long[]> hsItems = new ArrayList<Long[]>();
            hsData.setName(param.getId().toString() +" - " + param.getNameCs() +param.getUnit());
            int i = 0;
            Long[] dataArray = new Long[2];
            for (LogItemConv log: logs) {
                try {
                    Integer intValue;
                    intValue = (Integer) method.invoke(log);
                    hsItems.add( new Long[]{log.getTimestamp().toInstant().toEpochMilli(), intValue.longValue()}  );
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            hsData.setData(hsItems);
            hsDataList.add(hsData);
            System.out.println("DataController records: "+String.valueOf(hsItems.size()));
        }
        System.out.println("DataController end: "+new Date(System.currentTimeMillis()).toString());
        return hsDataList;
    }

    @CrossOrigin
    @RequestMapping("/getData")
    public List<HighStockData> getLogs3() {
        System.out.println("getData start: "+new Date(System.currentTimeMillis()).toString());
        return getLogsInterval(null, null);
        /*List<HighStockData> hsDataList = new ArrayList<HighStockData>();
        List<Parameter> params = (ArrayList<Parameter>) parameterRepository.findAll();
        List<LogItemConv> logs = (List<LogItemConv>) logItemConvRepository.getAll();

        Method method = null;
        for (Parameter param: params){
            try {
                method = LogItemConv.class.getMethod(getMethodName(param.getId()));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            HighStockData hsData = new HighStockData();
            ArrayList<Long[]> hsItems = new ArrayList<Long[]>();
            hsData.setName(param.getId().toString() +" - " + param.getNameCs() +param.getUnit());
            int i = 0;
            Long[] dataArray = new Long[2];
            for (LogItemConv log: logs) {
                try {
                    Integer intValue;
                    intValue = (Integer) method.invoke(log);
                    hsItems.add( new Long[]{log.getTimestamp().toInstant().toEpochMilli(), intValue.longValue()}  );
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            hsData.setData(hsItems);
            hsDataList.add(hsData);
        }
        System.out.println("log3 end: "+new Date(System.currentTimeMillis()).toString());
        return hsDataList;*/
    }

    @CrossOrigin
    @RequestMapping("/logint")
    public List<HighStockData> getLogsInt(@RequestParam(value = "d1", required = false) Long d1,
                                          @RequestParam(value = "d2", required = false) Long d2) {
        System.out.println("logInterval start: "+new Date(System.currentTimeMillis()).toString());
        Date dfrom =new Date(d1);
        Date dto =new Date(d2);
        System.out.println("Start: "+ dfrom.toString());
        System.out.println("End: "+ dto.toString());
        return getLogsInterval(dfrom, dto);
        /*List<HighStockData> hsDataList = new ArrayList<HighStockData>();
        List<Parameter> params = (ArrayList<Parameter>) parameterRepository.findAll();
        List<LogItemConv> logs = (List<LogItemConv>) logItemConvRepository.getAll();

        Method method = null;
        for (Parameter param: params){
            try {
                method = LogItemConv.class.getMethod(getMethodName(param.getId()));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            HighStockData hsData = new HighStockData();
            ArrayList<Long[]> hsItems = new ArrayList<Long[]>();
            hsData.setName(param.getId().toString() +" - " + param.getNameCs() +param.getUnit());
            int i = 0;
            Long[] dataArray = new Long[2];
            for (LogItemConv log: logs) {
                try {
                    Integer intValue;
                    intValue = (Integer) method.invoke(log);
                    hsItems.add( new Long[]{log.getTimestamp().toInstant().toEpochMilli(), intValue.longValue()}  );
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            hsData.setData(hsItems);
            hsDataList.add(hsData);
        }
        System.out.println("logInterval end: "+new Date(System.currentTimeMillis()).toString());
        return hsDataList;*/
    }

    @CrossOrigin
    @RequestMapping("/params")
    public List<Parameter> getParams() {

        List<Parameter> params = (ArrayList<Parameter>) parameterRepository.findAll();
        return params;

        //return (List<LogItemConv>) logItemConvRepository.findAll();
    }

    @CrossOrigin
    @RequestMapping("/logtest")
    public String getLogtest() {
        String result = "[[1483040658000,15,85], [1483040717000,18,88], [1483040778000,29,99], [1483040838000,29,99], [1483040898000,15,85], [1483040958000,15,85]]";
        return result;

        //return (List<LogItemConv>) logItemConvRepository.findAll();
    }
}
