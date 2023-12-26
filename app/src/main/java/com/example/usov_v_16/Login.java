package com.example.usov_v_16;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity implements View.OnClickListener {
    DBHelper dbHelper;
    final String LOG_TAG = "myLogs";
    Button btndalee;
    EditText etLogin, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btndalee = findViewById(R.id.btnVhod);
        btndalee.setOnClickListener(this);
        etLogin = findViewById(R.id.login);
        etPassword = findViewById(R.id.password);
        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {
        // создаем объект для данных
        ContentValues cv = new ContentValues();
        // получаем данные из полей ввода
        String log = etLogin.getText().toString();
        String pas = etPassword.getText().toString();
        // подключаемся к БД
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (v.getId() == R.id.btnVhod) {
            Log.d(LOG_TAG, "--- Insert in mytable: ---");
            // подготовим данные для вставки в виде пар: наименование столбца - значение
            cv.put("login", log);
            cv.put("password", pas);
            // вставляем запись и получаем ее ID
            long rowID = db.insert("mytable", null, cv);
            Log.d(LOG_TAG, "row inserted, ID = " + rowID);
        }
        // закрываем подключение к БД
        db.close();
        Intent intent = new Intent(this, PersonalArea.class);
        intent.putExtra("login", log);
        startActivity(intent);
    }

    class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "--- onCreate database ---");
            // создаем таблицу с полями
            db.execSQL("create table mytable ("
                    + "id integer primary key autoincrement,"
                    + "login text,"
                    + "password text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // здесь можно описать процесс обновления базы данных при изменении версии
        }
    }
}