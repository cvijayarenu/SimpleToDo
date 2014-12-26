package ru.chand.simpletodo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class EditItemActivity extends ActionBarActivity {

    private int pos;
    private String item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        item = getIntent().getStringExtra("item");
        pos = getIntent().getIntExtra("itemPosition",-1);
        EditText etEditItem = (EditText) findViewById(R.id.eTeditItem);
        etEditItem.setText(item);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_item, menu);
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

    public void onEditSaveItem(View v){
        EditText etEditItem = (EditText) findViewById(R.id.eTeditItem);
        String editedText  = etEditItem.getText().toString();
        Intent data = new Intent();
        data.putExtra("itemPosition", pos);
        data.putExtra("item", editedText);
        setResult(RESULT_OK, data);
        finish();

    }
}
