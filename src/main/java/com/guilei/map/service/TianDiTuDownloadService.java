package com.guilei.map.service;


import com.guilei.map.dto.TianDiTuDataServerDTO;
import com.guilei.map.dto.TianDiTuWmtsDTO;

public interface TianDiTuDownloadService {
    String getRealImagePath(String kind, TianDiTuWmtsDTO dto);

    String getRealImagePath(String kind, String key, TianDiTuWmtsDTO dto);

    String getRealImagePath(String key, TianDiTuDataServerDTO dto);

    String getLocalPath(String kind, TianDiTuWmtsDTO dto);
}
