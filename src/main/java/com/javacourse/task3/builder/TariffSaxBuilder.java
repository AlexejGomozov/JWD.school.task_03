package com.javacourse.task3.builder;

import com.javacourse.task3.entity.Tariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;


public class TariffSaxBuilder extends AbstractTariffBuilder {

    private static final Logger logger = LogManager.getLogger();
    private final SAXParserFactory factory;
    private XMLReader reader;

    public TariffSaxBuilder() {
        super();
       factory = SAXParserFactory.newInstance();
    }

    @Override
    public List<Tariff> buildSetTariffs(String path) {
        try {
            SAXParser saxParser = factory.newSAXParser();

            reader = saxParser.getXMLReader();
            TariffHandler handler = new TariffHandler();

            reader.setContentHandler(handler);
            reader.parse(path);
            tariffs =  handler.getTariffs();

        } catch (ParserConfigurationException | SAXException e) {
            logger.error("Error in TariffSaxBuilder: " + e.getMessage());
        } catch (IOException e) {
            logger.error(" Problems with path: " + path + " in method buildSetTariffs");
        }
        logger.info("Tariffs were successfully built. File: {}"+ path);
        return tariffs;
    }
}
