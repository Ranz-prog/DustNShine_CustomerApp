package com.example.dustnshine.ui.booking_history;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dustnshine.R;
import com.example.dustnshine.adapter.BookingHistoryAdapter;
import com.example.dustnshine.databinding.ActivityBookingHistoryBinding;
import com.example.dustnshine.models.BookingHistoryModel;
import com.example.dustnshine.models.BookingServiceData;
import com.example.dustnshine.models.CompanyAndServicesModel;
import com.example.dustnshine.storage.SharedPrefManager;

import java.util.List;

public class BookingHistoryActivity extends AppCompatActivity implements BookingHistoryAdapter.OnClickMessageListener {

    private TextView companyName, companyEmail, companyNumber, paymentStatus, dateAndTime, services, notes, comment, total;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private CompanyAndServicesModel companyAndServicesModels;
    private RecyclerView rvBookingHistory;
    private List<BookingHistoryModel> bookingHistoryModelList;
    private BookingHistoryAdapter bookingHistoryAdapter;
    private BookingHistoryViewModel bookingHistoryViewModel;
    private String userToken,companyFullname, companyEmailAddress, companyTelephoneNumber,commento;
    private ActivityBookingHistoryBinding activityBookingHistoryBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        activityBookingHistoryBinding = DataBindingUtil.setContentView(this, R.layout.activity_booking_history);
        bookingHistoryViewModel = new ViewModelProvider(BookingHistoryActivity.this).get(BookingHistoryViewModel.class);
        rvBookingHistory = findViewById(R.id.rvBookingHistory);
        userToken = SharedPrefManager.getInstance(BookingHistoryActivity.this).getUserToken();
        rvBookingHistory.setHasFixedSize(true);
        rvBookingHistory.setLayoutManager(new LinearLayoutManager(BookingHistoryActivity.this));
        bookingHistoryAdapter = new BookingHistoryAdapter(bookingHistoryModelList, BookingHistoryActivity.this, this);

        getBookingHistory(userToken);

        activityBookingHistoryBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getBookingHistory(String userToken) {
        bookingHistoryViewModel.getBookingHistory(userToken).observe(BookingHistoryActivity.this, new Observer<List<BookingHistoryModel>>() {
            @Override
            public void onChanged(List<BookingHistoryModel> bookingHistoryModels) {
                if (!bookingHistoryModels.isEmpty()) {
                    bookingHistoryModelList = bookingHistoryModels;
                    bookingHistoryAdapter.setData(bookingHistoryModelList);
                    activityBookingHistoryBinding.rvBookingHistory.setAdapter(bookingHistoryAdapter);
                    activityBookingHistoryBinding.imgNoTransactions.setVisibility(View.GONE);
                    activityBookingHistoryBinding.tvNoTransactions.setVisibility(View.GONE);
                } else {
                    activityBookingHistoryBinding.rvBookingHistory.setVisibility(View.GONE);
                    activityBookingHistoryBinding.imgNoTransactions.setVisibility(View.VISIBLE);
                    activityBookingHistoryBinding.tvNoTransactions.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void getSpecificCompany (int companyId, String userToken){
        bookingHistoryViewModel.getSpecificCompany(companyId,userToken).observe(BookingHistoryActivity.this, new Observer<CompanyAndServicesModel>() {
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

        BookingHistoryModel bookingHistoryModel = bookingHistoryModelList.get(adapterPosition);
        getSpecificCompany(bookingHistoryModel.getCompany_id(), userToken);

        dialogBuilder = new AlertDialog.Builder(this);

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

//        comment.setVisibility(View.GONE);
//        commentTV.setVisibility(View.GONE);



        paymentStatus.setText("Payment Acknowledged");
        if(String.valueOf(companyFullname) == "null"){
            getSpecificCompany(bookingHistoryModel.getCompany_id(), userToken);

        }else{companyName.setText(String.valueOf(companyFullname));
            companyEmail.setText(String.valueOf(companyEmailAddress));
            companyNumber.setText(String.valueOf(companyTelephoneNumber));
            }
        comment.setText(bookingHistoryModel.getReviews().get(0).getComment());
        dateAndTime.setText(bookingHistoryModel.getSched_datetime());
        services.setText(bookingHistoryModel.getServices().toString().replaceAll("(^\\[|\\]$)", ""));
        notes.setText(bookingHistoryModel.getNote());
        total.setText("Php "+String.valueOf(bookingHistoryModel.getTotal()));

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

}
