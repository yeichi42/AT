package com.example.ychen.listviewexample;

import android.app.DialogFragment;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main2Activity extends AppCompatActivity {

    private int id;
    private DateTime dt;
    private TodoDAO todoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        todoDAO = new TodoDAO(this);
        todoDAO.open();

        bindEdit();
    }

    public void onClick(View view) {
        EditText et = (EditText) findViewById(R.id.editText);
        String task = et.getText().toString();
        Intent intent = new Intent(view.getContext(), MainActivity.class);

        TextView txtDate = (TextView) findViewById(R.id.textView2);
        TextView txtTime = (TextView) findViewById(R.id.textView3);

        String combined = txtDate.getText().toString() +" "+ txtTime.getText().toString();

        switch (view.getId()) {
            case R.id.button:

                if(id == -1) {
                    todoDAO.createTodo(task, combined);
                }
                else {
                    todoDAO.updateTodo(task, id, combined);
                }
                startActivity(intent);
                break;

            case R.id.button2:
                todoDAO.deleteTodo(id);
                startActivity(intent);
                break;

            default:
                Log.e("MISS", "Miss");
                break;
        }

    }

    private void bindEdit() {
        Intent i = getIntent();
        id = i.getIntExtra("id", 0);
        String name = i.getStringExtra("name");
        boolean edit = i.getBooleanExtra("edit", false);
        String date = i.getStringExtra("date");

        Button btnDel = (Button) findViewById(R.id.button2);
        if(edit) {
            btnDel.setVisibility(View.VISIBLE);
            TextView txtDate = (TextView) findViewById(R.id.textView2);
            TextView txtTime = (TextView) findViewById(R.id.textView3);
            txtDate.setText(date.split(" ")[0]);
            txtTime.setText(date.split(" ")[1] + " " + date.split(" ")[2]);
        }

        EditText et = (EditText) findViewById(R.id.editText);
        et.setText(name);


    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DateTime.DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new DateTime.TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }

}
