package com.example.dustnshine.ui.booking;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.dustnshine.R;
import com.example.dustnshine.adapter.BookingAdapter;
import com.example.dustnshine.databinding.FragmentBookingBinding;
import com.example.dustnshine.models.BookingServiceData;
import com.example.dustnshine.models.CompanyAndServicesModel;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.ForgetPasswordActivity;
import com.example.dustnshine.ui.booking_history.BookingHistoryActivity;
import com.example.dustnshine.ui.checkout.CheckOutActivity;
import com.example.dustnshine.ui.signin.SignInActivity;

import java.util.List;


public class BookingFragment extends Fragment implements BookingAdapter.OnClickMessageListener, SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView rvBooking;
    private AlertDialog.Builder dialogBuilder;
    private TextView companyName, companyEmail, companyNumber, paymentStatus, dateAndTime, services, notes, comment, total;
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
                    Log.d("D", " Nagana");
                }else{
                    companyAndServicesModels = companyAndServicesModel;
                    companyFullname = companyAndServicesModel.getName();
                    companyEmailAddress = companyAndServicesModel.getEmail();
                    companyTelephoneNumber = companyAndServicesModel.getTel_number();

                    Log.d("pangalan", companyFullname);
                    Log.d("pangalan", companyEmailAddress);
                    Log.d("pangalan", companyTelephoneNumber);

                }

            }
        });

    }

    @Override
    public void onClickMessage(int adapterPosition) {

        BookingServiceData bookingServiceData = bookingServiceDataList.get(adapterPosition);
        getSpecificCompany(bookingServiceData.getCompany_id(), userToken);

        dialogBuilder = new AlertDialog.Builder(getContext());

        final View searchPopUp = getLayoutInflater().inflate(R.layout.pop_up_customerdetails, null);

        TextView commentTV = searchPopUp.findViewById(R.id.tvcom);
        Button okay = searchPopUp.findViewById(R.id.btnOkay);

        companyName = searchPopUp.findViewById(R.id.txtcompany);
        companyEmail = searchPopUp.findViewById(R.id.txtemail);
        companyNumber = searchPopUp.findViewById(R.id.txttelnum);

        paymentStatus = searchPopUp.findViewById(R.id.txtpaymentstatus);
        dateAndTime = searchPopUp.findViewById(R.id.txtSched);
        services = searchPopUp.findViewById(R.id.txtServices);
        notes = searchPopUp.findViewById(R.id.txtnote);
        comment = searchPopUp.findViewById(R.id.txtcom);
        total = searchPopUp.findViewById(R.id.txttotal);

        comment.setVisibility(View.GONE);
        commentTV.setVisibility(View.GONE);

        paymentStatus.setText("Not yet paid");
        if(String.valueOf(companyFullname) == "null"){
            getSpecificCompany(bookingServiceData.getCompany_id(), userToken);

        }else{companyName.setText(String.valueOf(companyFullname));
            companyEmail.setText(String.valueOf(companyEmailAddress));
            companyNumber.setText(String.valueOf(companyTelephoneNumber));}

        dateAndTime.setText(bookingServiceData.getSched_datetime());
        services.setText(bookingServiceData.getServices().toString().replaceAll("(^\\[|\\]$)", ""));
        notes.setText(bookingServiceData.getNote());
        total.setText("Php "+String.valueOf(bookingServiceData.getTotal()));

        okay.setOnClickListener(new View.OnClickListener() {
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