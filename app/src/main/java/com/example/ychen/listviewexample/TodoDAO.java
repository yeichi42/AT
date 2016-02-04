package com.example.ychen.listviewexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ychen on 2/3/2016.
 */
public class TodoDAO {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    private String[] allColumns = {MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_TODO};

    public TodoDAO(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Todo createTodo(String todo) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_TODO, todo);
        long insertId = database.insert(MySQLiteHelper.TABLE_TODO, null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_TODO, allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Todo newTodo = cursorToTodo(cursor);
        cursor.close();
        return newTodo;

    }

    public void updateTodo(String todo, int id) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_TODO, todo);
//        //public long insert(String table, String nullColumnHack, ContentValues values) {
//        long insertId = database.insert(MySQLiteHelper.TABLE_TODO, null, values);
//        //public int update(String table, ContentValues values, String whereClause, String[] whereArgs) {
        database.update(MySQLiteHelper.TABLE_TODO, values, MySQLiteHelper.COLUMN_ID + " = " + id, null);
//        Cursor cursor = database.query(MySQLiteHelper.TABLE_TODO, allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
//                null, null, null);
//        cursor.moveToFirst();
//        Todo newTodo = cursorToTodo(cursor);
//        cursor.close();
//        return newTodo;

    }


    public void deleteTodo(int id) {
        //long id = todo.getId();
        database.delete(MySQLiteHelper.TABLE_TODO, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Todo> getAllTodos() {
        List<Todo> todos = new ArrayList<Todo>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_TODO,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Todo todo = cursorToTodo(cursor);
            todos.add(todo);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return todos;
    }

    private Todo cursorToTodo(Cursor cursor) {
        Todo todo = new Todo();
        todo.setId(cursor.getInt(0));
        todo.setName(cursor.getString(1));
        return todo;
    }
}
