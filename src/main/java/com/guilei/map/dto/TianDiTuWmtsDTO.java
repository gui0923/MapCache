package com.guilei.map.dto;

import java.io.Serializable;

public class TianDiTuWmtsDTO implements Serializable {
    private static final long serialVersionUID = 2362094823468636988L;

    private String tk;

    private String layer;

    private String tilematrixset;

    private Integer TileMatrix;

    private Integer TileCol;

    private Integer TileRow;

    private String SERVICE;

    private String Request;

    private String version;

    public String getTk() {
        return tk;
    }

    public void setTk(String tk) {
        this.tk = tk;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public String getTilematrixset() {
        return tilematrixset;
    }

    public void setTilematrixset(String tilematrixset) {
        this.tilematrixset = tilematrixset;
    }

    public Integer getTileMatrix() {
        return TileMatrix;
    }

    public void setTileMatrix(Integer tileMatrix) {
        TileMatrix = tileMatrix;
    }

    public Integer getTileCol() {
        return TileCol;
    }

    public void setTileCol(Integer tileCol) {
        TileCol = tileCol;
    }

    public Integer getTileRow() {
        return TileRow;
    }

    public void setTileRow(Integer tileRow) {
        TileRow = tileRow;
    }

    public String getSERVICE() {
        return SERVICE;
    }

    public void setSERVICE(String SERVICE) {
        this.SERVICE = SERVICE;
    }

    public String getRequest() {
        return Request;
    }

    public void setRequest(String request) {
        Request = request;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "TianDiTuWmtsDTO{" + "tk='" + tk + '\'' + ", layer='" + layer + '\'' + ", tilematrixset='" + tilematrixset + '\'' + ", TileMatrix=" + TileMatrix + ", TileCol=" + TileCol + ", TileRow=" + TileRow + ", SERVICE='" + SERVICE + '\'' + ", Request='" + Request + '\'' + ", version='" + version + '\'' + '}';
    }
}
