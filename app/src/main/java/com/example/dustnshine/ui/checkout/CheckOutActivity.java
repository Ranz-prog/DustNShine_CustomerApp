package com.example.dustnshine.ui.checkout;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.dustnshine.R;
import com.example.dustnshine.databinding.ActivityCheckoutBinding;
import com.example.dustnshine.response.BookingServiceResponse;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.company_details.CompanyDetailsActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckOutActivity extends AppCompatActivity {

    private LinearLayout btnBack;
    private Dialog dialog;
    private TextView popText;
    private CheckOutViewModel checkOutViewModel;
    private ActivityCheckoutBinding activityCheckoutBinding;
    private Intent intent;
    private Date dateTime;
    private static List<Map<Integer, Integer>> services;
    private static Map<Integer, Integer> company;
    private static String userToken, companyName, companyAddress, customerFirstName, customerLastName, customerContactNumber, customerAddress;
    private static int companyID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityCheckoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_checkout);
        checkOutViewModel = new ViewModelProvider(CheckOutActivity.this).get(CheckOutViewModel.class);
        userToken = SharedPrefManager.getInstance(CheckOutActivity.this).getUserToken();
        intent = getIntent();
        btnBack = findViewById(R.id.btnBack);
        dateTime = Calendar.getInstance().getTime();

        customerFirstName = SharedPrefManager.getInstance(CheckOutActivity.this).getUser().getFirst_name();
        customerLastName = SharedPrefManager.getInstance(CheckOutActivity.this).getUser().getLast_name();
        customerContactNumber = SharedPrefManager.getInstance(CheckOutActivity.this).getUser().getMobile_number();

        companyID = intent.getIntExtra("COMPANY_ID", 0);
        companyName = intent.getStringExtra("COMPANY_NAME");
        companyAddress = intent.getStringExtra("COMPANY_ADDRESS");

        activityCheckoutBinding.txtCompanyName.setText(companyName);
        activityCheckoutBinding.txtCustomerName.setText(customerFirstName + " " + customerLastName);
        activityCheckoutBinding.txtContactNumber.setText(customerContactNumber + " " + dateTime);

        // DIALOG BOX START
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.pop_up_reference);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.pop_up_background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false); //Optional para lang d mag close pag clinick ang labas
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog

        Button Okay = dialog.findViewById(R.id.btn_okay);
        popText = dialog.findViewById(R.id.popUpText);
        String text = "Thank you. Checkout is successful!";// Set Message Here
        popText.setText(text.toString());

        company = new HashMap<Integer, Integer>();
        company.put(0, 1);
        company.put(1, 1);
        services = new ArrayList<Map<Integer, Integer>>();
        services.add(company);

        Okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CheckOutActivity.this, "Success", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        //END OF DIALOG BOX

        activityCheckoutBinding.btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBookingRequest(userToken, companyID, "San Carlos City", "2022-05-05 00:00:00", 1000, services);
                dialog.show();
                Intent intent = new Intent(CheckOutActivity.this, CompanyDetailsActivity.class);
                startActivity(intent);// Showing the dialog here
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void getBookingRequest(String userToken, int company_id, String address, String start_datetime, int total, List<Map<Integer, Integer>> services){
        checkOutViewModel.getBookingServiceRequest(userToken, company_id, address, start_datetime, total, services).observe(CheckOutActivity.this, new Observer<BookingServiceResponse>() {
            @Override
            public void onChanged(BookingServiceResponse bookingServiceResponse) {
                if (bookingServiceResponse == null){
                    Toast.makeText(CheckOutActivity.this, bookingServiceResponse.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CheckOutActivity.this, bookingServiceResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
