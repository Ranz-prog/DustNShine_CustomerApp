package com.example.dustnshine.ui.activities;

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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dustnshine.R;

public class ActivityCheckOut extends AppCompatActivity {

    LinearLayout btnBack;

    Button checkOut,agree;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog termsNConditions;

    Dialog dialog;
    TextView popText,termsAndConditionsText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        checkOut = findViewById(R.id.checkOutBtn);

        btnBack = findViewById(R.id.ReturnBtnOncheckout);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewDialog();
            }
        });

    }

    public void  createNewDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        final  View searchPopUp = getLayoutInflater().inflate(R.layout.terms_and_conditions_pop_up,null);
        termsAndConditionsText = searchPopUp.findViewById(R.id.termsTV);

        agree = searchPopUp.findViewById(R.id.btn_accept);

        dialogBuilder.setView(searchPopUp);
        termsNConditions = dialogBuilder.create();
        termsNConditions.show();

        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                termsNConditions.dismiss();
                dialogBox();
                dialog.show();
            }
        });



    }

    public void dialogBox(){
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
        String text= "Thank you. Checkout is successful!";// Set Message Here
        popText.setText(text.toString());


        Okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActivityCheckOut.this, "Success", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        //END OF DIALOG BOX
    }


}

