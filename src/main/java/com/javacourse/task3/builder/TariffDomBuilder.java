package com.javacourse.task3.builder;

import com.javacourse.task3.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.YearMonth;
import java.util.List;

import static com.javacourse.task3.builder.TariffXmlTag.*;

public class TariffDomBuilder extends AbstractTariffBuilder{

    private final static Logger logger = LogManager.getLogger();
    private DocumentBuilder documentBuilder;

    public TariffDomBuilder(){
        super();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            documentBuilder = factory.newDocumentBuilder();
        }catch (ParserConfigurationException e){
            logger.error("Error in TariffDomBuilder: " + e.getMessage());
        }
    }

    @Override
    public List<Tariff> buildSetTariffs(String filePath){
        Document document;
        try {
             document = documentBuilder.parse( filePath);
             Element root = document.getDocumentElement();

                    String startTariff = START_TARIFF.toString();
            String familyTariff = FAMILY_TARIFF.toString();
            String businessTariff = BUSINESS_TARIFF.toString();
            String unlimitedTariff = UNLIMITED_TARIFF.toString();

            NodeList startTariffs = root.getElementsByTagName(startTariff);
            NodeList familyTariffs = root.getElementsByTagName(familyTariff);
            NodeList businessTariffs = root.getElementsByTagName(businessTariff);
            NodeList unlimitedTariffs = root.getElementsByTagName(unlimitedTariff);

            for(int i = 0; i< startTariffs.getLength(); i++){

                Element element = (Element) startTariffs.item(i);
                Tariff tariff = buildTariff(element);
                tariffs.add(tariff);
            }

            for(int i = 0; i< familyTariffs.getLength(); i++){
                Element element = (Element) familyTariffs.item(i);
                Tariff tariff = buildTariff(element);
                tariffs.add(tariff);
            }
            for(int i = 0; i< businessTariffs.getLength(); i++){
                Element element = (Element) businessTariffs.item(i);
                Tariff tariff = buildTariff(element);
                tariffs.add(tariff);
            }
            for(int i = 0; i< unlimitedTariffs.getLength(); i++){
                Element element = (Element) unlimitedTariffs.item(i);
                Tariff tariff = buildTariff(element);
                tariffs.add(tariff);
            }
        } catch (IOException | SAXException  e) {
            logger.error("Any SAX or IO Exception during parsing {}", filePath);
        }
        logger.info("Tariffs were successfully built. File: {}"+ filePath);
        return tariffs;
    }
    Tariff tariff;
    public Tariff buildTariff(Element element){

        if(element.getTagName().equals(START_TARIFF.toString())) tariff = new StartTariff();
        if(element.getTagName().equals(FAMILY_TARIFF.toString())) tariff = new FamilyTariff();
        if(element.getTagName().equals(BUSINESS_TARIFF.toString())) tariff = new BusinessTariff();
        if(element.getTagName().equals(UNLIMITED_TARIFF.toString())) tariff = new UnlimitedTariff();

        tariff.setId(element.getAttribute("id"));

        if(element.getAttribute("title")!=null){
            tariff.setTitle(element.getAttribute("title"));
        }

        String data = getElementTextContent(element, OPERATOR_NAME.toString());
        tariff.setOperatorName(OperatorName.valueOf(data));
        data = getElementTextContent(element, PAYROLL.toString());
        tariff.setPayroll(Double.parseDouble(data));
        data = getElementTextContent(element, YEAR.toString());
        tariff.setYear(YearMonth.parse(data));
        data = getElementTextContent(element, CONNECTION_FEE.toString());
        tariff.setConnectionFee(Double.parseDouble(data));

        CallsType callsType = tariff.getCallsType();
        data = getElementTextContent(element, CALL_PRICES_ON_NET.toString());
        callsType.setCall_prices_on_net(Double.parseDouble(data));

        data = getElementTextContent(element, CALL_PRICES_ON_ANOTHER_NETWORK.toString());
        callsType.setCall_prises_on_another_network(Double.parseDouble(data));

        data = getElementTextContent(element, CALL_PRICES_TO_LANDLINE_PHONES.toString());
        callsType.setCall_prices_to_landline_phones(Double.parseDouble(data));

        if (element.getTagName().equals(BUSINESS_TARIFF.toString())){
           data =getElementTextContent(element, CALL_PRICE_FOR_INTERNATIONAL_CONNECTION.toString());
           BusinessTariff businessTariff = (BusinessTariff) tariff;
           businessTariff.setCallPriceForInternationalConnection(Double.parseDouble(data));
        }

        if(element.getTagName().equals(UNLIMITED_TARIFF.toString())) {
           data = getElementTextContent(element, UNLIMITED_CALLS_TO_ANY_NETWORK.toString());
           UnlimitedTariff unlimitedTariff = (UnlimitedTariff) tariff;
           unlimitedTariff.setUnlimitedCallsToAnyNetwork(Double.parseDouble(data));
        }
        data = getElementTextContent(element, TARIFFICATION.toString());
        tariff.setTarifficationType(TarifficationType.valueOf(data));

        data = getElementTextContent(element, SMS.toString());
        tariff.setSmsType(SmsType.valueOf(data));

        if(element.getTagName().equals(START_TARIFF.toString())) {
            data = getElementTextContent(element, FAVORITE_NUMBER.toString());
            StartTariff startTariff = (StartTariff) tariff;
            startTariff.setFavoriteNumber(Integer.valueOf(data));
        }
        if(element.getTagName().equals(FAMILY_TARIFF.toString())) {
            data = getElementTextContent(element, FAMILY_NUMBER.toString());
            FamilyTariff familyTariff = (FamilyTariff) tariff;
            familyTariff.setFamilyNumber(Integer.parseInt(data));
}
        logger.info("DOM-11111111111111 tarif-======================================== " + tariff.toString());
    return tariff;
    }

    private String getElementTextContent(Element element, String name){
        NodeList nodeList = element.getElementsByTagName(name);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }
}

