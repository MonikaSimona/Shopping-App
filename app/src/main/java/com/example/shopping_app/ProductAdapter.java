package com.example.shopping_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private ArrayList<Product> products;

    ProductClicked activity;

    public interface ProductClicked{
        void onProductClicked(int index);
    }

    public ProductAdapter (Context context, ArrayList<Product> list){

        products = list;
        activity = (ProductClicked) context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView productName;
        TextView price;
        ImageView productImage;
        Button detailsBtn;
        Button toCartBtn;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.productName);
            price = itemView.findViewById(R.id.price);
            productImage = itemView.findViewById(R.id.productImage);
            detailsBtn = itemView.findViewById(R.id.detailsBtn);
            toCartBtn  = itemView.findViewById(R.id.toCartBtn);

            detailsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    activity.onProductClicked(products.indexOf( itemView.getTag()));

                }
            });
        }
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(products.get(position));

        holder.productName.setText(products.get(position).getProductName());
//        holder.productImage.setImageResource(products.get(position).getImage());
        holder.price.setText(products.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
