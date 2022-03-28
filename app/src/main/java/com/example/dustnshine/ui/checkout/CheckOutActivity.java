package com.example.dustnshine.ui.checkout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.dustnshine.MainActivity;
import com.example.dustnshine.R;
import com.example.dustnshine.databinding.ActivityCheckoutBinding;
import com.example.dustnshine.response.BookingServiceResponse;
import com.example.dustnshine.response.UserManagementResponse;
import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.company_details.CompanyDetailsActivity;
import com.example.dustnshine.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckOutActivity extends AppCompatActivity {

    private CheckOutViewModel checkOutViewModel;
    private ActivityCheckoutBinding activityCheckoutBinding;
    private Intent intent;
    private static ArrayList<Integer> servicesIDList;
    private static ArrayList<String> servicesNameList;
    private static ArrayList<Integer> servicesPriceList;
    private static String notes;
    private static String userToken, companyName, companyAddress, customerAddress, selectedDate, selectedTime;
    private static int companyID;
    private static int total;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityCheckoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_checkout);
        checkOutViewModel = new ViewModelProvider(CheckOutActivity.this).get(CheckOutViewModel.class);
        userToken = SharedPrefManager.getInstance(CheckOutActivity.this).getUserToken();
        intent = getIntent();
        getUserInformation(userToken);

        servicesIDList = new ArrayList<Integer>();
        servicesNameList = new ArrayList<String>();
        servicesPriceList = new ArrayList<Integer>();

        companyID = intent.getIntExtra("COMPANY_ID", 0);
        companyName = intent.getStringExtra("COMPANY_NAME");
        companyAddress = intent.getStringExtra("COMPANY_ADDRESS");
        selectedDate = intent.getStringExtra("SELECTED_DATE");
        selectedTime = intent.getStringExtra("SELECTED_TIME");
        servicesIDList = intent.getIntegerArrayListExtra("SERVICES_ID_LIST");
        servicesNameList = intent.getStringArrayListExtra("SERVICES_NAME_LIST");
        servicesPriceList = intent.getIntegerArrayListExtra("SERVICES_PRICE_LIST");
        notes = intent.getStringExtra("NOTES");

        total = servicesPrice(servicesPriceList);

        activityCheckoutBinding.tvCompanyName.setText(companyName);
        activityCheckoutBinding.tvDateAndTime.setText(selectedDate + " " + selectedTime);
        activityCheckoutBinding.tvServiceName.setText(servicesNameList.toString().replaceAll("(^\\[|\\]$)", ""));
        activityCheckoutBinding.tvNotes.setText(notes);
        activityCheckoutBinding.tvTotalCost.setText(String.valueOf(total));

        activityCheckoutBinding.btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBookingRequest(userToken, companyID, customerAddress, selectedDate + " " + selectedTime, total, servicesIDList, notes);
            }
        });

        activityCheckoutBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getBookingRequest(String userToken, int company_id, String address, String start_datetime, int total, List<Integer> services, String notes){
        checkOutViewModel.getBookingServiceRequest(userToken, company_id, address, start_datetime, total, services, notes).observe(CheckOutActivity.this, new Observer<BookingServiceResponse>() {
            @Override
            public void onChanged(BookingServiceResponse bookingServiceResponse) {
                if (bookingServiceResponse == null){
                    AppConstants.alertMessage(1, R.drawable.ic_error_2, "Failed!", "Booking failed. Try Again", CheckOutActivity.this, MainActivity.class, "VISIBLE");
                } else {
                    AppConstants.alertMessage(1, R.drawable.check, "Success!", "Booked Successfully", CheckOutActivity.this, MainActivity.class, "GONE");
                }
            }
        });
    }

    private void getServices(Map<Integer, Integer> services, ArrayList<Integer> servicesList){
        for(int i = 0; i < servicesList.size(); i++){
            services.put(i, servicesList.get(i));
        }
    }

    private int servicesPrice(ArrayList<Integer> priceList)
    {
        int price = 0;
        for(int i = 0; i < priceList.size(); i++)
        {
            price = price + priceList.get(i);
        }
        return price;
    }

    private void getUserInformation(String userToken){
        checkOutViewModel.getUserInformationRequest(userToken).observe(CheckOutActivity.this, new Observer<UserManagementResponse>() {
            @Override
            public void onChanged(UserManagementResponse userManagementResponse) {
                if(userManagementResponse == null){
                    Log.d("TAG", "Invalid Request");
                } else {
                    activityCheckoutBinding.tvCustomerName.setText(userManagementResponse.getData().get(0).getFirst_name() + " " + userManagementResponse.getData().get(0).getLast_name());
                    activityCheckoutBinding.tvContactNumber.setText(userManagementResponse.getData().get(0).getMobile_number());
                    activityCheckoutBinding.tvAddress.setText(userManagementResponse.getData().get(0).getAddress().getHouse_number() + " " + userManagementResponse.getData().get(0).getAddress().getStreet() + " "+ userManagementResponse.getData().get(0).getAddress().getBarangay() + " " + userManagementResponse.getData().get(0).getAddress().getMunicipality());
                    customerAddress = userManagementResponse.getData().get(0).getAddress().getMunicipality();
                }
            }
        });
    }
}

