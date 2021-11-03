package com.javacourse.task3.entity;

import java.time.YearMonth;
import java.util.Objects;

public class UnlimitedTariff extends Tariff{

    private double unlimitedCallsToAnyNetwork;

    public UnlimitedTariff(){
    }
    public UnlimitedTariff(String id, String title, OperatorName operatorName, double payroll, YearMonth year, CallsType calls, SmsType sms, TarifficationType tariffication, double connectionFee, double unlimitedCallsToAnyNetwork){
        super(id, title, operatorName, payroll, year, calls, sms,tariffication, connectionFee);
        this.unlimitedCallsToAnyNetwork = unlimitedCallsToAnyNetwork;
    }

    public double getUnlimitedCallsToAnyNetwork(){
        return unlimitedCallsToAnyNetwork;
    }
    public void setUnlimitedCallsToAnyNetwork(double unlimitedCallsToAnyNetwork){
        this.unlimitedCallsToAnyNetwork = unlimitedCallsToAnyNetwork;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UnlimitedTariff that = (UnlimitedTariff) o;
        return Double.compare(that.unlimitedCallsToAnyNetwork, unlimitedCallsToAnyNetwork) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), unlimitedCallsToAnyNetwork);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UnlimitedTariff{");
        sb.append(super.toString());
        sb.append("unlimitedCallsToAnyNetwork=").append(unlimitedCallsToAnyNetwork);
        sb.append("} ");
        return sb.toString();
    }
}
