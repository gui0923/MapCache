package com.guilei.map.utils;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

public class DownloadUtils {

    public static boolean downloadXmlFromServer(String urlPath, File file) {
        StringBuffer document = new StringBuffer();
        URL url = null;//远程url地址
        try {
            url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
            conn.setRequestProperty("Accept",
                    "application/json, text/plain, */*");
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
            conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setConnectTimeout(15000); // 5s
            conn.setReadTimeout(15000);
            String encoding = conn.getContentType();
            encoding = encoding.substring(encoding.indexOf("charset=")+8).trim();
            InputStream urlStream = new GZIPInputStream(conn.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlStream, encoding));
            String line = null;
            while ((line = reader.readLine()) != null) {
                document.append(line + " ");
            }
            reader.close();
            FileUtils.writeStringToFile(file, document.toString(), "utf-8");
            return true;
        } catch (MalformedURLException e) {
//            e.printStackTrace();
        } catch (IOException e) {
//            e.printStackTrace();
        }
        return false;
    }

    public static boolean downloadFromWebServer(String urlPath, File file)
    {
        InputStream is = null;
        FileOutputStream fos = null;
        try
        {
            URLConnection openConnection = new URL(urlPath).openConnection();
            openConnection.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
            openConnection.setRequestProperty("Accept",
                    "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
            openConnection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
            openConnection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
            openConnection.setConnectTimeout(15000); // 5s
            openConnection.setReadTimeout(15000);
            is = openConnection.getInputStream();

            byte[] buff = new byte[1024];
            int len;

            fos = new FileOutputStream(file);
            if (null != is)
            {
                while ((len = is.read(buff)) != -1)
                {
                    fos.write(buff, 0, len);
                }
            }
            return true;
        }
        catch (MalformedURLException e)
        {
//            e.printStackTrace();
            return false;
        }
        catch (FileNotFoundException e)
        {
//            e.printStackTrace();
            return false;
        }
        catch (IOException e)
        {
//            e.printStackTrace();
            return false;
        }
        finally
        {
            if (fos != null)
            {
                try
                {
                    fos.close();
                }
                catch (IOException e)
                {
                }
            }
            if (is != null) try {
                is.close();
            } catch (IOException e) {
            }
        }
    }

    public static void main(String[] args) {
        String url = "https://t2.tianditu.com/img_c/wmts?tk=17d1619c13e4508bc1945bd59de4edf8&0ad45a9d-aacb-7a55-497c-59ca96d5b95e&SERVICE=WMTS&Request=getCapabilities&version=1.0.0&3ab41697-c56f-4f56-8b5f-813e799fa90d";
        downloadXmlFromServer(url, new File("abc.xml"));
    }
}
