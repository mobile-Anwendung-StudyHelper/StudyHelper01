package com.nfricke.coursecrafter_selfmade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nfricke.coursecrafter_selfmade.DAO.Modul;
import com.nfricke.coursecrafter_selfmade.MainActivity;
import com.nfricke.coursecrafter_selfmade.R;

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

        //Get current Module
        Modul currentModul = getItem(position);

        TextView moduleNameView = itemView.findViewById(R.id.moduleName);
        moduleNameView.setText(currentModul.getModulName());

        //Set Text in Viewlist
        TextView moduleGradeView = itemView.findViewById(R.id.moduleGrade);
        if (currentModul.getNote() == 0) {
            moduleGradeView.setText("-");
        } else {
            moduleGradeView.setText(currentModul.getNoteString());
        }

        return itemView;
    }
}