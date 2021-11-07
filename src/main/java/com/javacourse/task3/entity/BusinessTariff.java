package com.javacourse.task3.entity;

public class BusinessTariff extends Tariff{

    private double callPriceForInternationalConnection;

    public BusinessTariff(){
        super();
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
        int result = 1;
        result +=31*result + super.hashCode();
        result +=31*result + Double.hashCode(callPriceForInternationalConnection);
        return result;
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
