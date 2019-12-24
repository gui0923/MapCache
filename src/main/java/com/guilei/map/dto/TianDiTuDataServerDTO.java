package com.guilei.map.dto;

import java.io.Serializable;

public class TianDiTuDataServerDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String T;
    private Integer x;
    private Integer y;
    private Integer l;
    private String tk;

    public String getT() {
        return T;
    }

    public void setT(String t) {
        T = t;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getL() {
        return l;
    }

    public void setL(Integer l) {
        this.l = l;
    }

    public String getTk() {
        return tk;
    }

    public void setTk(String tk) {
        this.tk = tk;
    }

    @Override
    public String toString() {
        return "TianDiTuDataServerDTO{" +
                "T='" + T + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", l=" + l +
                ", tk='" + tk + '\'' +
                '}';
    }
}
