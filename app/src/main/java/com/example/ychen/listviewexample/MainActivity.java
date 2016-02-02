package com.example.ychen.listviewexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static List<String> values;

    private static int p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //super.onBackPressed();
        setContentView(R.layout.activity_main);

        final ListView listView = (ListView) findViewById(R.id.listView);
/*
        String[] values = new String[]{
                "A", "B", "C", "D", "E"
        };
  */
        if(values == null) {
            values = new ArrayList<String>();
            values.add("ok");
        }

        Intent i = getIntent();
        int position = i.getIntExtra("position", 0);
        if (i.getStringExtra("name") != null) {
            String task = i.getStringExtra("name");
            if(p != position)
                values.add(task);
            else
                values.set(position, task);
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPos = position;
                p = itemPos;
                String itemVal = (String) listView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPos + "  ListItem : " + itemVal, Toast.LENGTH_LONG)
                        .show();

                Intent detail = new Intent(getApplicationContext(), Main2Activity.class);
                detail.putExtra("position", position);
                detail.putExtra("name", itemVal);
                startActivity(detail);
                //finish();
            }
        });


        ImageButton ib = (ImageButton) findViewById(R.id.imageButton);

        ib.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Main2Activity.class);
                intent.putExtra("position", -1);
                startActivityForResult(intent, 0);
                //setContentView(R.layout.detail_view);
                //finish();
            }
        });



    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
