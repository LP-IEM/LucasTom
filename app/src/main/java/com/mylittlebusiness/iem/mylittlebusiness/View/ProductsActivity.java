package com.mylittlebusiness.iem.mylittlebusiness.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.mylittlebusiness.iem.mylittlebusiness.Model.Product;
import com.mylittlebusiness.iem.mylittlebusiness.R;
import com.mylittlebusiness.iem.mylittlebusiness.View.Adapter.ProductAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {
    private List<Product> cartList;
    private Snackbar snackbarCart;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        getSupportActionBar().setTitle(R.string.product_title);

        snackbarCart = Snackbar.make(findViewById(android.R.id.content), "", Snackbar.LENGTH_INDEFINITE);
        snackbarCart.setAction("VOIR", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductsActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        // INIT products list
        cartList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Salade", "Variété à carde. Les blettes cuites ont un goût qui oscille entre l'oseille et l'épinard.", 3.9, R.drawable.salade));
        productList.add(new Product("Patate", "Variété à carde. Les blettes cuites ont un goût qui oscille entre l'oseille et l'épinard.", 2.9, R.drawable.patate));
        productList.add(new Product("Courge", "Produit issu de l'agriculture biologique. Son goût est prononcé et légèrement sucré.", 4.9, R.drawable.courge));

        ListView lstVwProduct = (ListView) findViewById(R.id.Products_lstVw_products);
        productAdapter = new ProductAdapter(this);
        productAdapter.setProductList(productList);
        productAdapter.setContactListener(new ProductAdapter.OnProductListener() {
            @Override
            public void onAddProduct(Product product) {
                addCartProduct(product);
                notifyChangeCart();
            }

            @Override
            public void onRemoveProduct(Product product) {
                removeCartProduct(product);
                notifyChangeCart();
            }
        });

        lstVwProduct.setAdapter(productAdapter);
    }

    private void addCartProduct(Product product) {
        if (cartList.contains(product)) {
            Product productCart = cartList.get(cartList.indexOf(product));
            productCart.incrementNumber();
            productAdapter.notifyDataSetChanged();
        } else {
            cartList.add(product);
        }
    }

    private void removeCartProduct(Product product) {
        if (cartList.contains(product)) {
            Product productCart = cartList.get(cartList.indexOf(product));
            if (productCart.getNumber() == 1) {
                productCart.decrementNumber();
                cartList.remove(product);
            } else {
                productCart.decrementNumber();
            }
            productAdapter.notifyDataSetChanged();
        }
    }

    public void notifyChangeCart() {
        int nbProducts = cartList.size();
        if (nbProducts != 0) {
            String message = getCartMessage(nbProducts);
            snackbarCart.setText(message);
            snackbarCart.show();
        } else {
            snackbarCart.dismiss();
        }
    }

    @NonNull
    private String getCartMessage(int nbProducts) {
        String message = nbProducts + "";
        if (nbProducts == 1)
            message += " article ";
        else
            message += " articles ";
        message += "dans le panier";
        return message;
    }
}
