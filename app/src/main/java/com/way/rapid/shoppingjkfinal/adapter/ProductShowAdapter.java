package com.way.rapid.shoppingjkfinal.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.way.rapid.shoppingjkfinal.R;
import com.way.rapid.shoppingjkfinal.bean.Product;

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
        View view  = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView titleView = view.findViewById(R.id.product_title_text_view);
        titleView.setText(product.getTitle());
        TextView priceView = view.findViewById(R.id.product_price_text_view);
        priceView.setText(product.getPrice());
        return view;
    }
}
