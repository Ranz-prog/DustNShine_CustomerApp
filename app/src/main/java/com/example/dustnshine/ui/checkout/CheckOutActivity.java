package com.example.dustnshine.ui.checkout;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IntegerRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.dustnshine.R;
import com.example.dustnshine.databinding.ActivityCheckoutBinding;
import com.example.dustnshine.models.AddressModel;
import com.example.dustnshine.response.BookingServiceResponse;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.company_details.CompanyDetailsActivity;
import com.example.dustnshine.ui.home.HomeFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckOutActivity extends AppCompatActivity {

    private ImageView btnBack;
    private Dialog dialog;
    private TextView popText;
    private CheckOutViewModel checkOutViewModel;
    private ActivityCheckoutBinding activityCheckoutBinding;
    private Intent intent;
    private static ArrayList<Integer> selectedServices;
    private static ArrayList<String> servicesNameList;
    private static ArrayList<Map<Integer, Integer>> services;
    private static Map<Integer, Integer> serviceList;
    private static String userToken, companyName, companyAddress, customerFirstName, customerLastName, customerContactNumber, customerAddress, selectedDate, selectedTime;
    private static int companyID;
    private AddressModel addressModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityCheckoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_checkout);
        checkOutViewModel = new ViewModelProvider(CheckOutActivity.this).get(CheckOutViewModel.class);
        userToken = SharedPrefManager.getInstance(CheckOutActivity.this).getUserToken();
        addressModel = SharedPrefManager.getInstance(CheckOutActivity.this).getUserAddress();
        intent = getIntent();
        btnBack = findViewById(R.id.backCheckout);

        customerFirstName = SharedPrefManager.getInstance(CheckOutActivity.this).getUser().getFirst_name();
        customerLastName = SharedPrefManager.getInstance(CheckOutActivity.this).getUser().getLast_name();
        customerContactNumber = SharedPrefManager.getInstance(CheckOutActivity.this).getUser().getMobile_number();

        companyID = intent.getIntExtra("COMPANY_ID", 0);
        companyName = intent.getStringExtra("COMPANY_NAME");
        companyAddress = intent.getStringExtra("COMPANY_ADDRESS");
        selectedDate = intent.getStringExtra("SELECTED_DATE");
        selectedTime = intent.getStringExtra("SELECTED_TIME");
        selectedServices = intent.getIntegerArrayListExtra("SERVICES_ID_LIST");
        servicesNameList = intent.getStringArrayListExtra("SERVICES_NAME_LIST");

        activityCheckoutBinding.tvCompanyName.setText(companyName);
        activityCheckoutBinding.tvCustomerName.setText(customerFirstName + " " + customerLastName);
        activityCheckoutBinding.tvContactNumber.setText(customerContactNumber);
        activityCheckoutBinding.tvDateAndTime.setText(selectedDate + " " + selectedTime);
        activityCheckoutBinding.tvServiceName.setText(servicesNameList.toString());
        activityCheckoutBinding.tvAddress.setText(String.valueOf(addressModel.getHouse_number()) + " " + addressModel.getStreet() + " " + addressModel.getBarangay() + " " + addressModel.getMunicipality());

        serviceList = new HashMap<Integer, Integer>();
        getServices(serviceList, selectedServices);
        Log.d("SERVICES", String.valueOf(serviceList));
        services = new ArrayList<Map<Integer, Integer>>();
        services.add(serviceList);
        Log.d("TAG", String.valueOf(services));

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
                getBookingRequest(userToken, companyID, "San Fabian City", "2022-02-15 01:08:45", 2000, services);
                dialog.show();
                // Showing the dialog here
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void getBookingRequest(String userToken, int company_id, String address, String start_datetime, int total, ArrayList<Map<Integer, Integer>> services){
        checkOutViewModel.getBookingServiceRequest(userToken, company_id, address, start_datetime, total, services).observe(CheckOutActivity.this, new Observer<BookingServiceResponse>() {
            @Override
            public void onChanged(BookingServiceResponse bookingServiceResponse) {
                if (bookingServiceResponse == null){
                   // Toast.makeText(CheckOutActivity.this, bookingServiceResponse.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(CheckOutActivity.this, bookingServiceResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getServices(Map<Integer, Integer> services, ArrayList<Integer> servicesList){
        for(int i = 0; i < servicesList.size(); i++){
            services.put(i, servicesList.get(i));
            Log.d("TAG", services.toString());
        }
    }

}

