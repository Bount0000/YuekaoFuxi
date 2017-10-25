package com.example.yuekaofuxi.bean;

/**
 * Created by 祝文 on 2017/10/24.
 */

public class Shangpin {
    private boolean spselect;
    private String sp;
    private double jiage;
    private int num;

    @Override
    public String toString() {
        return "Shangpin{" +
                "spselect=" + spselect +
                ", sp='" + sp + '\'' +
                ", jiage=" + jiage +
                ", num=" + num +
                '}';
    }

    public void setSpselect(boolean spselect) {
        this.spselect = spselect;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }

    public void setJiage(double jiage) {
        this.jiage = jiage;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isSpselect() {
        return spselect;
    }

    public String getSp() {
        return sp;
    }

    public double getJiage() {
        return jiage;
    }

    public int getNum() {
        return num;
    }

    public Shangpin() {
    }

    public Shangpin(boolean spselect, String sp, double jiage, int num) {
        this.spselect = spselect;
        this.sp = sp;
        this.jiage = jiage;
        this.num = num;
    }
}
