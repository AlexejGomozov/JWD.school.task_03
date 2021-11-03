package com.javacourse.task3.entity;

import java.time.YearMonth;
import java.util.Objects;

public abstract class Tariff {
    private  String id;
    private String title;
    private OperatorName operatorName;
    private double payroll;
    private YearMonth year;
    private CallsType calls;
    private SmsType sms;
    private TarifficationType tariffication;
    private double connectionFee;

   public Tariff(){
       title = "unknown";
   }
       public Tariff(String id, String title,OperatorName operatorName, double payroll, YearMonth year, CallsType calls, SmsType sms, TarifficationType tariffication, double connectionFee ){
               this.id = id;
               this.title = title;
               this.operatorName = operatorName;
               this.payroll = payroll;
               this.year = year;
               this.calls = calls;
               this.sms = sms;
               this.tariffication = tariffication;
               this.connectionFee = connectionFee;
       }
       public String getId(){
       return id;
       }
       public void setId(String id){
       this.id = id;
       }
       public String getTitle(){ return title;}
    public void setTitle(String title){this.title = title;}
       public OperatorName getOperatorName(){
       return operatorName;
       }
       public void setOperatorName(OperatorName operatorName){
       this.operatorName = operatorName;
       }
       public double getPayroll(){
       return payroll;
       }
       public void setPayroll(double payroll){
       this.payroll = payroll;
       }
       public YearMonth getYear(){
       return year;
       }
       public void setYear(YearMonth year) {
           this.year = year;
       }

       public SmsType getSms() {
           return sms;
       }

       public void setSmsType(SmsType sms) {
           this.sms = sms;
       }

       public CallsType getCalls() {
           return calls;
       }

       public void setCallsType(CallsType calls) {
           this.calls = calls;
       }

        public TarifficationType getTariffication(){
        return tariffication;
        }
        public void setTarifficationType(TarifficationType tariffication){
        this.tariffication = tariffication;
        }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tariff tariff = (Tariff) o;
        return Double.compare(tariff.payroll, payroll) == 0 &&
                Double.compare(tariff.connectionFee, connectionFee) == 0 &&
                id.equals(tariff.id) &&
                operatorName == tariff.operatorName &&
                year.equals(tariff.year) &&
                calls == tariff.calls &&
                sms == tariff.sms &&
                tariffication == tariff.tariffication;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, operatorName, payroll, year, calls, sms, tariffication, connectionFee);
    }


    @Override
    public String toString() {
       final StringBuilder sb = new StringBuilder();
       sb.append("id =").append(id).append(id).append('\'');
       sb.append(", title=").append(title).append('\'');
       sb.append(", operatorName=").append(operatorName).append('\'');
       sb.append(", payroll=").append(payroll);
       sb.append(", year=").append(year);
       sb.append(", calls=").append(calls);
       sb.append(", sms='").append(sms);
       sb.append(", connectionFee=").append(connectionFee);

        return sb.toString();
    }
}
