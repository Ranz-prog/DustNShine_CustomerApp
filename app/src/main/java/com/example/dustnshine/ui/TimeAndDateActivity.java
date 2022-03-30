package com.example.dustnshine.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;

import com.example.dustnshine.R;
import com.example.dustnshine.databinding.ActivityTimeAndDateBinding;
import com.example.dustnshine.ui.checkout.CheckOutActivity;
import com.example.dustnshine.utils.AppConstants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TimeAndDateActivity extends AppCompatActivity{

    private Intent intent;
    private static String companyName, companyAddress, selectedDate, selectedTime;
    private static int companyID;
    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;
    private ActivityTimeAndDateBinding activityTimeAndDateBinding;
    private static ArrayList<Integer> servicesIDList;
    private static ArrayList<String> servicesNameList;
    private static ArrayList<Integer> servicesPriceList;
    private static String notes, dateTimeNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityTimeAndDateBinding = DataBindingUtil.setContentView(this, R.layout.activity_time_and_date);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        intent = getIntent();

        companyID = intent.getIntExtra("COMPANY_ID", 0);
        companyName = intent.getStringExtra("COMPANY_NAME");
        companyAddress = intent.getStringExtra("COMPANY_ADDRESS");
        servicesIDList = intent.getIntegerArrayListExtra("SERVICES_ID_LIST");
        servicesNameList = intent.getStringArrayListExtra("SERVICES_NAME_LIST");
        servicesPriceList = intent.getIntegerArrayListExtra("SERVICES_PRICE_LIST");
        notes = intent.getStringExtra("NOTES");

        activityTimeAndDateBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                servicesIDList.removeAll(servicesIDList);
                servicesNameList.removeAll(servicesNameList);
            }
        });
        activityTimeAndDateBinding.calendarView.setMinDate(System.currentTimeMillis()- 1000);
        activityTimeAndDateBinding.calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int date) {
                calendar = Calendar.getInstance();
                calendar.set(year, month, date);
                selectedDate = simpleDateFormat.format(calendar.getTime());
                dateTimeNow = simpleDateFormat.format(calendarView.getDate());;
                Log.d("DATE", dateTimeNow);
            }
        });

        activityTimeAndDateBinding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedDate == null){
                    AppConstants.alertMessage(0, R.drawable.ic_error_2, "Failed!", "Please select Date", TimeAndDateActivity.this, TimeAndDateActivity.class, "VISIBLE");
                } else if (selectedTime == null) {
                    AppConstants.alertMessage(0, R.drawable.ic_error_2, "Failed!", "Please select Time", TimeAndDateActivity.this, TimeAndDateActivity.class, "VISIBLE");
                } else if (selectedDate.equals(dateTimeNow)) {
                    AppConstants.alertMessage(0, R.drawable.ic_error_2, "Failed!", "Current date can not be selected", TimeAndDateActivity.this, TimeAndDateActivity.class, "VISIBLE");
                } else {
                    Intent intent = new Intent(TimeAndDateActivity.this, CheckOutActivity.class);
                    intent.putExtra("COMPANY_ID", companyID);
                    intent.putExtra("COMPANY_NAME", companyName);
                    intent.putExtra("COMPANY_ADDRESS", companyAddress);
                    intent.putIntegerArrayListExtra("SERVICES_ID_LIST", servicesIDList);
                    intent.putStringArrayListExtra("SERVICES_NAME_LIST", servicesNameList);
                    intent.putIntegerArrayListExtra("SERVICES_PRICE_LIST", servicesPriceList);
                    intent.putExtra("NOTES", notes);
                    intent.putExtra("SELECTED_DATE", selectedDate);
                    intent.putExtra("SELECTED_TIME", selectedTime);
                    startActivity(intent);
                }
            }
        });

        activityTimeAndDateBinding.btnEightAM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTime = "08:00:00";
                view.setBackgroundResource(R.drawable.datetime_selected_round_button);
                activityTimeAndDateBinding.btnNineAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnTenAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnElevenAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnTwelvePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnOnePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnTwoPM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnThreePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnFourPM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnFivePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
            }
        });

        activityTimeAndDateBinding.btnNineAM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTime = "09:00:00";
                view.setBackgroundResource(R.drawable.datetime_selected_round_button);
                activityTimeAndDateBinding.btnEightAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnTenAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnElevenAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnTwelvePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnOnePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnTwoPM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnThreePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnFourPM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnFivePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
            }
        });

        activityTimeAndDateBinding.btnTenAM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTime = "10:00:00";
                view.setBackgroundResource(R.drawable.datetime_selected_round_button);
                activityTimeAndDateBinding.btnEightAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnNineAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnElevenAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnTwelvePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnOnePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnTwoPM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnThreePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnFourPM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnFivePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
            }
        });

        activityTimeAndDateBinding.btnElevenAM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTime = "11:00:00";
                view.setBackgroundResource(R.drawable.datetime_selected_round_button);
                activityTimeAndDateBinding.btnEightAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnNineAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnTenAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnTwelvePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnOnePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnTwoPM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnThreePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnFourPM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnFivePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
            }
        });

        activityTimeAndDateBinding.btnTwelvePM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTime = "12:00:00";
                view.setBackgroundResource(R.drawable.datetime_selected_round_button);
                activityTimeAndDateBinding.btnEightAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnNineAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnTenAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnElevenAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnOnePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnTwoPM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnThreePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnFourPM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnFivePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
            }
        });

        activityTimeAndDateBinding.btnOnePM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTime = "13:00:00";
                view.setBackgroundResource(R.drawable.datetime_selected_round_button);
                activityTimeAndDateBinding.btnEightAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnNineAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnTenAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnElevenAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnTwelvePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnTwoPM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnThreePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnFourPM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnFivePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
            }
        });

        activityTimeAndDateBinding.btnTwoPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTime = "14:00:00";
                view.setBackgroundResource(R.drawable.datetime_selected_round_button);
                activityTimeAndDateBinding.btnEightAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnNineAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnTenAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnElevenAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnTwelvePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnOnePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnThreePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnFourPM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnFivePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
            }
        });

        activityTimeAndDateBinding.btnThreePM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTime = "15:00:00";
                view.setBackgroundResource(R.drawable.datetime_selected_round_button);
                activityTimeAndDateBinding.btnEightAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnNineAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnTenAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnElevenAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnTwelvePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnOnePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnTwoPM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnFourPM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnFivePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
            }
        });

        activityTimeAndDateBinding.btnFourPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTime = "16:00:00";
                view.setBackgroundResource(R.drawable.datetime_selected_round_button);
                activityTimeAndDateBinding.btnEightAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnNineAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnTenAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnElevenAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnTwelvePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnOnePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnTwoPM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnThreePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnFivePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
            }
        });

        activityTimeAndDateBinding.btnFivePM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTime = "17:00:00";
                view.setBackgroundResource(R.drawable.datetime_selected_round_button);
                activityTimeAndDateBinding.btnEightAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnNineAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnTenAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnElevenAM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnTwelvePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnOnePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnTwoPM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnThreePM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
                activityTimeAndDateBinding.btnFourPM.setBackgroundResource(R.drawable.datetime_unselected_round_button);
            }
        });

    }

}