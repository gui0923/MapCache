package com.guilei.map.service.impl;

import com.guilei.map.dto.TianDiTuDataServerDTO;
import com.guilei.map.dto.TianDiTuWmtsDTO;
import com.guilei.map.service.TianDiTuDownloadService;
import com.guilei.map.utils.DownloadUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Service
public class TianDiTuDownloadServiceImpl implements TianDiTuDownloadService {

    @Value("#{'${tks}'.split(',')}")
    private List<String> tkList;

    @Value("${nginxPath}")
    private String nginxPath;

    @Value("${tiandituVecc}")
    private String tiandituVeccUrl;

    @Value("${tiandituDataServer}")
    private String tiandituDataServer;

    @Value("${tiandituCapabilities}")
    private String tiandituCapabilities;

    @Value("#{${networkMapping}}")
    private Map<String, String> networkMapping;

    private static final int MAX_SERVER_NUM = 4;


    private Random random = new Random();

    @Override
    public String getRealImagePath(String kind, TianDiTuWmtsDTO dto) {
        boolean isGetXml = StringUtils.equals(dto.getRequest(), "getCapabilities");
        String tarName;
        if (isGetXml) {
            tarName = String.format("/Imagery/TianDiTu/%s/%s.xml", kind, "capabilities");
        } else {
            tarName = String.format("/Imagery/TianDiTu/%s/%s/%d/%d/%d.png", kind, dto.getLayer(), dto.getTileMatrix(), dto.getTileCol(), dto.getTileRow());
        }
        String fileName = nginxPath + tarName;
        File file = new File(fileName);
        if (file.exists()) {
            return tarName;
        }
        downloadTargetSource(kind, dto, isGetXml, file);

        if (file.exists()) {
            return tarName;
        } else {
            return null;
        }
    }

    @Override
    public String getRealImagePath(String kind, String key, TianDiTuWmtsDTO dto) {
        boolean isGetXml = StringUtils.equals(dto.getRequest(), "getCapabilities");
        String tarName;
        if (isGetXml) {
            tarName = String.format("/Imagery/TianDiTu/%s/%s.xml", kind, "capabilities");
        } else {
            tarName = String.format("/Imagery/TianDiTu/%s/%s/%d/%d/%d.png", kind, dto.getLayer(), dto.getTileMatrix(), dto.getTileCol(), dto.getTileRow());
        }
        String fileName = nginxPath + tarName;
        File file = new File(fileName);
        if (file.exists()) {
            return networkMapping.get(key) + tarName;
        }
        downloadTargetSource(kind, dto, isGetXml, file);

        if (file.exists()) {
            return networkMapping.get(key) + tarName;
        } else {
            return null;
        }
    }

    private void downloadTargetSource(String kind, TianDiTuWmtsDTO dto, boolean isGetXml, File file) {
        String url;
        if (isGetXml) {
            url = String.format(tiandituCapabilities, random.nextInt(MAX_SERVER_NUM), kind, tkList.get(random.nextInt(tkList.size())), UUID.randomUUID().toString());
        } else {
            url = String.format(tiandituVeccUrl, random.nextInt(MAX_SERVER_NUM), kind, tkList.get(random.nextInt(tkList.size())), dto.getLayer(), dto.getTileMatrix(), dto.getTileCol(), dto.getTileRow());
        }
        try {
            FileUtils.forceMkdirParent(file);
            if (isGetXml) {
                DownloadUtils.downloadXmlFromServer(url, file);
            } else {
                DownloadUtils.downloadFromWebServer(url, file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getRealImagePath(String key, TianDiTuDataServerDTO dto) {
        String tarName = String.format("/Imagery/TianDiTu/%s/%d/%d/%d.png", dto.getT(), dto.getL(), dto.getX(), dto.getY());
        String fileName = nginxPath + tarName;
        File file = new File(fileName);
        if (file.exists()) {
            return tarName;
        }
        String url = String.format(tiandituDataServer, random.nextInt(MAX_SERVER_NUM), dto.getT(),
                dto.getX(), dto.getY(), dto.getL(),tkList.get(random.nextInt(tkList.size())));
        try {
            FileUtils.forceMkdirParent(file);
            DownloadUtils.downloadFromWebServer(url, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (file.exists()) {
            return tarName;
        } else {
            return null;
        }
    }
}
