package ru.chand.simpletodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    ArrayList<TodoItem> items;
    TodoAdapter todoAdapter;
    ListView lvItems;
    TodoItemDatabase todoDatabase;

    private final int REQUEST_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        todoDatabase = new TodoItemDatabase(this);
        setContentView(R.layout.activity_main);
        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<TodoItem>();
        readItems();
        todoAdapter = new TodoAdapter(this, items);
        lvItems.setAdapter(todoAdapter);
        setupListViewListener();
    }

    private void readItems(){
        try {
            items = (ArrayList<TodoItem>) todoDatabase.getAllTodoItems();
        } catch (Exception e){
            items = new ArrayList<TodoItem>();
        }
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener(){
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter , View view, int pos, long id) {
                        TodoItem itemtodel = items.get(pos);
                        todoDatabase.deleteTodoItem(itemtodel);

                        items.remove(pos);
                        todoAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Item Removed", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> adapter, View view, int pos, long id){
                        TodoItem item = items.get(pos);
                        Intent edit = new Intent(MainActivity.this, EditItemActivity.class);
                        edit.putExtra("itemPosition", pos);
                        edit.putExtra("item", item.getBody());
                        startActivityForResult(edit, REQUEST_CODE);
                    }
                }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
              String itemtext = data.getStringExtra("item");
              int pos = data.getIntExtra("itemPosition", -1);

              if (pos >= 0 && itemtext != null && !itemtext.isEmpty()){
                  TodoItem item = items.get(pos);
                  item.setBody(itemtext);
                  todoDatabase.updateTodoItem(item);

                  items.set(pos, item);
                  todoAdapter.notifyDataSetChanged();
              }
        }
    }

    public void onAddItem(View v){
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText  = etNewItem.getText().toString();
        if (itemText != null && !itemText.isEmpty()){
            TodoItem item = new TodoItem(itemText, 1);
            long id = todoDatabase.addTodoItem(item);
            item.setId(id);
            items.add(item);
            todoAdapter.notifyDataSetChanged();
            etNewItem.setText("");
        } else {
            Toast.makeText(this, "Can't add Empty Item", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
