package com.javacourse.task3.entity;

public class UnlimitedTariff extends Tariff{

    private double unlimitedCallsToAnyNetwork;

    public UnlimitedTariff(){
        super();
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
        int result = 1;
        result = 31*result + super.hashCode();
        result = 31*result + (int)unlimitedCallsToAnyNetwork;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UnlimitedTariff{");
        sb.append(super.toString());
        sb.append(" unlimitedCallsToAnyNetwork=").append(unlimitedCallsToAnyNetwork);
        sb.append("} ");
        return sb.toString();
    }
}
