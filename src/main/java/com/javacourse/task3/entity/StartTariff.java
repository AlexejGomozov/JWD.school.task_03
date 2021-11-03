package com.javacourse.task3.entity;

import java.time.YearMonth;
import java.util.Objects;

public class StartTariff extends Tariff{

   private int favoriteNumber;
    public StartTariff(){
    }

    public StartTariff(String id,String title, OperatorName operatorName, double payroll, YearMonth year, CallsType calls, SmsType sms, TarifficationType tariffication, double connectionFee, int favoriteNumber){
        super(id, title,operatorName, payroll, year, calls, sms,tariffication, connectionFee);
        this.favoriteNumber = favoriteNumber;
    }

     public int getFavoriteNumber(){
        return favoriteNumber;
     }
     public void setFavoriteNumber(int favoriteNumber){
        this.favoriteNumber = favoriteNumber;
     }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StartTariff that = (StartTariff) o;
        return favoriteNumber == that.favoriteNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), favoriteNumber);
    }

    @Override
    public String toString() {
      final StringBuilder sb = new StringBuilder("StartTariff{");
      sb.append(super.toString());
        sb.append(", favoriteNumber = ").append(favoriteNumber);
      sb.append('}');
      return sb.toString();
    }
}
