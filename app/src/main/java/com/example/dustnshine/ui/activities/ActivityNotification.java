package com.example.dustnshine.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dustnshine.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityNotification extends AppCompatActivity {

    LinearLayout returnHome;
    ListView notifications;
    ImageView noTrans;
    TextView transactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_notification);

        returnHome=findViewById(R.id.btnReturnBooking);
        notifications = findViewById(R.id.notifList);
        noTrans = findViewById(R.id.imgNoTransactions);
        transactions = findViewById(R.id.noTransactionsTV);

        List<String> list = new ArrayList<>();
        list.add("Please rate our Work!");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,list);
        notifications.setAdapter(arrayAdapter);

        notifications.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent intent = new Intent(ActivityNotification.this, ActivityFeedBack.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(ActivityNotification.this, ActivityFeedBack.class);
                    startActivity(intent);
                }
            }
        });

        if (list.contains("Please rate our Work!")){
            noTrans.setVisibility(View.GONE);
            transactions.setVisibility(View.GONE);
        }
        else{
            noTrans.setVisibility(View.VISIBLE);
            transactions.setVisibility(View.VISIBLE);
        }
        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
