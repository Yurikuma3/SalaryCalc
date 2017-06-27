package com.example.salarycalc;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Calendar;

import static com.example.salarycalc.R.id.button_entry;
import static com.example.salarycalc.R.id.textView;

public class AddShiftActivity extends AppCompatActivity {

    NumberPicker numPicker;
    Spinner spinner;
    private DatePickerDialog.OnDateSetListener varDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shift);

        setSpinner();

        final EditText editText = (EditText)findViewById(R.id.txtDate);

        varDateSetListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view , int year , int monthOfYear , int dayOfMonth){
                editText.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
            }
        };

    }

    public void selectDate(View view){
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dateDialog = new DatePickerDialog(
                AddShiftActivity.this,
                varDateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        dateDialog.show();
    }

    private void setSpinner(){
        spinner = (Spinner)findViewById(R.id.spName);

        // Listの作成
        ArrayList<String> list = new ArrayList<String>();

        // sqlからの読み込み
        MyOpenHelper helper = new MyOpenHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        SQLiteCursor c = (SQLiteCursor)db.query("ptj",new String[] {"ptj_name"}, null,null,null,null,null);

        boolean mov = c.moveToFirst();
        while (mov) {
            list.add(String.format("%s", c.getString(0)));
            mov = c.moveToNext();
        }

        // Adapterの作成
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        // ドロップダウンのレイアウトを指定
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // ListViewにAdapterを関連付ける
        Spinner spinner = (Spinner) findViewById(R.id.spName);
        spinner.setAdapter(adapter);
    }

    public void shiftInput(View v){
        MyOpenHelper helper = new MyOpenHelper(this);
        final SQLiteDatabase db = helper.getWritableDatabase();

        final EditText date = (EditText) findViewById(R.id.txtDate);
        Spinner shift_name = (Spinner) findViewById(R.id.spName);
        Spinner time = (Spinner) findViewById(R.id.spTime);

        int timeIdx = time.getSelectedItemPosition() + 1; //シフトが入っている時間
        String name = (String) shift_name.getSelectedItem(); //選択したバイト
        String shitf_date = date.getText().toString();  //シフトが入っている日

        ContentValues contentValues = new ContentValues();
        contentValues.put("shift_name", name);
        contentValues.put("date", shitf_date);

        long id = db.insert("shift", name, contentValues); //登録したデータのIDを取得

    }

}
