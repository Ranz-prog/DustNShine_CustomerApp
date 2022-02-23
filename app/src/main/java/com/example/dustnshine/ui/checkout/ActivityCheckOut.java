package com.example.dustnshine.ui.checkout;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.dustnshine.MainActivity;
import com.example.dustnshine.R;
import com.example.dustnshine.response.BookingServiceResponse;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.ActivityCompanyDetails;
import com.example.dustnshine.ui.CompanyDetailsViewModel;
import com.example.dustnshine.ui.signin.ActivitySignIn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityCheckOut extends AppCompatActivity {

    private Button checkOut;

    Dialog dialog;
    private TextView popText, txtCustomerName, txtLocation, txtContactNumber;
    private List<Map<Integer, Integer>> services;
    private Map<Integer, Integer> company;
    private CheckOutViewModel checkOutViewModel;
    private String userToken;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        checkOut = findViewById(R.id.checkOutBtn);
        txtCustomerName = findViewById(R.id.txtCustomerName);
        txtLocation = findViewById(R.id.txtLocation);
        txtContactNumber= findViewById(R.id.txtContactNumber);
        userToken = SharedPrefManager.getInstance(ActivityCheckOut.this).getUserToken();

        txtCustomerName.setText(SharedPrefManager.getInstance(ActivityCheckOut.this).getUser().getFirst_name());
        txtContactNumber.setText(SharedPrefManager.getInstance(ActivityCheckOut.this).getUser().getMobile_number());

        // DIALOG BOX START
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.pop_up_reference);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.pop_up_background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false); //Optional para lang d mag close pag clinick ang labas
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog

        checkOutViewModel = new ViewModelProvider(ActivityCheckOut.this).get(CheckOutViewModel.class);

        Button Okay = dialog.findViewById(R.id.btn_okay);
        popText = dialog.findViewById(R.id.popUpText);
        String text= "Thank you. Checkout is successful!";// Set Message Here
        popText.setText(text.toString());

        company = new HashMap<Integer, Integer>();
        company.put(0, 1);
        company.put(1, 1);
        services = new ArrayList<Map<Integer, Integer>>();
        services.add(company);


        Okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActivityCheckOut.this, "Success", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        //END OF DIALOG BOX

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBookingRequest(userToken, 1, "San Carlos City", "2022-05-05 00:00:00", 1000, services);
                dialog.show();
                Intent intent = new Intent(ActivityCheckOut.this, ActivityCompanyDetails.class);
                startActivity(intent);// Showing the dialog here
            }
        });

    }
    public void getBookingRequest(String userToken, int company_id, String address, String start_datetime, int total, List<Map<Integer, Integer>> services){
        checkOutViewModel.getBookingServiceRequest(userToken, company_id, address, start_datetime, total, services).observe(ActivityCheckOut.this, new Observer<BookingServiceResponse>() {
            @Override
            public void onChanged(BookingServiceResponse bookingServiceResponse) {
                if (bookingServiceResponse == null){
                    Toast.makeText(ActivityCheckOut.this, bookingServiceResponse.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ActivityCheckOut.this, bookingServiceResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}

