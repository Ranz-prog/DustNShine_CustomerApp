package com.example.dustnshine.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dustnshine.R;
import com.example.dustnshine.ui.signin.SignInActivity;
import com.example.dustnshine.ui.signup.SignUpActivity;
import com.google.android.material.snackbar.Snackbar;

public final class AppConstants {

    public static final String BASE_URL = "https://lpn.boomtech.co/";
    public static final String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static final String APP_ID = "2055343da9e0c70c";
    public static final String REGION = "us";
    public static final String API_KEY = "71cc1e22e3a4a36fb5a49ec0be00b980a714782e";

    public static String GROUP_ID = "group_id";
    public static String GROUP_NAME = "group_id";
    public static String GROUP_ICON = "group_id";
    
    public static void alertMessage(Integer alert, Integer image, String title, String message, Context context, Class destination){

        Dialog showMessage;
        ImageView imgAlert;
        TextView tvTitle, tvMessage;
        Button btnOkay;

        showMessage = new Dialog(context);
        showMessage.setContentView(R.layout.pop_up_reference);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            showMessage.getWindow().setBackgroundDrawable(context.getDrawable(R.drawable.pop_up_background));
        }
        showMessage.setCancelable(false);
        showMessage.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        imgAlert = showMessage.findViewById(R.id.imgAlert);
        tvTitle = showMessage.findViewById(R.id.tvTitle);
        tvMessage = showMessage.findViewById(R.id.tvMessage);
        btnOkay = showMessage.findViewById(R.id.btnOkay);

        imgAlert.setImageResource(image);
        tvTitle.setText(title);
        tvMessage.setText(message);

        showMessage.show();

        btnOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alert == 1){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(context, destination);
                            context.startActivity(intent);
                        }
                    }, 1000);
                } else {
                    showMessage.dismiss();
                }
            }
        });
    }


}
