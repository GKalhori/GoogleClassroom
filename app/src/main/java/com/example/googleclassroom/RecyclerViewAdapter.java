//package com.example.googleclassroom;
//
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//class RecyclerViewHolder extends RecyclerView.ViewHolder {
//    public ImageView imageView;
//    TextView numberName;
//
//
//    public RecyclerViewHolder(View itemView) {
//        super(itemView);
//        imageView = (ImageView)itemView.findViewById(R.id.imageBack);
//        numberName = (TextView)itemView.findViewById(R.id.numberName);
//
//
//    }
//}
//
//public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{
//
//private List<ProductModel> listData = new ArrayList<ProductModel>();
//
//    public RecyclerViewAdapter(List<ProductModel> listData)
//    {
//        this.listData = listData;
//    }
//
//    @NonNull
//    @Override
//    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View view
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return listData.size();
//    }
//}
