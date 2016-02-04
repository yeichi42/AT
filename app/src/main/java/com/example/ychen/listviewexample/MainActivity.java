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

    private static List<Todo> todoList;
    private TodoDAO todoDAO;
    private static int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoDAO = new TodoDAO(this);
        todoDAO.open();

        todoList = todoDAO.getAllTodos();

        final ListView listView = (ListView) findViewById(R.id.listView);

        ArrayAdapter<Todo> adapter = new ArrayAdapter<Todo>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, todoList);

        listView.setAdapter(adapter);

        //need to refactor this
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPos = position;
                Todo itemVal = (Todo) listView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPos + "  ListItem : " + itemVal, Toast.LENGTH_LONG)
                        .show();

                Intent detail = new Intent(getApplicationContext(), Main2Activity.class);
                detail.putExtra("name", itemVal.getName());
                detail.putExtra("id", itemVal.getId());
                detail.putExtra("edit", true);
                startActivity(detail);
            }
        });
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.imageButton:
                Intent intent = new Intent(view.getContext(), Main2Activity.class);
                intent.putExtra("id", -1);
                startActivityForResult(intent, 0);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
