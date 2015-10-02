package com.example.john.domain;

import java.io.Serializable;

/**
 * Created by john on 15-10-2.
 */
public class RealData implements Serializable {

    private String unitnumber;
    private  String MonitoringInfo;
    private String Fault;

    public String getStandbyElectricity() {
        return StandbyElectricity;
    }

    public void setStandbyElectricity(String standbyElectricity) {
        StandbyElectricity = standbyElectricity;
    }

    public String getUnitnumber() {
        return unitnumber;
    }

    public void setUnitnumber(String unitnumber) {
        this.unitnumber = unitnumber;
    }

    public String getMonitoringInfo() {
        return MonitoringInfo;
    }

    public void setMonitoringInfo(String monitoringInfo) {
        MonitoringInfo = monitoringInfo;
    }

    public String getFault() {
        return Fault;
    }

    public void setFault(String fault) {
        Fault = fault;
    }

    public String getElectricity() {
        return Electricity;
    }

    public void setElectricity(String electricity) {
        Electricity = electricity;
    }

    private String StandbyElectricity;
    private String Electricity;

    public char[] dealMessage(String str) {
        char[] cs = str.toCharArray();
        return cs;
    }
}
