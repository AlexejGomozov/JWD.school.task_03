package com.javacourse.task3.entity;

import java.util.Objects;

public class StartTariff extends Tariff{
   private int favoriteNumber;

    public StartTariff(){
        super();
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
        int result = 1;
        result = 31*result + super.hashCode();
        result = 31*result + favoriteNumber;
        return result;
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
