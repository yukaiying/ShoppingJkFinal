package com.way.rapid.shoppingjkfinal.thread;

import com.way.rapid.shoppingjkfinal.bean.HttpMethod;
import com.way.rapid.shoppingjkfinal.util.HttpHelp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ProductHttpThread extends Thread {
    private String result;
    @Override
    public void run() {
        //http请求
        try {
            URL url = new URL(HttpHelp.URL + "product");
            HttpURLConnection httpURLConnection  = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(String.valueOf(HttpMethod.GET));
            httpURLConnection.setConnectTimeout(HttpHelp.TIME_OUT);
            InputStream is = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String temp ;
            while((temp = bufferedReader.readLine()) != null){
                stringBuffer.append(temp);
            }
            setResult(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
