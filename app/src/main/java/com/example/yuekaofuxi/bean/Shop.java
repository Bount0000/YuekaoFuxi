package com.example.yuekaofuxi.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 祝文 on 2017/10/24.
 */

public class Shop {
    private boolean select;
    private String shangjia;
    private List<Shangpin> splist;

    @Override
    public String toString() {
        return "Shop{" +
                "select=" + select +
                ", shangjia='" + shangjia + '\'' +
                ", splist=" + splist +
                '}';
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public void setShangjia(String shangjia) {
        this.shangjia = shangjia;
    }

    public void setSplist(List<Shangpin> splist) {
        this.splist = splist;
    }

    public Shop() {
    }

    public boolean isSelect() {
        return select;
    }

    public String getShangjia() {
        return shangjia;
    }

    public List<Shangpin> getSplist() {
        return splist;
    }

    public Shop(boolean select, String shangjia, List<Shangpin> splist) {
        this.select = select;
        this.shangjia = shangjia;
        this.splist = splist;
    }
}
