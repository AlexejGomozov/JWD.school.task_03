package com.javacourse.task3.entity;

import java.time.YearMonth;
import java.util.Objects;

public class BusinessTariff extends Tariff{


    private double callPriceForInternationalConnection;

    public BusinessTariff(){
    }
    public BusinessTariff(String id, OperatorName operatorName, double payroll, YearMonth year, CallsType calls, SmsType sms, TarifficationType tariffication, double connectionFee, double callPriceForInternationalConnection){
        super(id, operatorName, payroll, year, calls, sms, tariffication, connectionFee);
        this.callPriceForInternationalConnection = callPriceForInternationalConnection;
    }
     public double getCallPriceForInternationalConnection(){
        return callPriceForInternationalConnection;
    }
    public void setCallPriceForInternationalConnection(double callPriceForInternationalConnection){
        this.callPriceForInternationalConnection = callPriceForInternationalConnection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BusinessTariff that = (BusinessTariff) o;
        return Double.compare(that.callPriceForInternationalConnection, callPriceForInternationalConnection) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), callPriceForInternationalConnection);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BusinessTariff{");
        sb.append(super.toString());
        sb.append(", callPriceForInternationalConnection=").append(callPriceForInternationalConnection);
        sb.append("} ");
        return sb.toString();
    }
}
