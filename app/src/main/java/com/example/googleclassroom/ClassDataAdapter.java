package com.example.googleclassroom;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ClassDataAdapter extends RecyclerView.Adapter<ClassDataAdapter.ProductViewHolder> {

    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<ClassData> classList;

    //getting the context and product list with constructor
    public ClassDataAdapter(Context mCtx, List<ClassData> productList) {
        this.mCtx = mCtx;
        this.classList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.one_class, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        ClassData classData = classList.get(position);

        //binding the data with the viewholder views
        holder.className.setText(classData.getClassName());
        holder.classDescribe.setText(classData.getDescription());
        // else if master student
        holder.numberOrMaster.setText(String.valueOf(classData.getMasterName()));
        holder.classBackground.setImageDrawable(mCtx.getResources().getDrawable(classData.getImage()));
    }


    @Override
    public int getItemCount() {
        return classList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView className, classDescribe, numberOrMaster;
        ImageView classBackground;

        public ProductViewHolder(View itemView) {
            super(itemView);

            className = itemView.findViewById(R.id.className);
            classDescribe = itemView.findViewById(R.id.classDescribe);
            numberOrMaster = itemView.findViewById(R.id.numberOrMaster);
            classBackground = itemView.findViewById(R.id.classBackground);
        }
    }
}