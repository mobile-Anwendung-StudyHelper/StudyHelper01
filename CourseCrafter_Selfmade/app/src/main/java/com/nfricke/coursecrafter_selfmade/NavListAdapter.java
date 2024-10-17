package com.nfricke.coursecrafter_selfmade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class NavListAdapter extends ArrayAdapter<String> {

    private final int[] icons;

    public NavListAdapter(Context context, String[] options, int[] icons) {
        super(context, R.layout.nav_item, options);
        this.icons = icons;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.nav_item, parent, false);
        }

        String option = getItem(position);
        int icon = icons[position];

        if (option != null) {
            TextView textView = convertView.findViewById(R.id.itemText);
            ImageView imageView = convertView.findViewById(R.id.itemIcon);

            textView.setText(option);
            imageView.setImageResource(icon);
        }

        return convertView;
    }
}