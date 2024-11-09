package com.nfricke.coursecrafter_selfmade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NotenListAdapter extends ArrayAdapter<Modul> {

    public NotenListAdapter(@NonNull Context context) {
        super(context, 0, ((MainActivity) context).modulManager);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_noten, parent, false);
        }

        Modul currentModul = getItem(position);

        TextView moduleNameView = itemView.findViewById(R.id.moduleName);
        moduleNameView.setText(currentModul.getModulName());

        TextView moduleGradeView = itemView.findViewById(R.id.moduleGrade);
        if (currentModul.getNote() == 0) {
            moduleGradeView.setText("-");
        } else {
            moduleGradeView.setText(currentModul.getNoteString());
        }

        return itemView;
    }
}