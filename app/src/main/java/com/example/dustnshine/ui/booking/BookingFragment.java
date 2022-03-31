package com.example.dustnshine.ui.booking;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.dustnshine.R;
import com.example.dustnshine.adapter.BookingAdapter;
import com.example.dustnshine.databinding.FragmentBookingBinding;
import com.example.dustnshine.models.BookingServiceData;
import com.example.dustnshine.models.CompanyAndServicesModel;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.booking_history.BookingHistoryActivity;

import java.util.List;


public class BookingFragment extends Fragment implements BookingAdapter.OnClickMessageListener, SwipeRefreshLayout.OnRefreshListener {

    private Button btnOkay;
    private AlertDialog.Builder dialogBuilder;
    private TextView companyName, companyEmail, companyNumber, paymentStatus, dateAndTime, services, notes, comment, comments, total;
    private CompanyAndServicesModel companyAndServicesModels;
    private AlertDialog dialog;
    private View view;
    private List<BookingServiceData> bookingServiceDataList;
    private BookingFragmentViewModel bookingFragmentViewModel;
    private BookingAdapter bookingAdapter;
    private String userToken,companyFullname, companyEmailAddress, companyTelephoneNumber;
    private FragmentBookingBinding fragmentBookingBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        fragmentBookingBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_booking, container, false);
        bookingFragmentViewModel = new ViewModelProvider(BookingFragment.this).get(BookingFragmentViewModel.class);
        view = fragmentBookingBinding.getRoot();
        userToken = SharedPrefManager.getInstance(getContext()).getUserToken();
        fragmentBookingBinding.rvBooking.setHasFixedSize(true);
        fragmentBookingBinding.rvBooking.setLayoutManager(new LinearLayoutManager(getContext()));
        bookingAdapter = new BookingAdapter(bookingServiceDataList, getContext(), this);
        fragmentBookingBinding.refreshLayout.setOnRefreshListener(this);
        getBookedService(userToken);

        fragmentBookingBinding.btnBookingHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BookingHistoryActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void getBookedService(String userToken) {
        bookingFragmentViewModel.getBookedServices(userToken).observe(getActivity(), new Observer<List<BookingServiceData>>() {
            @Override
            public void onChanged(List<BookingServiceData> bookingServiceData) {
                if (!bookingServiceData.isEmpty()) {
                    bookingServiceDataList = bookingServiceData;
                    bookingAdapter.setData(bookingServiceData);
                    fragmentBookingBinding.rvBooking.setAdapter(bookingAdapter);
                    fragmentBookingBinding.imgNoTransactions.setVisibility(View.GONE);
                    fragmentBookingBinding.tvNoTransactions.setVisibility(View.GONE);
                } else {
                    fragmentBookingBinding.rvBooking.setVisibility(View.GONE);
                    fragmentBookingBinding.imgNoTransactions.setVisibility(View.VISIBLE);
                    fragmentBookingBinding.tvNoTransactions.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void getSpecificCompany (int companyId, String userToken){
        bookingFragmentViewModel.getSpecificCompany(companyId,userToken).observe(getActivity(), new Observer<CompanyAndServicesModel>() {
            @Override
            public void onChanged(CompanyAndServicesModel companyAndServicesModel) {
                if (companyAndServicesModel == null) {

                }else{
                    companyAndServicesModels = companyAndServicesModel;
                    companyFullname = companyAndServicesModel.getName();
                    companyEmailAddress = companyAndServicesModel.getEmail();
                    companyTelephoneNumber = companyAndServicesModel.getTel_number();
                    Log.d("D", companyFullname);
                }

            }
        });

    }

    @Override
    public void onClickMessage(int adapterPosition) {

        BookingServiceData bookingServiceData = bookingServiceDataList.get(adapterPosition);
        getSpecificCompany(bookingServiceData.getCompany_id(), userToken);
        dialogBuilder = new AlertDialog.Builder(getContext());
        final View searchPopUp = getLayoutInflater().inflate(R.layout.pop_up_booking_details, null);

        btnOkay = searchPopUp.findViewById(R.id.btnOkay);
        companyName = searchPopUp.findViewById(R.id.tvCompanyName);
        companyEmail = searchPopUp.findViewById(R.id.tvEmailAddress);
        companyNumber = searchPopUp.findViewById(R.id.tvTelNum);
        paymentStatus = searchPopUp.findViewById(R.id.tvPaymentStatus);
        dateAndTime = searchPopUp.findViewById(R.id.tvSchedDateTime);
        services = searchPopUp.findViewById(R.id.tvServices);
        notes = searchPopUp.findViewById(R.id.tvNotes);
        comment = searchPopUp.findViewById(R.id.tvComment);
        comments = searchPopUp.findViewById(R.id.tvComments);
        total = searchPopUp.findViewById(R.id.tvTotalCost);

        comment.setVisibility(View.GONE);
        comments.setVisibility(View.GONE);

        Log.d("COMPANY", String.valueOf(companyFullname));

        if (String.valueOf(companyFullname) == "null"){
            getSpecificCompany(bookingServiceData.getCompany_id(), userToken);
        } else {
            companyName.setText(String.valueOf(companyFullname));
            companyEmail.setText(String.valueOf(companyEmailAddress));
            companyNumber.setText(String.valueOf(companyTelephoneNumber));
        }

        paymentStatus.setText("Not yet paid");
        dateAndTime.setText(bookingServiceData.getSched_datetime());
        services.setText(bookingServiceData.getServices().toString().replaceAll("(^\\[|\\]$)", ""));
        notes.setText(bookingServiceData.getNote());
        total.setText("₱ " + String.valueOf(bookingServiceData.getTotal()));

        btnOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialogBuilder.setView(searchPopUp);
        dialog = dialogBuilder.create();
        dialog.show();

    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getBookedService(userToken);
            }
        }, 2000);
        fragmentBookingBinding.refreshLayout.setRefreshing(false);
    }
}