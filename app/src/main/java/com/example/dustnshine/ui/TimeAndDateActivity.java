package com.example.dustnshine.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dustnshine.R;
import com.example.dustnshine.databinding.ActivityTimeAndDateBinding;
import com.example.dustnshine.ui.checkout.CheckOutActivity;
import com.example.dustnshine.ui.company_details.CompanyDetailsActivity;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TimeAndDateActivity extends AppCompatActivity{

    private ImageView btnBack;
    private Intent intent;
    private static String companyName, companyAddress, selectedDate, selectedTime;
    private static int companyID;
    private CalendarView calendarView;
    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;
    private ActivityTimeAndDateBinding activityTimeAndDateBinding;
    private static ArrayList<Integer> servicesIDList;
    private static ArrayList<String> servicesNameList;
    private static ArrayList<Integer> servicesPriceList;
    private String notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTimeAndDateBinding = DataBindingUtil.setContentView(this, R.layout.activity_time_and_date);

        btnBack = findViewById(R.id.backDandT);
        calendarView = findViewById(R.id.calendar);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        intent = getIntent();

        companyID = intent.getIntExtra("COMPANY_ID", 0);
        companyName = intent.getStringExtra("COMPANY_NAME");
        companyAddress = intent.getStringExtra("COMPANY_ADDRESS");
        servicesIDList = intent.getIntegerArrayListExtra("SERVICES_ID_LIST");
        servicesNameList = intent.getStringArrayListExtra("SERVICES_NAME_LIST");
        servicesPriceList = intent.getIntegerArrayListExtra("SERVICES_PRICE_LIST");
        notes = intent.getStringExtra("NOTES");

        Log.d("Nagana", servicesIDList.toString());
        Log.d("Nagana", servicesNameList.toString());
        Log.d("Nagana", servicesPriceList.toString());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                servicesIDList.removeAll(servicesIDList);
                servicesNameList.removeAll(servicesNameList);
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int date) {
                calendar = Calendar.getInstance();
                calendar.set(year, month, date);
                selectedDate = simpleDateFormat.format(calendar.getTime());
            }
        });

        activityTimeAndDateBinding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedDate == null || selectedTime == null){
                    Toast.makeText(TimeAndDateActivity.this, "Please select Date and Time", Toast.LENGTH_SHORT).show();
                } else if(selectedDate == null) {

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