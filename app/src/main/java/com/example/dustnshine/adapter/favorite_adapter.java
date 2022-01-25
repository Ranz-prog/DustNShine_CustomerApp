package com.example.dustnshine.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dustnshine.Models.favorite_model;
import com.example.dustnshine.R;

import java.util.List;

public class favorite_adapter extends RecyclerView.Adapter<favorite_adapter.ViewHolder>{

    List<favorite_model> bookingModelList;

    public favorite_adapter(List<favorite_model> bookingModelList) {
        this.bookingModelList = bookingModelList;
    }

    @NonNull
    @Override
    public favorite_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_favorite_company, parent, false);
        favorite_adapter.ViewHolder viewHolder = new favorite_adapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull favorite_adapter.ViewHolder holder, int position) {

        holder.companyImgFav.setImageResource(bookingModelList.get(position).getCompanyImgFav());
        holder.companyNameFav.setText(bookingModelList.get(position).getCompanyNameFav());
        holder.companyAddressFav.setText(bookingModelList.get(position).getCompanyAddressFav());
        holder.companyRatingFav.setText(bookingModelList.get(position).getCompanyRatingFav());


    }

    @Override
    public int getItemCount() {
        return bookingModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView companyImgFav;
        private TextView companyNameFav, companyAddressFav,companyRatingFav;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            companyImgFav = itemView.findViewById(R.id.companyImgFav);
            companyNameFav = itemView.findViewById(R.id.companyNameTVFav);
            companyAddressFav = itemView.findViewById(R.id.txtAdrress);
            companyRatingFav = itemView.findViewById(R.id.ratingFavorite);

        }
    }
}