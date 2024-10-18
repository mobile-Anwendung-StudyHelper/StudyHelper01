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

    private ModulManagerDAO modulManagerDAO;
    private ModulManager modulManager;

    public ModulListAdapter(@NonNull Context context, ModulManager modulManager, ModulManagerDAO modulManagerDAO) {
        super(context, 0, modulManager);
        this.modulManagerDAO = modulManagerDAO;
        this.modulManager = modulManager;
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
                modulManagerDAO.saveModulManager(modulManager);
                notifyDataSetChanged();
            }});

        TextView textView1 = currentItemView.findViewById(R.id.modullist_modul_name);
        textView1.setText(modul.getModulName());

        TextView textView2 = currentItemView.findViewById(R.id.modullist_prof_name);
        if (modul.getProfName().isEmpty()) textView2.setText("-"); else textView2.setText(modul.getProfName());

        TextView textView3 = currentItemView.findViewById(R.id.modullist_note);
        if (modul.getNote() == 0) textView3.setText("Note: -"); else textView3.setText("Note: " + modul.getNoteString());

        TextView textView11 = currentItemView.findViewById(R.id.modullist_tag_1);
        if (modul.getTagString(0).equals("<auswahl>")) textView11.setText("-"); else textView11.setText(modul.getTagString(0));
        TextView textView12 = currentItemView.findViewById(R.id.modullist_block_1);
        if (modul.getBlockString(0).equals("<auswahl>")) textView12.setText("-"); else textView12.setText(modul.getBlockString(0));
        TextView textView13 = currentItemView.findViewById(R.id.modullist_raum_1);
        if (modul.getRaum(0).isEmpty()) textView13.setText("-"); else textView13.setText(modul.getRaum(0));

        TextView textView21 = currentItemView.findViewById(R.id.modullist_tag_2);
        if (modul.getTagString(1).equals("<auswahl>")) textView21.setText("-"); else textView21.setText(modul.getTagString(1));
        TextView textView22 = currentItemView.findViewById(R.id.modullist_block_2);
        if (modul.getBlockString(1).equals("<auswahl>")) textView22.setText("-"); else textView22.setText(modul.getBlockString(1));
        TextView textView23 = currentItemView.findViewById(R.id.modullist_raum_2);
        if (modul.getRaum(1).isEmpty()) textView23.setText("-"); else textView23.setText(modul.getRaum(1));

        TextView textView31 = currentItemView.findViewById(R.id.modullist_tag_3);
        if (modul.getTagString(2).equals("<auswahl>")) textView31.setText("-"); else textView31.setText(modul.getTagString(2));
        TextView textView32 = currentItemView.findViewById(R.id.modullist_block_3);
        if (modul.getBlockString(2).equals("<auswahl>")) textView32.setText("-"); else textView32.setText(modul.getBlockString(2));
        TextView textView33 = currentItemView.findViewById(R.id.modullist_raum_3);
        if (modul.getRaum(2).isEmpty()) textView33.setText("-"); else textView33.setText(modul.getRaum(2));

        // then return the recyclable view
        return currentItemView;
    }
}
