package com.example.dustnshine.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dustnshine.models.FavoriteModel;
import com.example.dustnshine.R;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>{

    List<FavoriteModel> bookingModelList;
    private FavoriteAdapter.OnClickMessageListener onClickMessageListener;

    public FavoriteAdapter(List<FavoriteModel> bookingModelList, FavoriteAdapter.OnClickMessageListener onClickMessageListener) {
        this.bookingModelList = bookingModelList;
        this.onClickMessageListener = onClickMessageListener;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_favorite_company, parent, false);
        FavoriteAdapter.ViewHolder viewHolder = new FavoriteAdapter.ViewHolder(view, onClickMessageListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {

        holder.companyImgFav.setImageResource(bookingModelList.get(position).getCompanyImgFav());
        holder.companyNameFav.setText(bookingModelList.get(position).getCompanyNameFav());
        holder.companyAddressFav.setText(bookingModelList.get(position).getCompanyAddressFav());
        holder.companyRatingFav.setText(bookingModelList.get(position).getCompanyRatingFav());


    }

    @Override
    public int getItemCount() {
        return bookingModelList.size();
    }

    public interface OnClickMessageListener {
        void onClickMessage(int adapterPosition);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView companyImgFav;
        private TextView companyNameFav, companyAddressFav,companyRatingFav;

        FavoriteAdapter.OnClickMessageListener onClickMessageListener;

        public ViewHolder(@NonNull View itemView, FavoriteAdapter.OnClickMessageListener onClickMessageListener) {
            super(itemView);

            companyImgFav = itemView.findViewById(R.id.companyImgFav);
            companyNameFav = itemView.findViewById(R.id.companyNameTVFav);
            companyAddressFav = itemView.findViewById(R.id.txtAdrress);
            companyRatingFav = itemView.findViewById(R.id.ratingFavorite);

            this.onClickMessageListener = (FavoriteAdapter.OnClickMessageListener) onClickMessageListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onClickMessageListener.onClickMessage(getAdapterPosition());
        }
    }
}
