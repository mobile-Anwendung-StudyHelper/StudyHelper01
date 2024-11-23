package com.nfricke.coursecrafter_selfmade.Adapter;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nfricke.coursecrafter_selfmade.R;

import java.util.List;

public class ModulplanAdapter extends ArrayAdapter<String> {
    public ModulplanAdapter(Context context, List<String> scheduleList) {
        super(context, R.layout.item_modulplan, scheduleList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_modulplan, parent, false);
        }

        String schedule = getItem(position);

        TextView blockTimeTextView = convertView.findViewById(R.id.blockTimeTextView);
        TextView moduleDetailsTextView = convertView.findViewById(R.id.moduleDetailsTextView);

        // Assuming the string is in format "Block Time:\n Module Details\n"
        String[] parts = schedule.split(";", 2);
        if (parts.length > 1) {
            blockTimeTextView.setText(parts[0].trim());
            moduleDetailsTextView.setText(parts[1].trim());
        } else {
            blockTimeTextView.setText(schedule);
            moduleDetailsTextView.setText(""); // Clear module details if not present
        }

        return convertView;
    }
}
