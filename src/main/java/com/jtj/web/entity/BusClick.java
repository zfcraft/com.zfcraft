package com.jtj.web.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class BusClick {
    @ExcelColumn(value = "地区编码", col = 1)
    private String cityCode;

    @ExcelColumn(value = "标志", col = 2)
    private String markId;

    @ExcelColumn(value = "toaluv", col = 3)
    private String toaluv;

    @ExcelColumn(value = "date", col = 4)
    private String date;

    @ExcelColumn(value = "clientVer", col = 5)
    private String clientVer;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getMarkId() {
        return markId;
    }

    public void setMarkId(String markId) {
        this.markId = markId;
    }

    public String getToaluv() {
        return toaluv;
    }

    public void setToaluv(String toaluv) {
        this.toaluv = toaluv;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClientVer() {
        return clientVer;
    }

    public void setClientVer(String clientVer) {
        this.clientVer = clientVer;
    }

    @Override
    public String toString() {
        return "BusClick [cityCode=" + cityCode + ", markId=" + markId + ", toaluv=" + toaluv + ", date=" + date
                + ", clientVer=" + clientVer + "]";
    }
}
