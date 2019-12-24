package com.guilei.map.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ScheduleService {

    private static final long EMPIRE_TIME = 2592000000L;//最大过期时间30天

    @Value("${nginxPath}")
    private String nginxPath;

    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteFile() {
        long time = System.currentTimeMillis();
        foreachDeleteFiles(new File(nginxPath + "/Imagery/"), time);
    }

    private void foreachDeleteFiles(File file, long time) {
        if (file.isFile()) {
            long mdTime = file.lastModified();
            if (time - mdTime > EMPIRE_TIME) {
                file.delete();
            }
        } else {
            File[] files = file.listFiles();
            for (File f : files) {
                foreachDeleteFiles(f, time);
            }
        }
    }

}
