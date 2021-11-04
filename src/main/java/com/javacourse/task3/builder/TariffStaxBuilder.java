package com.javacourse.task3.builder;


import com.javacourse.task3.entity.*;
import com.javacourse.task3.exception.TariffException;
import com.javacourse.task3.validator.TariffValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.YearMonth;

public class TariffStaxBuilder extends AbstractTariffBuilder {

    private static final Logger logger = LogManager.getLogger();
    private XMLInputFactory inputFactory;
    private static final char HYPHEN = '-';
    private static final char UNDERSCORE = '_';

    public TariffStaxBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public void buildSetTariffs(String filePath) throws TariffException {
        XMLStreamReader reader;
        String name;

        if (!TariffValidator.validateTariffXmlFile(filePath)) {
            throw new TariffException("File " + filePath + " isn't valid");
        }

        try (FileInputStream inputStream = new FileInputStream(new File(filePath))) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(TariffTag.START_TARIFF.toString())
                            || name.equals(TariffTag.FAMILY_TARIFF.toString())) {
                        Tariff tariff = buildTariff(reader);
                        tariffs.add(tariff);
                    }
                }
            }
            }catch(IOException e){
                logger.error("Error! with file {}; message: {}", filePath, e.getMessage());
                throw new TariffException("Error! with reading file {}" + filePath + "message: {}"+ e.getMessage());
            }catch(XMLStreamException e){
                logger.error("Error! with parsing file {}; message: {}", filePath, e.getMessage());
                throw new TariffException("Error! with file {}" + filePath + "message: {}"+ e.getMessage());
            }
        logger.info("Tariffs were successfully built. File: {}"+ filePath);
        }
        private Tariff buildTariff(XMLStreamReader reader) throws XMLStreamException, TariffException{
        Tariff tariff = reader.getLocalName().equals(TariffTag.START_TARIFF.toString())?
                new StartTariff():
                new FamilyTariff();
        tariff.setId(reader.getAttributeValue(null, "id"));
        if(reader.getAttributeValue(null,"title")!=null){
            tariff.setTitle(reader.getAttributeValue(null,"title"));
        }

        while(reader.hasNext()){
            int type = reader.next();

            switch (type){
                case XMLStreamConstants.START_ELEMENT -> getStartElement(reader, tariff);
                case XMLStreamConstants.END_ELEMENT -> {
                    String name = reader.getLocalName();
                    if(name.equals(TariffTag.START_TARIFF.toString())|| name.equals(TariffTag.FAMILY_TARIFF.toString())){return tariff;}
                }
            }
        }
        return tariff;
    }
        private void getStartElement(XMLStreamReader reader, Tariff tariff) throws XMLStreamException, TariffException{
        String name = reader.getLocalName();
        String data = getXMLText(reader);
        TariffTag currentTag = TariffTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE));
        switch (currentTag){
            case OPERATOR_NAME -> tariff.setOperatorName(OperatorName.valueOf(data));
            case TARIFF_ID -> tariff.setId(data);
            case PAYROLL -> tariff.setPayroll(Double.parseDouble(data));
            case TARIFFICATION -> tariff.setTarifficationType(TarifficationType.valueOf(data));
            case CALLS -> tariff.setCallsType(CallsType.valueOf(data));
            case YEAR -> tariff.setYear(YearMonth.parse(data));
            case SMS -> tariff.setSmsType(SmsType.valueOf(data));
            case START_TARIFF -> {StartTariff tariff1 = (StartTariff) tariff; tariff1.setFavoriteNumber(Integer.parseInt(data));}
            case FAMILY_TARIFF -> {FamilyTariff tariff1 =(FamilyTariff) tariff; tariff1.setFamilyNumber(Integer.parseInt(data));}
            default -> {
                logger.error("Error while mhetod getStartElement with bad tag" + currentTag);
                throw new TariffException("Error while mhetod getStartElement with bad tag" + currentTag);
            }
        }
    }
private void getCallTypeXml(XMLStreamReader reader, Tariff tariff) throws XMLStreamException, TariffException{
        while(reader.hasNext()){
            int type = reader.next();
            String name = reader.getLocalName();
            String data = getXMLText(reader);
            switch (type){
                case XMLStreamConstants.START_ELEMENT -> {
                    TariffTag currentTag = TariffTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE));
                    switch(currentTag){
                        case CALL_PRICES_ON_NET -> tariff.setCallsType(CallsType.valueOf(data));
                        case CALL_PRICES_ON_ANOTHER_NETWORK -> tariff.setCallsType(CallsType.valueOf(data));
                        case CALL_PRICES_T0_LANDLINE_PHONES -> tariff.setCallsType(CallsType.valueOf(data));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    if(name.equals(TariffTag.SMS.toString())){ return ;};
                }
                default -> {
                    logger.error("Error! Bad element: " + type);
                    throw new TariffException("Error! Bad element: " + type);
                }
            }
        }
}
       private  String getXMLText(XMLStreamReader reader) throws XMLStreamException{
        String text = null;
        if(reader.hasNext()){
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
