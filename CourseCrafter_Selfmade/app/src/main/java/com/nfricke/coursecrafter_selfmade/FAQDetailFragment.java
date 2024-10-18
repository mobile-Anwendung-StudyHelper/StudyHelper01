package com.nfricke.coursecrafter_selfmade;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;

public class FAQDetailFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faq_detail, container, false);

        FAQ faq = null;
        Bundle bundle = getArguments();
        if (bundle != null) {
            faq = (FAQ) bundle.getSerializable("faq"); // Annahme: FAQ ist serialisierbar
        }

        TextView labelView = view.findViewById(R.id.detail_label);
        TextView titleView = view.findViewById(R.id.detail_title);
        TextView subtitleView = view.findViewById(R.id.detail_subtitle);
        ImageView image = view.findViewById(R.id.imageView);
        TextView content1 = view.findViewById(R.id.content1);
        TextView content2 = view.findViewById(R.id.content2);

        labelView.setText(faq.getLabel());
        titleView.setText(faq.getTitle());
        subtitleView.setText(faq.getSubtitle());
        content1.setText(faq.getContent1());
        content1.setText(faq.getContent2());



        try (InputStream is = getContext().getAssets().open(faq.getLink())) {
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            image.setImageBitmap(bitmap);
        } catch (IOException e) {
            // Fehlerbehandlung, falls das Bild nicht geladen werden kann
            e.printStackTrace();
            // Optional: ein Standardbild anzeigen
            image.setImageResource(R.drawable.baseline_question_mark_24);
        }

        return view;
    }
}
