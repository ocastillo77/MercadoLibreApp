package com.ideacreativa.mercadolibreapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ideacreativa.mercadolibreapp.R;
import com.ideacreativa.mercadolibreapp.controllers.DetailProductActivity;
import com.ideacreativa.mercadolibreapp.models.Producto;

import java.util.List;

public class ListProductsAdapter extends RecyclerView.Adapter<ListProductsAdapter.ListHolder> {
    Producto[] products;
    Context context;

    public static final String ITEM_ID = "com.mercadolibre.ITEM_ID";

    public ListProductsAdapter(Producto[] products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.producto_card, parent, false);
        return new ListHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, int position) {
        final int pos = position;
        holder.title.setText(products[position].title);
        holder.price.setText("$ " + products[position].price);

        if (!products[position].sold_quantity.equals("0")) {
            holder.sold_quantity.setText(products[position].sold_quantity + " vendidos");
        }

        Glide.with(context).load(products[position].thumbnail).into(holder.image);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailProductActivity.class);
                intent.putExtra(ITEM_ID, products[pos].id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.length;
    }

    public static class ListHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView price;
        public TextView sold_quantity;
        public ImageView image;
        public View container;

        public ListHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            sold_quantity = itemView.findViewById(R.id.sold_quantity);
            image = itemView.findViewById(R.id.image);
            container = itemView.findViewById(R.id.container);
        }
    }
}
