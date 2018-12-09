package com.way.rapid.shoppingjkfinal.thread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.way.rapid.shoppingjkfinal.util.HttpHelp;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageHttpThread extends Thread {
    //传入参数url
    private String imageUrl;
    //传出参数result
    private Bitmap resultBitmap;
    public ImageHttpThread(String imageUrl){
        this.imageUrl = imageUrl;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(HttpHelp.URL + imageUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            setResultBitmap(BitmapFactory.decodeStream(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Bitmap getResultBitmap() {
        return resultBitmap;
    }

    public void setResultBitmap(Bitmap resultBitmap) {
        this.resultBitmap = resultBitmap;
    }
}
