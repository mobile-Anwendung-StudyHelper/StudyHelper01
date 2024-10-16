package com.nfricke.coursecrafter_selfmade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ModulAdapter extends ArrayAdapter<Modul>{
    // invoke the suitable constructor of the ArrayAdapter class
    public ModulAdapter(@NonNull Context context, ModulManager modulManager) {
        // pass the context and arrayList for the super
        // constructor of the ArrayAdapter class
        super(context, 0, modulManager);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_modullist_item, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        Modul currentModulPosition = getItem(position);

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView textView1 = currentItemView.findViewById(R.id.modullist_modul_name);
        textView1.setText(currentModulPosition.getModulName());

        TextView textView2 = currentItemView.findViewById(R.id.modullist_prof_name);
        textView2.setText(currentModulPosition.getProfName());

        TextView textView11 = currentItemView.findViewById(R.id.modullist_tag_1);
        textView11.setText(currentModulPosition.getTagString(0));
        TextView textView12 = currentItemView.findViewById(R.id.modullist_block_1);
        textView12.setText(currentModulPosition.getBlockString(0));
        TextView textView13 = currentItemView.findViewById(R.id.modullist_raum_1);
        textView13.setText(currentModulPosition.getRaum(0));

        TextView textView21 = currentItemView.findViewById(R.id.modullist_tag_2);
        textView21.setText(currentModulPosition.getTagString(1));
        TextView textView22 = currentItemView.findViewById(R.id.modullist_block_2);
        textView22.setText(currentModulPosition.getBlockString(1));
        TextView textView23 = currentItemView.findViewById(R.id.modullist_raum_2);
        textView23.setText(currentModulPosition.getRaum(1));

        TextView textView31 = currentItemView.findViewById(R.id.modullist_tag_3);
        textView31.setText(currentModulPosition.getTagString(2));
        TextView textView32 = currentItemView.findViewById(R.id.modullist_block_3);
        textView32.setText(currentModulPosition.getBlockString(2));
        TextView textView33 = currentItemView.findViewById(R.id.modullist_raum_3);
        textView33.setText(currentModulPosition.getRaum(2));

        // then return the recyclable view
        return currentItemView;
    }

}
