package es.babel.clientesbanco.utils;


import es.babel.clientesbanco.service.ClienteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LogUtils {

    public static final Logger logger = LogManager.getLogger(ClienteService.class);

    public void logEror(String msg){
        logger.error(msg);
    }

    public void logDebug(String msg){
        logger.debug(msg);
    }

    public void loginfo(String msg){
        logger.info(msg);
    }

    public void logWarn(String msg){
        logger.warn(msg);
    }

    public void logFatal(String msg){
        logger.fatal(msg);
    }
}
