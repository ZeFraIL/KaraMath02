package com.fraiman.zeev.karamath01;

public class MyExercise {
    private String exDate;
    private String exType;
    private String coefA;
    private String coefB;
    private String coefC;

    public MyExercise(String exDate, String exType, String coefA, String coefB, String coefC) {
        this.exDate = exDate;
        this.exType = exType;
        this.coefA = coefA;
        this.coefB = coefB;
        this.coefC = coefC;
    }

    public String getExDate() {
        return exDate;
    }

    public void setExDate(String exDate) {
        this.exDate = exDate;
    }

    public String getExType() {
        return exType;
    }

    public void setExType(String exType) {
        this.exType = exType;
    }

    public String getCoefA() {
        return coefA;
    }

    public void setCoefA(String coefA) {
        this.coefA = coefA;
    }

    public String getCoefB() {
        return coefB;
    }

    public void setCoefB(String coefB) {
        this.coefB = coefB;
    }

    public String getCoefC() {
        return coefC;
    }

    public void setCoefC(String coefC) {
        this.coefC = coefC;
    }

    @Override
    public String toString() {
        return "MyExercise{" +
                "exDate='" + exDate + '\'' +
                ", exType='" + exType + '\'' +
                ", coefA='" + coefA + '\'' +
                ", coefB='" + coefB + '\'' +
                ", coefC='" + coefC + '\'' +
                '}';
    }
}
