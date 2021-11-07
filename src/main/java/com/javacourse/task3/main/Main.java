package com.javacourse.task3.main;

import com.javacourse.task3.builder.AbstractTariffBuilder;
import com.javacourse.task3.builder.TariffBuilderFactory;
import com.javacourse.task3.entity.Tariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.javacourse.task3.exception.TariffException;
import java.util.List;


public class Main{

    private final static Logger logger = LogManager.getLogger();
    private static final String RELATIVE_FILE_PATH = "resources/tariffs.xml";
    private static final String RELATIVE_SCHEMA_PATH = "resources/tariffs.xsd";


    public static void main(String[] args) throws TariffException {

        try {
            AbstractTariffBuilder domBuilder = TariffBuilderFactory.createTariffBuilder("dom");
             AbstractTariffBuilder saxBuilder = TariffBuilderFactory.createTariffBuilder("sax");
            AbstractTariffBuilder staxBuilder = TariffBuilderFactory.createTariffBuilder("stax");

           List<Tariff> tariff1 = domBuilder.buildSetTariffs(RELATIVE_FILE_PATH );
           logger.info("10101010101010101========================================"+  tariff1);

           List<Tariff> tariff2 = saxBuilder.buildSetTariffs(RELATIVE_FILE_PATH);
            logger.info("202020202020202========================================" +  tariff2);

            List<Tariff> tariff3 = staxBuilder.buildSetTariffs(RELATIVE_FILE_PATH);
             logger.info("30303030303030303========================================" +  tariff3);

              } catch(TariffException e){
                logger.fatal(e.getMessage());
              }
        }
}
