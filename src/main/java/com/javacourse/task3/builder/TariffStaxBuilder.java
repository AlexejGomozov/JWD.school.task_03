package com.javacourse.task3.builder;

import com.javacourse.task3.entity.*;
import com.javacourse.task3.exception.TariffException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.xml.stream.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.YearMonth;
import java.util.List;
import static com.javacourse.task3.builder.TariffXmlTag.*;

public class TariffStaxBuilder extends AbstractTariffBuilder {

    private static final Logger logger = LogManager.getLogger();
    private XMLInputFactory inputFactory;
    private XMLStreamReader reader;
    private static final char HYPHEN = '-';
    private static final char UNDERSCORE = '_';
    private Tariff tariff;

    public TariffStaxBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public List<Tariff> buildSetTariffs(String filePath) throws TariffException {

 try  (FileInputStream inputStream = new FileInputStream(new File(filePath))){
            String name;
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(START_TARIFF.toString())
                            || name.equals(FAMILY_TARIFF.toString())
                            || name.equals(BUSINESS_TARIFF.toString())
                            || name.equals(UNLIMITED_TARIFF.toString())) {
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
        return tariffs;
        }

        private Tariff buildTariff(XMLStreamReader reader) throws XMLStreamException, TariffException{

            if(reader.getLocalName().equals(START_TARIFF.toString()))     tariff = new StartTariff();
            if(reader.getLocalName().equals(FAMILY_TARIFF.toString()))     tariff = new FamilyTariff();
            if(reader.getLocalName().equals(BUSINESS_TARIFF.toString()))    tariff = new BusinessTariff();
            if(reader.getLocalName().equals(UNLIMITED_TARIFF.toString()))    tariff = new UnlimitedTariff();

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
                    if(name.equals(START_TARIFF.toString())
                            || name.equals(FAMILY_TARIFF.toString())
                            || name.equals(BUSINESS_TARIFF.toString())
                            || name.equals(UNLIMITED_TARIFF.toString())) {
                           return tariff; }
                }
            }
        }
        return tariff;
    }
        private void getStartElement(XMLStreamReader reader, Tariff tariff) throws XMLStreamException, TariffException{

        String name = reader.getLocalName();
        String data = getXMLText(reader);
        TariffXmlTag currentTag = TariffXmlTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE));
        switch (currentTag){
            case OPERATOR_NAME -> tariff.setOperatorName(OperatorName.valueOf(data));
            case PAYROLL -> tariff.setPayroll(Double.parseDouble(data));
            case YEAR -> tariff.setYear(YearMonth.parse(data));
            case CONNECTION_FEE -> tariff.setConnectionFee(Double.parseDouble(data));
            case CALLS -> {}
            case CALL_PRICES_ON_NET -> tariff.getCallsType().setCall_prices_on_net(Double.parseDouble(data));
            case CALL_PRICES_ON_ANOTHER_NETWORK -> tariff.getCallsType().setCall_prises_on_another_network(Double.parseDouble(data));
            case CALL_PRICES_TO_LANDLINE_PHONES -> tariff.getCallsType().setCall_prices_to_landline_phones(Double.parseDouble(data));
            case CALL_PRICE_FOR_INTERNATIONAL_CONNECTION -> {
                BusinessTariff businessTariff = (BusinessTariff) tariff;
                businessTariff.setCallPriceForInternationalConnection(Double.parseDouble(data)); }
            case UNLIMITED_CALLS_TO_ANY_NETWORK -> {
                UnlimitedTariff unlimitedTariff = (UnlimitedTariff) tariff;
                unlimitedTariff.setUnlimitedCallsToAnyNetwork(Double.parseDouble(data)); }
            case SMS -> tariff.setSmsType(SmsType.valueOf(data));
            case TARIFFICATION -> tariff.setTarifficationType(TarifficationType.valueOf(data));
            case FAVORITE_NUMBER -> {
                StartTariff startTariff = (StartTariff) tariff;
                startTariff.setFavoriteNumber(Integer.parseInt(data)); }
            case FAMILY_NUMBER -> {
                FamilyTariff familyTariff = (FamilyTariff) tariff;
                familyTariff.setFamilyNumber(Integer.parseInt(data)); }
            default -> {
                logger.error("Error while mhetod getStartElement with bad tag" + currentTag);
                throw new TariffException("Error while mhetod getStartElement with bad tag" + currentTag);
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
