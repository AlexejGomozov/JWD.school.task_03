package com.javacourse.task3.entity;

public class CallsType {

    private double call_prices_on_net;
    private double call_prises_on_another_network;
    private double call_prices_to_landline_phones;

    public CallsType(){
    }
    public CallsType(double call_prices_on_net, double call_prises_on_another_network, double call_prices_to_landline_phones){
        this.call_prices_on_net = call_prices_on_net;
        this.call_prises_on_another_network = call_prises_on_another_network;
        this.call_prices_to_landline_phones = call_prices_to_landline_phones;
    }

    public double getCall_prices_on_net() {
        return call_prices_on_net;
    }
    public void setCall_prices_on_net(double call_prices_on_net) {
        this.call_prices_on_net = call_prices_on_net;
    }

    public double getCall_prises_on_another_network() {
        return call_prises_on_another_network;
    }
    public void setCall_prises_on_another_network(double call_prises_on_another_network) {
        this.call_prises_on_another_network = call_prises_on_another_network;
    }

    public double getCall_prices_to_landline_phones() {
        return call_prices_to_landline_phones;
    }
    public void setCall_prices_to_landline_phones(double call_prices_to_landline_phones) {
        this.call_prices_to_landline_phones = call_prices_to_landline_phones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallsType callsType = (CallsType) o;
        return Double.compare(callsType.call_prices_on_net, call_prices_on_net) == 0 &&
                Double.compare(callsType.call_prises_on_another_network, call_prises_on_another_network) == 0 &&
                Double.compare(callsType.call_prices_to_landline_phones, call_prices_to_landline_phones) == 0;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31*result + (int)call_prices_on_net;
        result = 31*result + (int)call_prises_on_another_network;
        result = 31*result + (int)call_prices_to_landline_phones;
        return result;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        sb.append("{");
        sb.append("call_prices_on_net ='").append(call_prices_on_net).append('\'');
        sb.append("call_prises_on_another_network ='").append(call_prises_on_another_network).append('\'');
        sb.append("call_prices_to_landline_phones ='").append(call_prices_to_landline_phones).append('\'');
        sb.append("}");
        return sb.toString();
    }
}
