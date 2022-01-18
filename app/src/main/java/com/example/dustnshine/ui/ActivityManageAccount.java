package com.example.dustnshine.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dustnshine.R;

public class ActivityManageAccount extends AppCompatActivity {

    Button personal, edit, cancel, save;
    AlertDialog.Builder dialogBuilder;
    AlertDialog dialog;
    EditText fname, lname, email, mobile, pass,repass, house , street,  barangay,city,province, zip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_acc);

        personal = findViewById(R.id.PersonalInfoBtn);

        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewContactDialog();

            }
        });
    }

    public void createNewContactDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.personal_info_pop_layout,null);
        fname = contactPopupView.findViewById(R.id.firstName);
        lname = contactPopupView.findViewById(R.id.lastName);
        email = contactPopupView.findViewById(R.id.email);
        mobile = contactPopupView.findViewById(R.id.mobileNum);
        pass = contactPopupView.findViewById(R.id.Password);
        repass = contactPopupView.findViewById(R.id.reytpePass);
        house = contactPopupView.findViewById(R.id.houseNo);
        street = contactPopupView.findViewById(R.id.street);
        barangay = contactPopupView.findViewById(R.id.barangay);
        city = contactPopupView.findViewById(R.id.city);
        province = contactPopupView.findViewById(R.id.province);
        zip = contactPopupView.findViewById(R.id.zip);

        edit = contactPopupView.findViewById(R.id.edit);
        cancel = contactPopupView.findViewById(R.id.cancel);
        save = contactPopupView.findViewById(R.id.save);

        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }
}
