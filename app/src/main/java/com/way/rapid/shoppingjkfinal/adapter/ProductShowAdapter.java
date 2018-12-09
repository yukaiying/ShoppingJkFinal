package com.way.rapid.shoppingjkfinal.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.way.rapid.shoppingjkfinal.R;
import com.way.rapid.shoppingjkfinal.bean.Product;
import com.way.rapid.shoppingjkfinal.thread.ImageHttpThread;

import java.util.List;

public class ProductShowAdapter extends ArrayAdapter {
    private int resourceId;

    public ProductShowAdapter(@NonNull Context context,
                              int resource,
                              @NonNull List objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Product product = (Product) getItem(position);
        ProductLayout productLayout = new ProductLayout();
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            productLayout.titleView = view.findViewById(R.id.product_title_text_view);
            productLayout.priceView = view.findViewById(R.id.product_price_text_view);
            productLayout.imgView = view.findViewById(R.id.product_image_view);
            view.setTag(productLayout);
        } else {
            view = convertView;
            productLayout = (ProductLayout) view.getTag();
        }
        productLayout.titleView.setText(product.getTitle());
        productLayout.priceView.setText(product.getPrice());
        ImageHttpThread imageHttpThread = new ImageHttpThread(product.getImage());
        imageHttpThread.start();
        try {
            imageHttpThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        productLayout.imgView.setImageBitmap(imageHttpThread.getResultBitmap());
        return view;
    }

    class ProductLayout {
        TextView titleView;
        TextView priceView;
        ImageView imgView;
        Button addButton;
    }
}
