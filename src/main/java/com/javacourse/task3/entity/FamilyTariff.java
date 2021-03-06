package com.javacourse.task3.entity;

public class FamilyTariff extends Tariff{
    int familyNumber;

    public FamilyTariff(){
        super();
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
        int result = 1;
        result += 31*result + super.hashCode();
        result += 31*result + familyNumber;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("FamilyTariff{");
        sb.append(super.toString());
        sb.append(" familyNumber=").append(familyNumber);
        sb.append("} ");
        return sb.toString();
    }
}
