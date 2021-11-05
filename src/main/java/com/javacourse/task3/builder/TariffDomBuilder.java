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
import java.io.File;
import java.io.IOException;
import java.time.YearMonth;
import java.util.Set;

import static com.javacourse.task3.builder.TariffXmlTag.*;


public class TariffDomBuilder extends AbstractTariffBuilder{

    private final static Logger logger = LogManager.getLogger();
    private static final char UNDERSCORE = '_';
    private static final char HYPHEN = '-';

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
    public Set<Tariff> buildSetTariffs(String filePath){ //throws TariffException {
        Document document;
        try {
//Cannot invoke "java.net.URL.getPath()" because the return value of "java.lang.ClassLoader.getResource(String)" is null
           //document = documentBuilder.parse(getClass().getClassLoader().getResource(filePath).getPath());//почему здесь null?? выдает null
            document = documentBuilder.parse( filePath);
            // document = documentBuilder.parse( new File(filePath));
            logger.error("5555555========================================"+ document);
                     Element root = document.getDocumentElement();

//////??????????????????
            /* NodeList startTar1 = document.getDocumentElement().getElementsByTagName("business-tariff");
            logger.error("888888888========================================"+ startTar1.getLength());
           NodeList startTar = document.getDocumentElement().getChildNodes();//.item(1);//getElementsByTagName(START_TARIFF.toString());//.getChildNodes();
            logger.error("111111111========================================" + startTar.getLength());
           Node node;
            //String stringNode;
            for(int i= 0; i<startTar.getLength(); i++){
                node = startTar.item(i);//.getNodeName();//.getNodeName();//.getTextContent();
                String stringNode = node.getNodeName();


                if(stringNode.equals(START_TARIFF.toString())){
                    logger.error("777777777========================================" +  node);
                }
*/
                //if(element.getTagName().equals(START_TARIFF.toString())) tariff = new StartTariff();

            /*    Element element = (Element) startTar.item(i);

                Tariff tariff = buildTariff(element);

                tariffs.add(tariff);

                //if(startTar.item(i).getLocalName() instanceof Tariff){

                }
*/

           // if(startTar.item(i) instanceof Tariff){}
//            NodeList startTar = document.getDocumentElement().getChildNodes();
//            logger.error("111111111========================================" + startTar.getLength());//25
            //String startTar = document.getDocumentElement().getNodeName();
            //logger.error("111111111========================================" + startTar.length()); //7
//     /////////       NodeList startTariffs = document.getElementsByTagName(START_TARIFF.toString());
//            logger.error("44444444========================================" + startTariffs.getLength());
//            NodeList familyTariffs = document.getElementsByTagName(FAMILY_TARIFF.toString());
//            NodeList businessTariffs = document.getElementsByTagName(BUSINESS_TARIFF.toString());
//     ////////               NodeList unlimitedTariffs = document.getElementsByTagName(UNLIMITED_TARIFF.toString());



                    String startTariff = START_TARIFF.toString();
            String familyTariff = FAMILY_TARIFF.toString();
            String businessTariff = BUSINESS_TARIFF.toString();
            String unlimitedTariff = UNLIMITED_TARIFF.toString();

            NodeList startTariffs = root.getElementsByTagName(startTariff);//getChildNodes(); //getElementsByTagName(startTariff);
            NodeList familyTariffs = root.getElementsByTagName(familyTariff);//getChildNodes();
            NodeList businessTariffs = root.getElementsByTagName(businessTariff);
            NodeList unlimitedTariffs = root.getElementsByTagName(unlimitedTariff);

            logger.info("9999999======================================== " + startTariffs.item(0).getTextContent());
            logger.info("222222======================================== " + startTariffs.getLength());
            logger.info("333333======================================== " + familyTariffs.getLength());

            for(int i = 0; i< startTariffs.getLength(); i++){

                Element element = (Element) startTariffs.item(i);

                Tariff tariff = buildTariff(element);
         // нет ничего       logger.info("888888888======================================== " + tariff.toString());
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
        } catch (IOException | SAXException  e) {  ///// нужен ли здесь NullPointerExceptoon ???
            logger.error("Any SAX or IO Exception during parsing {}", filePath);
            //throw new TariffException("Error occured while parsing file" + filePath);
        }
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

        data = getElementTextContent(element, CALL_PRICES_TO_LANDLINE_PHONES.toString());
        callsType.setCall_prices_to_landline_phones(Double.parseDouble(data));   // null ничего совсем не показывает

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
        logger.info("888888888======================================== " + tariff.toString());
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

