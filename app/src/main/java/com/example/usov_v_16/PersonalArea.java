package com.example.usov_v_16;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.w3c.dom.Text;

public class PersonalArea extends AppCompatActivity implements View.OnClickListener {
Text log;
Button btn, btnB;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_area);

        Intent intent = getIntent();
        String login = intent.getStringExtra("login");

        btn = findViewById(R.id.btncall);
        btn.setOnClickListener(this);
        btnB = findViewById(R.id.btnback);
        btnB.setOnClickListener(this);
        log = findViewById(R.id.textView);
        log.setTextContent("Логин: " + login);
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btncall){
            String phoneNumber = "+79244112311";
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phoneNumber));

            // Проверяем, есть ли приложение для обработки данного интента
            if (intent.resolveActivity(getPackageManager()) != null) {
                // Если есть, запускаем интент
                startActivity(intent);
            } else {
                // Если нет, выводим сообщение об ошибке
                Toast.makeText(getApplicationContext(), "Приложение для звонков не найдено", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            startActivity(new Intent(this, Login.class));
        }
    }

}