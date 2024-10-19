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

public class ModulListAdapter extends ArrayAdapter<Modul>{

    public ModulListAdapter(@NonNull Context context) {
        super(context, 0, ((MainActivity)context).modulManager);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.modullist_item, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        Modul modul = getItem(position);

        Button belegtButton = currentItemView.findViewById(R.id.modullist_belegt_button);
        if (modul.isBelegt()) belegtButton.setText("✓"); else belegtButton.setText("+");
        belegtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modul.setBelegt(!modul.isBelegt());
                if (modul.isBelegt()) {
                    Toast.makeText(getContext(),modul.getModulName() + " wird belegt", Toast.LENGTH_SHORT).show();
                } else if (!modul.isBelegt()) {
                    Toast.makeText(getContext(),modul.getModulName() + " wird nicht belegt", Toast.LENGTH_SHORT).show();
                }
                ((MainActivity)getContext()).modulManagerDAO.saveModulManager(((MainActivity)getContext()).modulManager);
                notifyDataSetChanged();
            }});

        TextView textView1 = currentItemView.findViewById(R.id.modullist_modul_name);
        textView1.setText(modul.getModulName());

        TextView textView2 = currentItemView.findViewById(R.id.modullist_prof_name);
        if (modul.getProfName().isEmpty()) textView2.setText("-"); else textView2.setText(modul.getProfName());

        TextView textView3 = currentItemView.findViewById(R.id.modullist_note);
        if (modul.getNote() == 0) textView3.setText("Note: -"); else textView3.setText("Note: " + modul.getNoteString());

        TextView textView11 = currentItemView.findViewById(R.id.modullist_tag_1);
        if (modul.getTag(0) == 0) textView11.setText("-"); else textView11.setText(((MainActivity)getContext()).wochentage[modul.getTag(0)]);
        TextView textView12 = currentItemView.findViewById(R.id.modullist_block_1);
        if (modul.getBlock(0) == 0) textView12.setText("-"); else textView12.setText(((MainActivity)getContext()).bloecke[modul.getBlock(0)]);
        TextView textView13 = currentItemView.findViewById(R.id.modullist_raum_1);
        if (modul.getRaum(0).isEmpty()) textView13.setText("-"); else textView13.setText(modul.getRaum(0));

        TextView textView21 = currentItemView.findViewById(R.id.modullist_tag_2);
        if (modul.getTag(1) == 0) textView21.setText("-"); else textView21.setText(((MainActivity)getContext()).wochentage[modul.getTag(1)]);
        TextView textView22 = currentItemView.findViewById(R.id.modullist_block_2);
        if (modul.getBlock(1) == 0) textView22.setText("-"); else textView22.setText(((MainActivity)getContext()).bloecke[modul.getBlock(1)]);
        TextView textView23 = currentItemView.findViewById(R.id.modullist_raum_2);
        if (modul.getRaum(1).isEmpty()) textView23.setText("-"); else textView23.setText(modul.getRaum(1));

        TextView textView31 = currentItemView.findViewById(R.id.modullist_tag_3);
        if (modul.getTag(2) == 0) textView31.setText("-"); else textView31.setText(((MainActivity)getContext()).wochentage[modul.getTag(2)]);
        TextView textView32 = currentItemView.findViewById(R.id.modullist_block_3);
        if (modul.getBlock(2) == 0) textView32.setText("-"); else textView32.setText(((MainActivity)getContext()).bloecke[modul.getBlock(2)]);
        TextView textView33 = currentItemView.findViewById(R.id.modullist_raum_3);
        if (modul.getRaum(2).isEmpty()) textView33.setText("-"); else textView33.setText(modul.getRaum(2));

        // then return the recyclable view
        return currentItemView;
    }
}
