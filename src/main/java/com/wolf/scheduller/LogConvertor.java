package com.wolf.scheduller;

import com.wolf.Domain.LogItem;
import com.wolf.Domain.LogItemConv;
import com.wolf.Repository.LogItemConvRepository;
import com.wolf.Repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Jirka on 10.01.2017.
 */

@Component
public class LogConvertor {

    @Autowired
    LogItemConvRepository logItemConvRepository;
    @Autowired
    LogRepository logRepository;

    private List<LogItem> getData(){
        LogItemConv lic = logItemConvRepository.getLastTS();
        if (lic != null){
            return logRepository.findByTS(lic.getTimestamp());
        }else {
            return (List<LogItem>) logRepository.findAll();
        }

        /*List<LogItemConv> lics = (List<LogItemConv>) logItemConvRepository.findAll();
        if (lics.size() > 0){
            //LogItemConv lic = logItemConvRepository.getLastTS();
            return logRepository.findByTS(lic.getTimestamp());
        }else {
            return (List<LogItem>) logRepository.findAll();
        }*/
    }

    private String getMethodName(Integer paramNo){
        if (paramNo < 10 ){
            return "setP0" + paramNo.toString();
        }else{
            return "setP" + paramNo.toString();
        }
    }

    private Integer getValue(LogItem logItem){
        if (logItem.getValue().isEmpty()){
            return 0;
        }else if (logItem.getValue().contentEquals("True")){
            return 100;
        }else if (logItem.getValue().contentEquals("False")){
            return 0;
        }else {
            return Math.round(Float.parseFloat(logItem.getValue().replace(',', '.')));
        }
    }

    private void saveValue(LogItem logItem){
        LogItemConv lic = logItemConvRepository.findByTS(logItem.getTimestamp());
        if (lic == null) {
            lic = new LogItemConv();
            lic.setTimestamp(logItem.getTimestamp());
        }
        //String methodName = "setP" + logItem.getParamNo().toString();
        Method method = null;
        try {
            method = lic.getClass().getMethod(getMethodName(logItem.getParamNo()), Integer.class);
            Object ret = method.invoke(lic, getValue(logItem));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        logItemConvRepository.save(lic);
    }

    private void convertData(List<LogItem> logItems){
        for (LogItem logItem : logItems) {
            saveValue(logItem);
        }
    }


    public void start(){
        convertData(getData());
    }
}
