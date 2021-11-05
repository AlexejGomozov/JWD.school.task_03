package com.javacourse.task3.entity;

import java.time.YearMonth;
import java.util.Objects;

public abstract class Tariff {
    private  String id;
    private String title;
    private OperatorName operatorName;
    private double payroll;
    private YearMonth year;
    private double connectionFee;
    private CallsType calls;
    private SmsType sms;
    private TarifficationType tariffication;


   public Tariff(){
       title = "unknown";
       calls = new CallsType();
   }
//       public Tariff(String id, String title,OperatorName operatorName, double payroll, YearMonth year, CallsType calls, SmsType sms, TarifficationType tariffication, double connectionFee ){
//               this.id = id;
//               this.title = title;
//               this.operatorName = operatorName;
//               this.payroll = payroll;
//               this.year = year;
//               this.calls = calls;
//               this.sms = sms;
//               this.tariffication = tariffication;
//               this.connectionFee = connectionFee;
//       }
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

    public double getConnectionFee() {
        return connectionFee;
    }
    public void setConnectionFee(double connectionFee) {
        this.connectionFee = connectionFee;
    }

    public CallsType getCallsType() {
        return calls;
    }

    public void setCallsType(CallsType calls) {
        this.calls = calls;
    }

    public SmsType getSms() {
           return sms;
       }

       public void setSmsType(SmsType sms) {
           this.sms = sms;
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
                id.equals(tariff.id) && title.equals(tariff.title) &&
                operatorName == tariff.operatorName &&
                year.equals(tariff.year) &&
                calls.equals(tariff.calls) &&
                sms == tariff.sms &&
                tariffication == tariff.tariffication;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, operatorName, payroll, year, connectionFee, calls, sms, tariffication);
    }

    @Override
    public String toString() {
       final StringBuilder sb = new StringBuilder();
       sb.append("id =").append(id).append('\'');
       sb.append(", title=").append(title).append('\'');
       sb.append(", operatorName=").append(operatorName).append('\'');
       sb.append(", payroll=").append(payroll);
       sb.append(", year=").append(year);
        sb.append(", connectionFee=").append(connectionFee);
       sb.append(", calls=").append(calls);
        sb.append(", tariffication='").append(tariffication);
        sb.append(", sms='").append(sms);

        return sb.toString();
    }
}
