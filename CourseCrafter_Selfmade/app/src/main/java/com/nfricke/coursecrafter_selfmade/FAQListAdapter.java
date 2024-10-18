package com.nfricke.coursecrafter_selfmade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class FAQListAdapter extends ArrayAdapter<FAQ> {

    public FAQListAdapter(Context context, List<FAQ> faqList) {
        super(context, R.layout.faqlist_item, faqList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Aktuelles FAQ-Objekt holen
        FAQ faq = getItem(position);

        // View wiederverwenden oder neu erstellen
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.faqlist_item, parent, false);
        }

        // TextViews in der View finden
        TextView labelView = convertView.findViewById(R.id.faq_label);
        TextView titleView = convertView.findViewById(R.id.faq_Titel);
        TextView subtitleView = convertView.findViewById(R.id.faq_subtitel);

        // Daten in die TextViews setzen
        labelView.setText(faq.getLabel());
        titleView.setText(faq.getTitle());
        subtitleView.setText(faq.getSubtitle());

        // View zurückgeben
        return convertView;
    }
}