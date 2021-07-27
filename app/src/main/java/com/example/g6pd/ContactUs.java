package com.example.g6pd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;

public class ContactUs extends AppCompatActivity {

    EditText etName, etEmail, etMessage;
    Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_contact_us);
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etMessage = findViewById(R.id.et_message);
        btnSubmit = findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(v -> {
            if(checkName() && checkEmail() &&
                    !etMessage.getText().toString().isEmpty()) {
                sendMail();
                Toast.makeText(ContactUs.this, Constants.thankYouForFeedback, Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(ContactUs.this, Constants.pleaseFillAllFields, Toast.LENGTH_LONG).show();
            }
        });
    }
    void sendMail(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{Constants.michalEmail});
        intent.putExtra(Intent.EXTRA_SUBJECT, etName.getText().toString().trim());
        intent.putExtra(Intent.EXTRA_TEXT, etMessage.getText().toString().trim());
        intent.setType("message/rfc822");
        startActivity(intent);
        etName.setText("");
        etEmail.setText("");
        etMessage.setText("");
    }
    public boolean checkName(){
        boolean isChecked;
        if (etName.getText().toString().length() < 2){
            isChecked = false;
        }else {
            isChecked = true;
        }
        return isChecked;
    }
    public boolean checkEmail(){
        boolean isChecked;
        Pattern pattern = Pattern.compile(Constants.regex);
        Matcher matcher = pattern.matcher(etEmail.getText().toString());
        if (matcher.matches()){
            isChecked = false;
        }else {
            isChecked = true;
        }
        return isChecked;
    }
}