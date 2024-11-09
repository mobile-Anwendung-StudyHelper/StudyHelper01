package com.nfricke.coursecrafter_selfmade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nfricke.coursecrafter_selfmade.DAO.FAQ;
import com.nfricke.coursecrafter_selfmade.R;

import java.util.List;

public class FAQListAdapter extends ArrayAdapter<FAQ> {

    public FAQListAdapter(Context context, List<FAQ> faqList) {
        super(context, R.layout.item_faqlist, faqList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Get current FAQ object
        FAQ faq = getItem(position);

        //Reuse or recreate a view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_faqlist, parent, false);
        }

        //Find TextViews in the View
        TextView labelView = convertView.findViewById(R.id.faq_label);
        TextView titleView = convertView.findViewById(R.id.faq_Titel);
        TextView subtitleView = convertView.findViewById(R.id.faq_subtitel);

        //Put data into the TextViews
        labelView.setText(faq.getLabel());
        titleView.setText(faq.getTitle());
        subtitleView.setText(faq.getSubtitle());

        return convertView;
    }
}