package com.javacourse.task3.builder;

import com.javacourse.task3.entity.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.YearMonth;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class TariffHandler extends DefaultHandler {
    //private static final char HYPHEN = '-';
   // private static final char UNDERSCORE = '_';
    private  Set<Tariff> tariffs;
    private EnumSet<TariffTag> withText;
    private Tariff currentTariff;
    private TariffTag currentTag;

    public TariffHandler(){
        tariffs = new HashSet<>();
        withText = EnumSet.range(TariffTag.OPERATOR_NAME,TariffTag.CONNECTION_FEE);
    }
    public Set<Tariff> getTariffs(){
        return tariffs;
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){
        String StartTariffTag = TariffTag.START_TARIFF.toString();
        String FamilyTariffTag = TariffTag.FAMILY_TARIFF.toString();
        String BusinessTariffTag = TariffTag.BUSINESS_TARIFF.toString();
        String UnlimitedTariffTag = TariffTag.UNLIMITED_TARIFF.toString();

        if(StartTariffTag.equals(qName) || FamilyTariffTag.equals(qName) || BusinessTariffTag.equals(qName) || UnlimitedTariffTag.equals(qName)){
            if(StartTariffTag.equals(qName)) {currentTariff = new StartTariff(); fillAttrValue(attributes);}
            if(FamilyTariffTag.equals(qName)) {currentTariff = new FamilyTariff(); fillAttrValue(attributes);}
            if(BusinessTariffTag.equals(qName)) {currentTariff = new BusinessTariff(); fillAttrValue(attributes);}
            if(UnlimitedTariffTag.equals(qName)) {currentTariff = new UnlimitedTariff(); fillAttrValue(attributes);}
        }else {
            TariffTag temp = TariffTag.valueOf((qName.toUpperCase().replace("-","_")));
              if(withText.contains(temp)) {currentTag = temp;}
        }
    }
    @Override
    public void endElement(String uri, String localName, String qName) {
        if (TariffTag.START_TARIFF.getTitle().equals(qName) || TariffTag.FAMILY_TARIFF.getTitle().equals(qName) || TariffTag.BUSINESS_TARIFF.getTitle().equals(qName) || TariffTag.UNLIMITED_TARIFF.getTitle().equals(qName)) {
       tariffs.add(currentTariff);
        }
    }
    @Override
    public void characters(char[] ch, int start, int length){
        String data = new String(ch, start, length).strip();
        if(currentTag != null){
            switch (currentTag){
                case SMS -> currentTariff.setSmsType(SmsType.valueOf(data));
                case YEAR -> currentTariff.setYear(YearMonth.parse(data));
                case CALLS -> currentTariff.setCallsType(CallsType.valueOf(data));
                case TARIFFICATION -> currentTariff.setTarifficationType(TarifficationType.valueOf(data));
                case PAYROLL -> currentTariff.setPayroll(Double.parseDouble(data));
                default -> throw new EnumConstantNotPresentException(
                        currentTag.getDeclaringClass(), currentTag.name());
            }
        }
        currentTag = null;
    }

    private void fillAttrValue(Attributes attributes){
        if(attributes.getLength()==1){
            currentTariff.setId(attributes.getValue(0));
            currentTariff.setOperatorName(OperatorName.A1);
        }
        if(attributes.getLength()==2){
            String qAttrName_0 = attributes.getQName(0);
            if(qAttrName_0.equals(TariffTag.OPERATOR_NAME.getTitle())){
                OperatorName operatorName = OperatorName.valueOf(attributes.getValue(0).toUpperCase());
                currentTariff.setOperatorName(operatorName);
                currentTariff.setCallsType(CallsType.valueOf(attributes.getValue(1)));
            }else{
                currentTariff.setCallsType(CallsType.valueOf(attributes.getValue(0)));
                OperatorName operatorName = OperatorName.valueOf(attributes.getValue(1).toUpperCase());
                currentTariff.setOperatorName(operatorName);
            }
        }
    }
}
