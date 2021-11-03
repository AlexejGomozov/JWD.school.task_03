package com.javacourse.task3.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import org.xml.sax.SAXException;

public class TariffValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final String SCHEMA_NAME = "files.tariffs.xsd";

    public static boolean validateTariffXmlFile(String fileName){

        boolean result = false;
        String language = XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(SCHEMA_NAME);

        try{
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);
            validator.validate(source);
            result = true;
            logger.info("File: {} is not valid; message: {}", fileName);

        }catch(SAXException e){
            logger.error("{} or {} isn't correct of valid", fileName, SCHEMA_NAME);
        }catch (IOException e){
            logger.error("{} can't be read", fileName);
        }
         return result;
    }
}
