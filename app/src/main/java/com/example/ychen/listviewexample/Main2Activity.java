package com.example.ychen.listviewexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bindEdit();
        bindSave();
    }

    private void bindSave() {

        Button btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                EditText et = (EditText) findViewById(R.id.editText);
                String task = et.getText().toString();


                Intent intent = new Intent(v.getContext(), MainActivity.class);
                intent.putExtra("position",pos);
                intent.putExtra("name", task);
                startActivity(intent);
            }
        });

    }

    private void bindEdit(){
        Intent i = getIntent();
        pos = i.getIntExtra("position", 0);
        String name = i.getStringExtra("name");
        EditText et = (EditText) findViewById(R.id.editText);
        et.setText(name);
    }




}
