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

        // then according to the position of the view assign the desired image for the same
        //ImageView numbersImage = currentItemView.findViewById(R.id.imageView);
        //assert currentNumberPosition != null;
        //numbersImage.setImageResource(currentModulPosition.getNumbersImageId());

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView textView1 = currentItemView.findViewById(R.id.textView1);
        textView1.setText(currentModulPosition.getModulName());

        // then according to the position of the view assign the desired TextView 2 for the same
        TextView textView2 = currentItemView.findViewById(R.id.textView2);
        textView2.setText(currentModulPosition.getProfName());

        // then return the recyclable view
        return currentItemView;
    }

}
