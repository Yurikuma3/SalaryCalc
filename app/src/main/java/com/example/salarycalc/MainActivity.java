package com.example.salarycalc;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;


public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.salarycalc.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        MyOpenHelperShift helper = new MyOpenHelperShift(this);
        final SQLiteDatabase db = helper.getWritableDatabase();

        TextView show = (TextView) findViewById(R.id.show_shift);

        Cursor c = db.query("shift", new String[]{"shift_name", "date", "time"}, null, null, null, null, null);

        boolean mov = c.moveToFirst();
        String shifts = "";

        while (mov) {

            shifts += String.format("バイト：%s 　日付 : %s 　時間 : %d 時間 \n",c.getString(0), c.getString(1), c.getInt(2));
            mov = c.moveToNext();
            //layout.addView(textView);
        }
        c.close();
        db.close();

        show.setText(shifts);
        */

        updateShift();

    }



    public void addPTJ(View view) {
        Intent intent = new Intent(this, AddPTJActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void addShift(View view) {
        Intent intent = new Intent(this, AddShiftActivity.class);
        startActivity(intent);
    }

    public void updateShift(){
        //setContentView(R.layout.activity_main);

        MyOpenHelperShift helper = new MyOpenHelperShift(this);
        final SQLiteDatabase db = helper.getWritableDatabase();

        TextView show = (TextView) findViewById(R.id.show_shift);

        Cursor c = db.query("shift", new String[]{"shift_name", "date", "time"}, null, null, null, null, null);

        boolean mov = c.moveToFirst();
        String shifts = "";

        while (mov) {

            shifts += String.format("バイト：%s 　日付 : %s 　時間 : %d 時間 \n",c.getString(0), c.getString(1), c.getInt(2));
            mov = c.moveToNext();
            //layout.addView(textView);
        }
        c.close();
        db.close();

        show.setText(shifts);

    }
}
