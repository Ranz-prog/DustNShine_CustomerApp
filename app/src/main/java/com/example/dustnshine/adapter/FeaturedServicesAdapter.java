package com.example.dustnshine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dustnshine.R;
import com.example.dustnshine.models.ServicesModel;
import com.example.dustnshine.utils.AppConstants;

import java.util.List;

public class FeaturedServicesAdapter extends BaseAdapter {

    private List<ServicesModel> servicesModelList;
    private Context context;
    private LayoutInflater inflater;
    private AppConstants appConstants;

    public FeaturedServicesAdapter(List<ServicesModel> servicesModelList, Context context) {
        this.servicesModelList = servicesModelList;
        this.context = context;
    }

    public void setData(List<ServicesModel> servicesModelList) {
        this.servicesModelList = servicesModelList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return servicesModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        } if (view == null) {
            view = inflater.inflate(R.layout.content_features, null);
        }

        ImageView imgService;
        TextView tvServiceName, tvServiceDetails;

        imgService = view.findViewById(R.id.imgService);
        tvServiceName = view.findViewById(R.id.tvServiceName);
        tvServiceDetails = view.findViewById(R.id.tvServiceDetails);

        Glide.with(context).load(appConstants.BASE_URL + servicesModelList.get(position).getService_image()).into(imgService);
        tvServiceName.setText(String.valueOf(servicesModelList.get(position).getName()));
        tvServiceDetails.setText(String.valueOf(servicesModelList.get(position).getDescription()));

        return view;
    }
}
