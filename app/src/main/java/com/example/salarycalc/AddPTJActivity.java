package com.example.salarycalc;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddPTJActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ptj);
    }

    public void showData(View v) {
        //データを表示する
        setContentView(R.layout.activity_add_ptj);
        Intent dbIntent = new Intent(AddPTJActivity.this,ShowDataBase.class);
        startActivity(dbIntent);
    }

    public void dataInput(View v){
        MyOpenHelper helper = new MyOpenHelper(this);
        final SQLiteDatabase db = helper.getWritableDatabase();

        final EditText txtJob = (EditText) findViewById(R.id.txtJob); // テキストの場所を見つけて
        final EditText txtSalary = (EditText) findViewById(R.id.txtSalary);
        String jobname = txtJob.getText().toString(); // Stringに直す
        String salary = txtSalary.getText().toString();

        if(jobname.equals("")) {
            Toast.makeText(AddPTJActivity.this,"バイト先を入力してください。", Toast.LENGTH_SHORT).show();
        } else if(salary.equals("")) {
            Toast.makeText(AddPTJActivity.this, "時給を入力してください。", Toast.LENGTH_SHORT).show();
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("ptj_name", jobname);
            contentValues.put("salary", salary);
            long id = db.insert("ptj", jobname, contentValues); //登録したデータのIDを取得
            Toast.makeText(AddPTJActivity.this, "登録完了", Toast.LENGTH_SHORT).show();
            txtJob.setText("");
            txtSalary.setText("");
        }

    }

    public void toTop(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Disable Back key
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

}
