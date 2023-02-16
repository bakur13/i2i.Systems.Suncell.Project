package com.i2i.OCSProject.dgwApp.logger;


import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Logs {

    static Logger logger=Logger.getLogger(Logs.class);

    public Logs(){
        DOMConfigurator.configure("C:\\Users\\recep\\Downloads\\dgwApp\\dgwApp\\log4j.xml");
    }

    public void info(String message){
        logger.info(message);
    }

    public void warn(String message){
        logger.warn(message);
    }
    public void error(String message){
        logger.error(message);
    }
    public void fatal(String message){
        logger.fatal(message);
    }
}
