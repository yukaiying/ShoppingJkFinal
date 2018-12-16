package com.way.rapid.shoppingjkfinal.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
import com.way.rapid.shoppingjkfinal.util.DataBaseHelp;

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
        final Product product = (Product) getItem(position);
        ProductLayout productLayout = new ProductLayout();
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            productLayout.titleView = view.findViewById(R.id.product_title_text_view);
            productLayout.priceView = view.findViewById(R.id.product_price_text_view);
            productLayout.imgView = view.findViewById(R.id.product_image_view);
            productLayout.addButton = view.findViewById(R.id.product_add_shop_button);
            view.setTag(productLayout);
        } else {
            view = convertView;
            productLayout = (ProductLayout) view.getTag();
        }
        productLayout.titleView.setText(product.getTitle());
        productLayout.priceView.setText(product.getPrice());
        productLayout.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelp dataBaseHelp = new DataBaseHelp(getContext(), "shopping.db", null, 1);
                Cursor cu = dataBaseHelp.getWritableDatabase().query("tab_shopping", null, "product_id = ?", new String[]{product.getId() + ""}, null, null, null);
                ContentValues contentValues = new ContentValues();
                if(cu.moveToFirst()){
                    //存在相同商品
                    contentValues.put("num", cu.getInt(5) + 1);
                    dataBaseHelp.getWritableDatabase().update("tab_shopping", contentValues, "product_id = ? ",new String[]{product.getId()+ ""});
                }else{
                    contentValues.put("product_id", product.getId());
                    contentValues.put("title", product.getTitle());
                    contentValues.put("price", product.getPrice());
                    contentValues.put("image", product.getImage());
                    contentValues.put("num", 1);
                    dataBaseHelp.getWritableDatabase().insert("tab_shopping", null , contentValues);

                }
            }
        });
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
