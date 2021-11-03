package com.javacourse.task3.builder;

import com.javacourse.task3.entity.Tariff;
import com.javacourse.task3.exception.TariffException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.HashSet;

public class TariffSaxBuilder extends AbstractTariffBuilder{

    private static final Logger logger = LogManager.getLogger(TariffSaxBuilder.class);

    private TariffHandler handler = new TariffHandler();
    private XMLReader reader;

    public TariffSaxBuilder(){
        super();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try{
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();

        }catch(ParserConfigurationException  | SAXException e){
            logger.error("Error in TariffSaxBuilder: " + e.getMessage());
        }
        reader.setContentHandler(handler);
        //reader.setErrorHandler(errorHandler);
    }
//    @Override
//    public HashSet<Tariff> getTariff(){
//        return (HashSet<Tariff>) tariffs.clone();
//    }
    @Override
    public void buildSetTariffs(String path) throws TariffException{
        try{
            reader.parse(path);
        }catch (SAXException e){
            logger.error("Error in method buildSetTariffs: " + e.getMessage());
            throw new TariffException("Error in method buildSetTariffs", e);
        }catch (IOException e){
            logger.error(" Problems with path: " + path + " in method buildSetTariffs");
            throw new TariffException("Problems with path");
        }
        tariffs = (HashSet<Tariff>) handler.getTariffs();
        logger.info("Tariffs from SAX bulder are: \n" + tariffs);
    }
}
