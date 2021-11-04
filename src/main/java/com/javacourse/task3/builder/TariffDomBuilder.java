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

import static com.javacourse.task3.builder.TariffXmlTag.*;


public class TariffDomBuilder extends AbstractTariffBuilder{

    private final static Logger logger = LogManager.getLogger(TariffDomBuilder.class);
    private static final char UNDERSCORE = '_';
    private static final char HYPHEN = '-';

    private DocumentBuilder documentBuilder;

    public TariffDomBuilder(){
//        super();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            documentBuilder = factory.newDocumentBuilder();
        }catch (ParserConfigurationException e){
            logger.error("Error in TariffDomBuilder: " + e.getMessage());
        }
    }

    @Override
    public void buildSetTariffs(String filePath){ //throws TariffException {
        Document document;
        try {
            document = documentBuilder.parse(getClass().getClassLoader().getResource(filePath).getPath());  //почему здесь null  ??
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
                Tariff tariff = buildTariff(element); //new StartTariff(); //buildStartTariff(element, tariff);
                tariffs.add(tariff);
            }
            for(int i = 0; i< familyTariffs.getLength(); i++){
                Element element = (Element) familyTariffs.item(i);
                Tariff tariff = buildTariff(element); //new FamilyTariff(); //buildFamilyTariff(element, tariff);
                tariffs.add(tariff);
            }
            for(int i = 0; i< businessTariffs.getLength(); i++){
                Element element = (Element) businessTariffs.item(i);
                Tariff tariff = buildTariff(element); //new BusinessTariff();//buildBusinessTariff(element, tariff);
                tariffs.add(tariff);
            }
            for(int i = 0; i< unlimitedTariffs.getLength(); i++){
                Element element = (Element) unlimitedTariffs.item(i);
                Tariff tariff = buildTariff(element); //new UnlimitedTariff();//buildUnlimitedTariff(element, tariff);
                tariffs.add(tariff);
            }
        } catch (IOException | SAXException  e) {  ///// нужен ли здесь NullPointerExceptoon ???
            logger.error("Any SAX or IO Exception during parsing {}", filePath);
            //throw new TariffException("Error occured while parsing file" + filePath);
        }
    }
    Tariff tariff;
    public Tariff buildTariff(Element element){
        //Tariff tariff = element.getTagName().equals(START_TARIFF.toString()| FAMILY_TARIFF.toString())? new
                //? new StartTariff() : new FamilyTariff();// || (element.getTagName().equals(START_TARIFF.toString())? new StartTariff() : new FamilyTariff());
        if(element.getTagName().equals(START_TARIFF.toString())) tariff = new StartTariff();
        if(element.getTagName().equals(FAMILY_TARIFF.toString())) tariff = new FamilyTariff();
        if(element.getTagName().equals(BUSINESS_TARIFF.toString())) tariff = new BusinessTariff();
        if(element.getTagName().equals(UNLIMITED_TARIFF.toString())) tariff = new UnlimitedTariff();

        tariff.setId(element.getAttribute("id"));
        if(element.getAttribute("title")!=null){
            tariff.setTitle(element.getAttribute("title"));
        }

        String data = getElementTextContent(element, OPERATOR_NAME.toString());
        tariff.setOperatorName(OperatorName.valueOf(data));    //возможно нужно (data.toUpperCase())
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
        data = getElementTextContent(element, CALL_PRICES_T0_LANDLINE_PHONES.toString());
        callsType.setCall_prices_to_landline_phones(Double.parseDouble(data));

        data =getElementTextContent(element, CALL_PRICE_FOR_INTERNATIONAL_CONNECTION.toString());
        BusinessTariff businessTariff = (BusinessTariff) tariff;
        businessTariff.setCallPriceForInternationalConnection(Double.parseDouble(data));
        data = getElementTextContent(element, UNLIMITED_CALLS_TO_ANY_NETWORK.toString());
        UnlimitedTariff unlimitedTariff = (UnlimitedTariff) tariff;
        unlimitedTariff.setUnlimitedCallsToAnyNetwork(Double.parseDouble(data));

        data = getElementTextContent(element, TARIFFICATION.toString());
        tariff.setTarifficationType(TarifficationType.valueOf(data));
        data = getElementTextContent(element, SMS.toString());
        tariff.setSmsType(SmsType.valueOf(data));

        data = getElementTextContent(element, FAVORITE_NUMBER.toString());
        StartTariff startTariff = (StartTariff) tariff;
        startTariff.setFavoriteNumber(Integer.parseInt(data));
        data = getElementTextContent(element, FAMILY_NUMBER.toString());
        FamilyTariff familyTariff = (FamilyTariff) tariff;
        familyTariff.setFamilyNumber(Integer.parseInt(data));

    return tariff;
    }
