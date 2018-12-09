package com.way.rapid.shoppingjkfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.way.rapid.shoppingjkfinal.adapter.ProductShowAdapter;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //数据，适配器，xml布局
        List<String> list = Arrays.asList("a", "b", "c");
        ProductShowAdapter productShowAdapter = new ProductShowAdapter(
                this,R.layout.product_show_layout, list
        );
        ListView listView = findViewById(R.id.product_list_view);
        listView.setAdapter(productShowAdapter);
    }
}
