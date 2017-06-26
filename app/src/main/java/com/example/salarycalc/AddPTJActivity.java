package com.example.salarycalc;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

public class AddPTJActivity extends AppCompatActivity {

    //NumberPicker numberPicker;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ptj);
    }
/*
        MyOpenHelper helper = new MyOpenHelper(this);
        final SQLiteDatabase db = helper.getWritableDatabase();

        final EditText txt_jobname = (EditText) findViewById(R.id.txt_jobname);
        final EditText txt_salary = (EditText) findViewById(R.id.txt_salary);
*/

        //Button button_entry = (Button) findViewById(R.id.button_entry);
        //button_entry.setOnClickListener(new View.OnClickListener() {

            //@Override
    public void showData(View v) {
            MyOpenHelper helper = new MyOpenHelper(this);
            final SQLiteDatabase db = helper.getWritableDatabase();

            final EditText txt_jobname = (EditText) findViewById(R.id.txt_jobname);
            final EditText txt_salary = (EditText) findViewById(R.id.txt_salary);
                String jobname = txt_jobname.getText().toString();
                String salary = txt_salary.getText().toString();

                ContentValues insertValues = new ContentValues();
                insertValues.put("ptj_name", jobname);
                insertValues.put("salary", salary);
                long id = db.insert("ptj", jobname, insertValues);

                    //データを表示する
                    setContentView(R.layout.show_database);
                    Intent dbIntent = new Intent(AddPTJActivity.this,
                            ShowDataBase.class);
                    startActivity(dbIntent);


                }
/*
        //findViews();
    });
    }
*/

    private void findViews() {
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        //numberPicker = (NumberPicker)findViewById(R.id.numPicker);
        //int val = numberPicker.getValue();

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_add_ptj);
        layout.addView(textView);
    }

}
