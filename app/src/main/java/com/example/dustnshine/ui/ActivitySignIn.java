package com.example.dustnshine.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dustnshine.MainActivity;
import com.example.dustnshine.R;

public class ActivitySignIn extends AppCompatActivity {

    private long backButtonCount;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    EditText emailSearch;
    Button search;

    TextView createAcc,forget;
    Button signInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_signin);

        createAcc = findViewById(R.id.createAcc);
        signInBtn = findViewById(R.id.btnServerLogin);
        forget = findViewById(R.id.forget);

        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivitySignIn.this, ActivitySignUp.class);
                startActivity(intent);
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivitySignIn.this, MainActivity.class);
                startActivity(intent);
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewDialog();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        if(backButtonCount >= 1)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            finish();
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }

    public void  createNewDialog(){
            dialogBuilder = new AlertDialog.Builder(this);
            final  View searchPopUp = getLayoutInflater().inflate(R.layout.activity_search_email,null);
            emailSearch = searchPopUp.findViewById(R.id.enterEmailSearch);

            search = searchPopUp.findViewById(R.id.searchBtn);

            dialogBuilder.setView(searchPopUp);
            dialog = dialogBuilder.create();
            dialog.show();

            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ActivitySignIn.this, ActivityForgetPassword.class);
                    startActivity(intent);
                }
            });



    }
}
