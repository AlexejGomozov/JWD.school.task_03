package com.javacourse.task3.builder;

import com.javacourse.task3.entity.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.YearMonth;
import java.util.*;
import static com.javacourse.task3.builder.TariffXmlTag.*;

public class TariffHandler extends DefaultHandler {

    private static final char HYPHEN = '-';
    private static final char UNDERSCORE = '_';

    private List<Tariff> tariffs;
    private EnumSet<TariffXmlTag> withText;
    private Tariff currentTariff;
    private TariffXmlTag currentTag;

    public TariffHandler(){
        tariffs = new ArrayList<>();
        withText = EnumSet.range(OPERATOR_NAME,FAMILY_NUMBER);
    }
    public List<Tariff> getTariffs(){
        return tariffs;
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        String StartTariffTag = START_TARIFF.toString();
        String FamilyTariffTag = FAMILY_TARIFF.toString();
        String BusinessTariffTag = BUSINESS_TARIFF.toString();
        String UnlimitedTariffTag = UNLIMITED_TARIFF.toString();

        if (!qName.toUpperCase().equals(TARIFFS)) {
            if (StartTariffTag.equals(qName)
                    || FamilyTariffTag.equals(qName)
                    || BusinessTariffTag.equals(qName)
                    || UnlimitedTariffTag.equals(qName)) {

                if (StartTariffTag.equals(qName)) {
                    currentTariff = new StartTariff(); }
                if (FamilyTariffTag.equals(qName)) {
                    currentTariff = new FamilyTariff(); }
                if (BusinessTariffTag.equals(qName)) {
                    currentTariff = new BusinessTariff(); }
                if (UnlimitedTariffTag.equals(qName)) {
                    currentTariff = new UnlimitedTariff(); }

                currentTariff.setId(attributes.getValue("id"));
                if (attributes.getValue("title") != null) {
                    currentTariff.setTitle(attributes.getValue("title"));
                }
            } else {
                TariffXmlTag temp = TariffXmlTag.valueOf((qName.toUpperCase().replace(HYPHEN, UNDERSCORE)));
                if (withText.contains(temp)) {
                    currentTag = temp;
                }
            }
        }
    }
    @Override
    public void endElement(String uri, String localName, String qName) {
        if (START_TARIFF.toString().equals(qName)
                || FAMILY_TARIFF.toString().equals(qName)
                || BUSINESS_TARIFF.toString().equals(qName)
                || UNLIMITED_TARIFF.toString().equals(qName)) {
             tariffs.add(currentTariff);
        }
    }
    @Override
    public void characters(char[] ch, int start, int length){

        String data = new String(ch, start, length).trim();
        if(currentTag != null){
            switch (currentTag) {

                case OPERATOR_NAME -> currentTariff.setOperatorName(OperatorName.valueOf(data));
                case PAYROLL -> currentTariff.setPayroll(Double.parseDouble(data));
                case YEAR -> currentTariff.setYear(YearMonth.parse(data));
                case CONNECTION_FEE -> currentTariff.setConnectionFee(Double.parseDouble(data));
                case CALLS ->{}
                case CALL_PRICES_ON_NET -> currentTariff.getCallsType().setCall_prices_on_net(Double.parseDouble(data));
                case CALL_PRICES_ON_ANOTHER_NETWORK -> currentTariff.getCallsType().setCall_prises_on_another_network(Double.parseDouble(data));
                case CALL_PRICES_TO_LANDLINE_PHONES -> currentTariff.getCallsType().setCall_prices_to_landline_phones(Double.parseDouble(data));
                case CALL_PRICE_FOR_INTERNATIONAL_CONNECTION -> {
                    BusinessTariff businessTariff = (BusinessTariff) currentTariff;
                    businessTariff.setCallPriceForInternationalConnection(Double.parseDouble(data)); }
                case UNLIMITED_CALLS_TO_ANY_NETWORK -> {
                    UnlimitedTariff unlimitedTariff = (UnlimitedTariff) currentTariff;
                    unlimitedTariff.setUnlimitedCallsToAnyNetwork(Double.parseDouble(data)); }
                case SMS -> currentTariff.setSmsType(SmsType.valueOf(data));
                case TARIFFICATION -> currentTariff.setTarifficationType(TarifficationType.valueOf(data));
                case FAVORITE_NUMBER -> {
                    StartTariff startTariff = (StartTariff) currentTariff;
                    startTariff.setFavoriteNumber(Integer.parseInt(data)); }
                case FAMILY_NUMBER -> {
                    FamilyTariff familyTariff = (FamilyTariff) currentTariff;
                    familyTariff.setFamilyNumber(Integer.parseInt(data)); }
                default -> throw new EnumConstantNotPresentException(
                        currentTag.getDeclaringClass(), currentTag.name());
            }
        }
        currentTag = null;
    }
}
