package com.nfricke.coursecrafter_selfmade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.nfricke.coursecrafter_selfmade.R;

public class NavListAdapter extends ArrayAdapter<String> {

    private final int[] icons;

    public NavListAdapter(Context context, String[] options, int[] icons) {
        super(context, R.layout.item_nav, options);
        this.icons = icons;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_nav, parent, false);
        }

        //Set text and Icon of Nav view element
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