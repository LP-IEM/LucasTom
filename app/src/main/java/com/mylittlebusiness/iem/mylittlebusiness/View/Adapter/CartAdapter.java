package com.mylittlebusiness.iem.mylittlebusiness.View.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mylittlebusiness.iem.mylittlebusiness.Model.Product;
import com.mylittlebusiness.iem.mylittlebusiness.R;

import java.util.List;

public class CartAdapter extends BaseAdapter {
    private List<Product> productList;
    private Context mContext;

    public CartAdapter(Context context, List<Product> productList) {
        this.mContext = context;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Product getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.cart_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.txtPrice = (TextView) convertView.findViewById(R.id.cart_txtVw_result);
            viewHolder.txtNumber = (TextView) convertView.findViewById(R.id.cart_txtVw_product);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Product product = getItem(position);
        String formatProduct = String.format(convertView.getResources().getString(R.string.cart_product_nb), product.getName(), product.getNumber() + "");
        viewHolder.txtNumber.setText(formatProduct);
        viewHolder.txtPrice.setText(product.getPrice() * product.getNumber() + "€");

        return convertView;
    }

    private class ViewHolder {
        TextView txtPrice, txtNumber;
    }
}
