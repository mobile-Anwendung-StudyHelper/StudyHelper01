package com.nfricke.coursecrafter_selfmade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class HomeModulListView extends ArrayAdapter<Modul> {

    public HomeModulListView(@NonNull Context context) {
        super(context, 0, ((MainActivity) context).modulManager);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.home_list_item, parent, false);
        }

        Modul currentModul = getItem(position);

        TextView BlockNameView = itemView.findViewById(R.id.textView234);
        TextView moduleNameView = itemView.findViewById(R.id.modullist_modul_name);
        BlockNameView.setText("Block:" + "1");

        moduleNameView.setText(currentModul.getModulName());
        /*
        TextView moduleGradeView = itemView.findViewById(R.id.moduleGrade);
        if (currentModul.getNote() == 0) {
            moduleGradeView.setText("-");
        } else {
            moduleGradeView.setText(currentModul.getNoteString());
        }
        */
        return itemView;
    }

   

}