//       private void buildTariff(Element element, Tariff tariff){
//       tariff.setId(element.getAttribute("id"));
//       if(element.getAttribute("title")!=null){tariff.setTitle(element.getAttribute("title"));}
//       CallsType callsType;
//       String type = element.getAttribute("type");
//       switch(type){
//           case "CALL_PRICE_FOR_INTERNATIONAL_CONNECTION" -> callsType = CallsType.CALL_PRICE_FOR_INTERNATIONAL_CONNECTION;
//           case "CALL_PRICES_ON_NET" -> callsType = CallsType.CALL_PRICES_ON_NET;
//           case "UNLIMITED_CALLS_TO_ANY_NETWORK" -> callsType = CallsType.UNLIMITED_CALLS_TO_ANY_NETWORK;
//           case "CALL_PRICES_ON_ANOTHER_NETWORK" -> callsType = CallsType.CALL_PRICES_ON_ANOTHER_NETWORK;
//           case "CALL_PRICES_T0_LANDLINE_PHONES" -> callsType = CallsType.CALL_PRICES_T0_LANDLINE_PHONES;
//           default -> callsType = CallsType.CALL_PRICES_ON_NET;
//       }
//       tariff.setCallsType(callsType);
//       String data = getElementTextContent(element, OPERATOR_NAME.toString());
//       tariff.setOperatorName(OperatorName.valueOf(data));
//       data = getElementTextContent(element, TARIFFICATION.toString());
//       tariff.setTarifficationType(TarifficationType.valueOf(data));
//       data = getElementTextContent(element, SMS.toString());
//       tariff.setSmsType(SmsType.valueOf(data));
//       data = getElementTextContent(element, PAYROLL.toString());
//       tariff.setPayroll(Double.parseDouble(data));
//       data = getElementTextContent(element, YEAR.toString());
//       tariff.setYear(YearMonth.parse(data));
//
//       }
    private String getElementTextContent(Element element, String name){
        NodeList nodeList = element.getElementsByTagName(name);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }

//    private void buildStartTariff(Element element, Tariff tariff){
//        buildTariff(element, tariff);
//        ((StartTariff) tariff).setFavoriteNumber(Integer.parseInt(getElementTextContent(element, "favorite-number")));
//    }
//    private void buildFamilyTariff(Element element, Tariff tariff){
//        buildTariff(element, tariff);
//        ((FamilyTariff) tariff).setFamilyNumber(Integer.parseInt(getElementTextContent(element, "family-number")));
//    }
//    private void buildBusinessTariff(Element element, Tariff tariff){
//        buildTariff(element, tariff);
//        ((BusinessTariff) tariff).setCallPriceForInternationalConnection(Double.parseDouble(getElementTextContent(element, "call-price-for-international-connection")));
//    }
//    private void buildUnlimitedTariff(Element element, Tariff tariff){
//        buildTariff(element, tariff);
//        ((UnlimitedTariff) tariff).setUnlimitedCallsToAnyNetwork(Double.parseDouble(getElementTextContent(element, "unlimited-calls-to-any-network")));
//    }
    }

