package com.javacourse.task3.builder;

import com.javacourse.task3.entity.*;
import com.javacourse.task3.exception.TariffException;
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


public class TariffDomBuilder extends AbstractTariffBuilder{

    private final static Logger logger = LogManager.getLogger(TariffDomBuilder.class);

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
    public void buildSetTariffs(String filePath) throws TariffException {
        Document document;
        try {
            document = documentBuilder.parse(filePath);
            Element root = document.getDocumentElement();

            String startTariff = TariffTag.START_TARIFF.toString();
            String familyTariff = TariffTag.FAMILY_TARIFF.toString();
            String businessTariff = TariffTag.BUSINESS_TARIFF.toString();
            String unlimitedTariff = TariffTag.UNLIMITED_TARIFF.toString();

            NodeList startTariffs = root.getElementsByTagName(startTariff);
            NodeList familyTariffs = root.getElementsByTagName(familyTariff);
            NodeList businessTariffs = root.getElementsByTagName(businessTariff);
            NodeList unlimitedTariffs = root.getElementsByTagName(unlimitedTariff);

            for(int i = 0; i< startTariffs.getLength(); i++){
                Element element = (Element) startTariffs.item(i);
                Tariff tariff = new StartTariff();
                buildStartTariff(element, tariff);
                tariffs.add(tariff);
            }
            for(int i = 0; i< familyTariffs.getLength(); i++){
                Element element = (Element) familyTariffs.item(i);
                Tariff tariff = new FamilyTariff();
                buildFamilyTariff(element, tariff);
                tariffs.add(tariff);
            }
            for(int i = 0; i< businessTariffs.getLength(); i++){
                Element element = (Element) businessTariffs.item(i);
                Tariff tariff = new BusinessTariff();
                buildBusinessTariff(element, tariff);
                tariffs.add(tariff);
            }
            for(int i = 0; i< unlimitedTariffs.getLength(); i++){
                Element element = (Element) unlimitedTariffs.item(i);
                Tariff tariff = new UnlimitedTariff();
                buildUnlimitedTariff(element, tariff);
                tariffs.add(tariff);
            }
        } catch (IOException | SAXException e) {
            logger.error("Any SAX or IO Exception during parsing {}", filePath);
            throw new TariffException("Error occured while parsing file" + filePath);
        }
    }
       private void buildTariff(Element element, Tariff tariff){
       tariff.setId(element.getAttribute("id"));
       if(element.getAttribute("title")!=null){tariff.setTitle(element.getAttribute("title"));}
       CallsType callsType;
       String type = element.getAttribute("type");
       switch(type){
           case "CALL_PRICE_FOR_INTERNATIONAL_CONNECTION" -> callsType = CallsType.CALL_PRICE_FOR_INTERNATIONAL_CONNECTION;
           case "CALL_PRICES_ON_NET" -> callsType = CallsType.CALL_PRICES_ON_NET;
           case "UNLIMITED_CALLS_TO_ANY_NETWORK" -> callsType = CallsType.UNLIMITED_CALLS_TO_ANY_NETWORK;
           case "CALL_PRICES_ON_ANOTHER_NETWORK" -> callsType = CallsType.CALL_PRICES_ON_ANOTHER_NETWORK;
           case "CALL_PRICES_T0_LANDLINE_PHONES" -> callsType = CallsType.CALL_PRICES_T0_LANDLINE_PHONES;
           default -> callsType = CallsType.CALL_PRICES_ON_NET;
       }
       tariff.setCallsType(callsType);
       String data = getElementTextContent(element, TariffTag.OPERATOR_NAME.toString());
       tariff.setOperatorName(OperatorName.valueOf(data));
       data = getElementTextContent(element, TariffTag.TARIFFICATION.toString());
       tariff.setTarifficationType(TarifficationType.valueOf(data));
       data = getElementTextContent(element, TariffTag.SMS.toString());
       tariff.setSmsType(SmsType.valueOf(data));
       data = getElementTextContent(element, TariffTag.PAYROLL.toString());
       tariff.setPayroll(Double.parseDouble(data));
       data = getElementTextContent(element, TariffTag.YEAR.toString());
       tariff.setYear(YearMonth.parse(data));

       }
    private String getElementTextContent(Element element, String name){
        NodeList nodeList = element.getElementsByTagName(name);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }

    private void buildStartTariff(Element element, Tariff tariff){
        buildTariff(element, tariff);
        ((StartTariff) tariff).setFavoriteNumber(Integer.parseInt(getElementTextContent(element, "favorite-number")));
    }
    private void buildFamilyTariff(Element element, Tariff tariff){
        buildTariff(element, tariff);
        ((FamilyTariff) tariff).setFamilyNumber(Integer.parseInt(getElementTextContent(element, "family-number")));
    }
    private void buildBusinessTariff(Element element, Tariff tariff){
        buildTariff(element, tariff);
        ((BusinessTariff) tariff).setCallPriceForInternationalConnection(Double.parseDouble(getElementTextContent(element, "call-price-for-international-connection")));
    }
    private void buildUnlimitedTariff(Element element, Tariff tariff){
        buildTariff(element, tariff);
        ((UnlimitedTariff) tariff).setUnlimitedCallsToAnyNetwork(Double.parseDouble(getElementTextContent(element, "unlimited-calls-to-any-network")));
    }
    }

