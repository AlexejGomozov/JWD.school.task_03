package com.javacourse.task3.entity;

import java.time.YearMonth;
import java.util.Objects;

public class FamilyTariff extends Tariff{
    int familyNumber;

    public FamilyTariff(){
    }
    public FamilyTariff(String id,String title, OperatorName operatorName, double payroll, YearMonth year, CallsType calls, SmsType sms, TarifficationType tariffication, double connectionFee, int familyNumber){
        super(id,title, operatorName, payroll, year, calls, sms, tariffication, connectionFee);
        this.familyNumber = familyNumber;
    }

    public int getFamilyNumber() {
        return familyNumber;
    }
    public void setFamilyNumber(int familyNumber){
        this.familyNumber = familyNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FamilyTariff that = (FamilyTariff) o;
        return familyNumber == that.familyNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), familyNumber);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("FamilyTariff{");
        sb.append(super.toString());
        sb.append("familyNumber=").append(familyNumber);
        sb.append("} ");
        return sb.toString();
    }
}
