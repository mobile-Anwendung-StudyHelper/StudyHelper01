package com.nfricke.coursecrafter_selfmade;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FAQDetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faq_detail, container, false);

        //TextView labelView = view.findViewById(R.id.detail_label);
        //TextView titleView = view.findViewById(R.id.detail_title);
        //TextView subtitleView = view.findViewById(R.id.detail_subtitle);
        WebView webView = view.findViewById(R.id.detail_webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Argumente aus dem Bundle abrufen
        Bundle bundle = getArguments();
        if (bundle != null) {
            //String label = bundle.getString("label");
            //String title = bundle.getString("title");
            //String subtitle = bundle.getString("subtitle");
            String link = bundle.getString("Link");

            //labelView.setText(label);
            //titleView.setText(title);
            //subtitleView.setText(subtitle);
            webView.loadUrl("file:///android_asset/" + link);
        }

        return view;
    }
}
