package com.guilei.map.controller;

import com.guilei.map.dto.TianDiTuDataServerDTO;
import com.guilei.map.dto.TianDiTuWmtsDTO;
import com.guilei.map.service.TianDiTuDownloadService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Controller
@RequestMapping("/mapcache")
public class MapCacheCtroller {

    @Autowired
    private TianDiTuDownloadService tianDiTuDownloadService;

    @CrossOrigin
    @RequestMapping(value = "/{key}/{kind}/wmts", method = RequestMethod.GET)
    public String vecWmts(@PathVariable String kind, @PathVariable String key, TianDiTuWmtsDTO dto) {
        String path = tianDiTuDownloadService.getRealImagePath(kind, key, dto);
        if (path == null) {
            System.out.println("不存在  dto = " + dto);
            return "";
        }
        return "redirect:" + path;
    }

    @CrossOrigin
    @RequestMapping(value = "/{kind}/wmts", method = RequestMethod.GET)
    public String vecWmtsdefault(@PathVariable String kind, TianDiTuWmtsDTO dto) {
        String path = tianDiTuDownloadService.getRealImagePath(kind, dto);
        if (path == null) {
            System.out.println("不存在  dto = " + dto);
            return "";
        }
        return "forward:" + path;
    }

    @CrossOrigin
    @RequestMapping(value = "/DataServer/{key}", method = RequestMethod.GET)
    public String vecWmts(@PathVariable String key, TianDiTuDataServerDTO dto) {
        String path = tianDiTuDownloadService.getRealImagePath(key, dto);
        if (path == null) {
            return null;
        }
        return "forward:" + path;
    }

    private void downloadFile(File file, HttpServletResponse response) {
        OutputStream os = null;

        try {
            os = response.getOutputStream();
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
            response.addHeader("Content-Length", "" + file.length());
            if (StringUtils.endsWith(file.getName(), "xml")) {
                response.setContentType("text/xml");
            }
            else
            {
                response.setContentType("image/png");
            }
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            byte[] bytes = new byte[1024];

            byte[] b = new byte[1024];
            int length;
            while ((length = bis.read(b, 0, b.length)) > 0) {
                os.write(b, 0, length);
            }
            os.flush();
            os.close();
            bis.close();

        } catch (Exception e) {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e2) {
            }
            e.printStackTrace();
        }
    }
}
