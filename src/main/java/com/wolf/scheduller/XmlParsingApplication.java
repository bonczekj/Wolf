package com.wolf.scheduller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.wolf.Domain.LogFile;
import com.wolf.Domain.LogItem;
import com.wolf.Domain.Parameter;
import com.wolf.Domain.Texts;
import com.wolf.Repository.LogFilesRepository;
import com.wolf.Repository.LogRepository;
import com.wolf.Repository.ParameterRepository;
import com.wolf.Repository.TextsRepository;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sun.rmi.runtime.Log;

/**
 * Created by Jirka on 04.01.2017.
 */

@Component
public class XmlParsingApplication {

    @Autowired
    LogRepository logRepository;
    @Autowired
    LogFilesRepository logFilesRepository;
    @Autowired
    ParameterRepository parameterRepository;
    @Autowired
    TextsRepository textsRepository;
    @Autowired
    private ApplicationContext applicationContext = null;
    @Autowired
    LogConvertor logConvertor;

    private Resource[] resources;


    @Scheduled(fixedDelay=500000)
    public void processFiles() throws IOException, SAXException, ParserConfigurationException {
        boolean flg_files = false;
        System.out.println("Scheduled process started: "+ new Date(System.currentTimeMillis()).toString());
        try {
            //resources = applicationContext.getResources("file:C:/java/del/prot_*");
            resources = applicationContext.getResources("file:C:/wolf/data/prot_2*");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        for (Resource resource: resources) {
            //importParams(resource.getFile().getAbsolutePath());
            processXML(resource.getFile().getAbsolutePath());
            flg_files = true;
            //LogConvertor logConvertor = new LogConvertor();
        }
        if (flg_files) {
            System.out.println("Converting start: "+ new Date(System.currentTimeMillis()).toString());
            logConvertor.start();
            System.out.println("Converting end: "+ new Date(System.currentTimeMillis()).toString());
        }
    }

    public void importParams(String XMLFile) throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = new File(XMLFile);

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(xmlFile);
            NodeList nodes = doc.getElementsByTagName("LogParameter");

            String descDE;
            String descCZ;
            String descEN;

            for (int i = 0; i < nodes.getLength(); i++) {

                /*
    <LogParameter>
      <GraphStateValue xsi:nil="true" />
      <Index>0</Index>
      <DeviceTemplateId>WRS-BM</DeviceTemplateId>
      <DeviceId>BM-768</DeviceId>
      <ParameterDescriptorId>Raumsollwert</ParameterDescriptorId>
      <IsGraphEnabled>true</IsGraphEnabled>
      <ModuleBusId>1</ModuleBusId>
      <HeatingCircuit xsi:nil="true" />
      <ConverterTemplateId>cVERSTELLTE_RAUMSOLLTEMP(ext)-cBEDIEN(null)-Int10</ConverterTemplateId>
    </LogParameter>

                                 */
                Element element = (Element) nodes.item(i);
                String id = element.getElementsByTagName("Index").item(0).getTextContent();
                String type = element.getElementsByTagName("DeviceId").item(0).getTextContent();
                descDE = element.getElementsByTagName("ParameterDescriptorId").item(0).getTextContent();
                Texts textW = textsRepository.findByDEU(descDE);
                descCZ = "";
                descEN = "";
                if (textW != null){
                    descCZ = textW.getCsy();
                    descEN = textW.getEng();
                }
                Parameter parameter = new Parameter(Integer.parseInt(id), type, descCZ, descDE, descEN, "");
                parameterRepository.save(parameter);
            }
    };

    public void processXML(String XMLFile) throws IOException, SAXException, ParserConfigurationException {
        //ApplicationContext ctx = new AnnotationConfigApplicationContext(PersistenceContext.class);
        //LogRepository logRepository = ctx.getBean(LogRepository.class);

        //File logFile = new File("C:\\java\\del\\prot_20161230_2138.cslog.xml");


        File xmlFile = new File(XMLFile);
        if (xmlFile.getTotalSpace() < 50000 ){
            System.out.println("Empty XML file: "+xmlFile.getName() +" "+ new Date(System.currentTimeMillis()).toString());
            return;
        }
        LogFile logFile = logFilesRepository.findByFile(xmlFile.getName());
        if (logFile == null ){
            logFile = new LogFile();
            logFile.setFileName(xmlFile.getName());
            logFile.setFileImport(DateTime.now().toDate());
            logFilesRepository.save(logFile);
            Integer fileID = logFile.getFileID();

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(xmlFile);
            NodeList nodes = doc.getElementsByTagName("LogItem");
            for (int i = 0; i < nodes.getLength(); i++) {

                Element element = (Element) nodes.item(i);

                String cx = element.getAttribute("cx");
                String ts = element.getAttribute("ts");
                String v = element.getAttribute("v");

                DateTime dtp = ISODateTimeFormat.dateTimeNoMillis().parseDateTime(ts);

                LogItem logItem = new LogItem(fileID, Integer.parseInt(cx), dtp.toDate(), v);
                logRepository.save(logItem);
            }
            xmlFile.renameTo(new File(xmlFile.getParent()+"\\ARCH", xmlFile.getName()));
            System.out.println("Import done of XML : "+xmlFile.getName() +" "+ new Date(System.currentTimeMillis()).toString());
        }else {
            System.out.println("Already imported XML : "+xmlFile.getName() +" "+ new Date(System.currentTimeMillis()).toString());
        }
    };

    /*public static void main(String[] args) throws Exception {
        XmlParsingApplication parser = new XmlParsingApplication();
        parser.processXML();


    }*/
}
