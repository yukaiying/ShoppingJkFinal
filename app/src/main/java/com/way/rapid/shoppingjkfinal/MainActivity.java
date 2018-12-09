package com.way.rapid.shoppingjkfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.way.rapid.shoppingjkfinal.adapter.ProductShowAdapter;
import com.way.rapid.shoppingjkfinal.bean.Product;
import com.way.rapid.shoppingjkfinal.thread.ProductHttpThread;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //数据，适配器，xml布局
        ProductHttpThread productHttpThread = new ProductHttpThread();
        productHttpThread.start();
        try {
            productHttpThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Product> list =
                JSON.parseArray(productHttpThread.getResult(), Product.class);
        ProductShowAdapter productShowAdapter = new ProductShowAdapter(
                this, R.layout.product_show_layout, list
        );
        ListView listView = findViewById(R.id.product_list_view);
        listView.setAdapter(productShowAdapter);
    }
}
