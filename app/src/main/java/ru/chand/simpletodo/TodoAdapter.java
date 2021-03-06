package ru.chand.simpletodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by chandrav on 12/29/14.
 */

public class TodoAdapter extends ArrayAdapter<TodoItem> {
    public TodoAdapter(Context context, ArrayList<TodoItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        TodoItem item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }
        // Lookup view for data population
        TextView tvBody = (TextView) convertView.findViewById(R.id.tvBody);
        TextView tvPriority = (TextView) convertView.findViewById(R.id.tvPriority);
        TextView tvDueDate = (TextView) convertView.findViewById(R.id.tvDueDate);
        // Populate the data into the template view using the data object
        tvBody.setText(item.getBody());
        tvPriority.setText(Integer.toString(item.getPriority()));
        tvDueDate.setText(item.getCompletionDate().toString());
        // Return the completed view to render on screen
        return convertView;
    }
}