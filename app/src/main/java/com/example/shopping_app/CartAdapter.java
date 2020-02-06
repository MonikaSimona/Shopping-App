package com.example.shopping_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private ArrayList<CartProduct> cartProducts;
    private onItemDelete mlistener;

    public interface onItemDelete {
        void onItemDelete(int index);
    }

    public void setOnItemDeleteListener(onItemDelete listener){
        mlistener=listener;
    }

    public CartAdapter(Context context, ArrayList<CartProduct> list){
        cartProducts = list;


    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView productNameCart;
        TextView priceCart;
        Button deleteCart;

        public ViewHolder(@NonNull View itemView, final onItemDelete listener) {
            super(itemView);
            productNameCart = itemView.findViewById(R.id.productNameCart);
            priceCart = itemView.findViewById(R.id.priceCart);
            deleteCart = itemView.findViewById(R.id.deleteCart);

            deleteCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(listener != null){
                        int position =  getAdapterPosition();
                        if(position !=RecyclerView.NO_POSITION){
                            listener.onItemDelete(position);
                        }
                    }


                }
            });
        }
    }
    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartproduct_card,parent,false);

        return new ViewHolder(view,mlistener);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {

        holder.itemView.setTag(cartProducts.get(position));

        holder.productNameCart.setText(cartProducts.get(position).getName());
        holder.priceCart.setText(cartProducts.get(position).getPrice());


    }

    @Override
    public int getItemCount() {
        return cartProducts.size();
    }
}
