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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mylittlebusiness.iem.mylittlebusiness.Model.Product;
import com.mylittlebusiness.iem.mylittlebusiness.R;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private List<Product> productList;
    private Context mContext;
    private OnProductListener itemAddListener;

    public ProductAdapter(Context context) {
        this.mContext = context;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    public void setContactListener(OnProductListener itemAddListener) {
        this.itemAddListener = itemAddListener;
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
            convertView = inflater.inflate(R.layout.product_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.txtDescription = (TextView) convertView.findViewById(R.id.item_textVw_description);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.item_textVw_name);
            viewHolder.txtPrice = (TextView) convertView.findViewById(R.id.item_textVw_price);
            viewHolder.txtNumber = (TextView) convertView.findViewById(R.id.item_textVw_number);
            viewHolder.imgProduct = (ImageView) convertView.findViewById(R.id.item_imgVw_image);
            viewHolder.bttnAdd = (Button) convertView.findViewById(R.id.item_bttn_add);
            viewHolder.bttnRemove = (Button) convertView.findViewById(R.id.item_bttn_remove);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Product product = getItem(position);
        viewHolder.imgProduct.setImageResource(product.getImage());
        viewHolder.txtDescription.setText(product.getDescription());
        viewHolder.txtName.setText(product.getName());
        viewHolder.txtNumber.setText(product.getNumber()+"");
        viewHolder.txtPrice.setText(product.getPrice() + "€");

        viewHolder.bttnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemAddListener != null){
                    itemAddListener.onAddProduct(getItem(position));
                }
            }
        });

        viewHolder.bttnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemAddListener != null){
                    itemAddListener.onRemoveProduct(getItem(position));
                }
            }
        });

        // Change line color
        if(position%2 == 0){
            convertView.findViewById(R.id.item_layout_background).setBackgroundColor(Color.parseColor("#e9e9e9"));
        }else{
            convertView.findViewById(R.id.item_layout_background).setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        return convertView;
    }

    private class ViewHolder {
        TextView txtName, txtPrice, txtDescription, txtNumber;
        ImageView imgProduct;
        Button bttnAdd, bttnRemove;
    }

    public interface OnProductListener {
        void onAddProduct(Product product);
        void onRemoveProduct(Product product);
    }
}
